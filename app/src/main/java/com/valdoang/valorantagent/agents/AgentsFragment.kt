package com.valdoang.valorantagent.agents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valdoang.valorantagent.R
import com.valdoang.valorantagent.core.data.Resource
import com.valdoang.valorantagent.core.ui.ValorantAdapter
import com.valdoang.valorantagent.databinding.FragmentAgentsBinding
import com.valdoang.valorantagent.detail.DetailValorantActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentsFragment : Fragment() {

    private val agentsViewModel: AgentsViewModel by viewModel()

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val valorantAdapter = ValorantAdapter()
            valorantAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailValorantActivity::class.java)
                intent.putExtra(DetailValorantActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            agentsViewModel.valorant.observe(viewLifecycleOwner) { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            valorantAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvValorant) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = valorantAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
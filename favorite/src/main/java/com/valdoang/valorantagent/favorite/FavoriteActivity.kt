package com.valdoang.valorantagent.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.valdoang.valorantagent.core.ui.ValorantAdapter
import com.valdoang.valorantagent.detail.DetailValorantActivity
import com.valdoang.valorantagent.favorite.databinding.ActivityFavoriteBinding
import com.valdoang.valorantagent.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(com.valdoang.valorantagent.R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupView()
    }

    private fun setupView() {
        val valorantAdapter = ValorantAdapter()
        valorantAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailValorantActivity::class.java)
            intent.putExtra(DetailValorantActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteValorant.observe(this) { dataValorant ->
            valorantAdapter.setData(dataValorant)
            binding.viewEmpty.root.visibility =
                if (dataValorant.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvValorant) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = valorantAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}
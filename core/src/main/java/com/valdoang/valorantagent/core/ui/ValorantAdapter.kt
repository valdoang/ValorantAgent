package com.valdoang.valorantagent.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valdoang.valorantagent.core.R
import com.valdoang.valorantagent.core.databinding.ItemListValorantBinding
import com.valdoang.valorantagent.core.domain.model.Valorant
import java.util.ArrayList

class ValorantAdapter : RecyclerView.Adapter<ValorantAdapter.ListViewHolder>() {

    private var listData = ArrayList<Valorant>()
    var onItemClick: ((Valorant) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Valorant>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_valorant, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListValorantBinding.bind(itemView)
        fun bind(data: Valorant) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.agentIcon)
                    .into(ivItemImage)
                tvItemName.text = data.agentName
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
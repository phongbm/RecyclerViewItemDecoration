package com.phongbm.recyclerviewitemdecoration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phongbm.recyclerviewitemdecoration.databinding.ItemRecyclerViewBinding

/**
 * Create by PhongBM on 03/13/2022
 */

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val data = ArrayList<Int>()

    init {
        for (i in 1..50) {
            data.add(i)
        }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(position)
    }

    inner class ViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(position: Int) {
            val number = data[position]

            val title = "Title $number"
            binding.txtTitle.text = title

            val description = "Description $number"
            binding.txtDescription.text = description
        }
    }

}
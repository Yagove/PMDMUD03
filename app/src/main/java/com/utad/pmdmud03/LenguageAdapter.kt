package com.utad.pmdmud03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utad.pmdmud03.databinding.ItemLenguageBinding

class LenguageAdapter(private val lenguages: List<Lenguage>) :
    RecyclerView.Adapter<LenguageAdapter.LenguageViewHolder>() {

    class LenguageViewHolder(val binding: ItemLenguageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LenguageViewHolder {
        val binding = ItemLenguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LenguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LenguageViewHolder, position: Int) {
        val lenguage = lenguages[position]

        holder.binding.tvLeng.text = lenguage.name
        holder.binding.ivLeng.setImageResource(lenguage.icon)
    }

    override fun getItemCount(): Int {
        return lenguages.size
    }
}

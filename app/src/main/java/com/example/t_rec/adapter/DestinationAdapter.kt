package com.example.t_rec.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.t_rec.data.response.DestinationResponse
import com.example.t_rec.databinding.ItemRowBinding

class DestinationAdapter(private var destinations: List<DestinationResponse>) :
    RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]

        with(holder.binding) {
            tvDestinasiName.text = destination.place_Name
            tvDestinasiLoc.text = destination.city

            Glide.with(root.context)
                .load(destination.urlImage)
                .into(imgDestinasi)
        }
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDestinations: List<DestinationResponse>) {
        destinations = newDestinations
        notifyDataSetChanged()
    }
}

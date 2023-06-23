package com.chugunov.foodapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.foodapp.data.network.FoodItemDto
import com.chugunov.foodapp.databinding.ItemCardBinding

class ItemsAdapter : ListAdapter<FoodItemDto, ItemsAdapter.ItemsViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ItemsViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(food)
    }


    inner class ItemsViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(foodItemDto: FoodItemDto) {
                binding.itemImage.load(foodItemDto.img)
                binding.itemPriceButton.text = foodItemDto.price
                binding.itemTextName.text = foodItemDto.name
                binding.itemTextDescription.text = foodItemDto.description
            }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FoodItemDto>() {

            override fun areItemsTheSame(oldItem: FoodItemDto, newItem: FoodItemDto): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: FoodItemDto, newItem: FoodItemDto): Boolean {
                return oldItem == newItem
            }

        }
    }

}
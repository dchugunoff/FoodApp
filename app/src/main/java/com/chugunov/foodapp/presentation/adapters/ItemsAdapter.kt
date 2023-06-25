package com.chugunov.foodapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.foodapp.R
import com.chugunov.foodapp.databinding.ItemCardBinding
import com.chugunov.foodapp.domain.models.FoodModel

class ItemsAdapter(private val context: Context) :
    ListAdapter<FoodModel, ItemsAdapter.ItemsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapter.ItemsViewHolder {
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

        fun bind(foodModel: FoodModel) {
            binding.itemImage.load(foodModel.img)
            binding.itemPriceButton.text =
                String.format(context.getString(R.string.price_btn_string), foodModel.price)
            binding.itemTextName.text = foodModel.name
            binding.itemTextDescription.text = foodModel.description
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FoodModel>() {

            override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}
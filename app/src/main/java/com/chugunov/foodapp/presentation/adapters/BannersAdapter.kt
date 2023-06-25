package com.chugunov.foodapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.foodapp.databinding.BannerCardBinding
import com.chugunov.foodapp.domain.models.BannerModel

class BannersAdapter : ListAdapter<BannerModel, BannersAdapter.BannersViewHolder>(DiffCallBack) {


    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val banner = getItem(position)
        holder.bind(banner)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val binding = BannerCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannersViewHolder(binding)
    }

    inner class BannersViewHolder(private val binding: BannerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(banner: BannerModel) {
                binding.imageCardBanner.load(banner.drawable)
            }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<BannerModel>() {
            override fun areItemsTheSame(oldItem: BannerModel, newItem: BannerModel): Boolean {
                return oldItem.drawable == newItem.drawable
            }

            override fun areContentsTheSame(oldItem: BannerModel, newItem: BannerModel): Boolean {
                return oldItem == newItem
            }

        }
    }


}
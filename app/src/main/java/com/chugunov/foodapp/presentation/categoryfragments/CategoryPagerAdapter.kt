package com.chugunov.foodapp.presentation.categoryfragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CategoryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AllItemsFragment()
            1 -> BurgersFragment()
            else -> PizzaFragment()
        }
    }
}
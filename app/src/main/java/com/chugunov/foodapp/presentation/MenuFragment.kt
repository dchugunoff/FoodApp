package com.chugunov.foodapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.MainActivity
import com.chugunov.foodapp.databinding.FragmentMenuBinding
import com.chugunov.foodapp.presentation.adapters.BannersAdapter
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter
import com.chugunov.foodapp.presentation.categoryfragments.CategoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    private val viewModel: MainViewModel by lazy {
        (requireActivity() as MainActivity).sharedViewModel
    }

    private val tabTitles = arrayListOf("Все", "Бургеры", "Пицца")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        setUpTableLayoutWithViewPager()
        return binding.root
    }

    private fun setUpTableLayoutWithViewPager() {
        binding.viewPager.adapter = CategoryPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCitiesSpinner()
        val adapter = BannersAdapter()
//        val itemAdapter = (requireActivity() as MainActivity).adapter
//        binding.rvItems.adapter = itemAdapter
//        viewModel.itemList.observe(viewLifecycleOwner) {
//            itemAdapter.submitList(it)
//        }
        binding.rvBanners.adapter = adapter
        viewModel.bannersList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
//        Log.d("MenuFragment", "$viewModel")
//        Log.d("MenuFragment", "$itemAdapter")
//        Log.d("MenuFragment", "${viewModel.itemList.value}")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun createCitiesSpinner() {
        val cities = listOf("Москва", "Казань")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.citySpinner.adapter = adapter
    }
}
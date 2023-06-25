package com.chugunov.foodapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.databinding.FragmentMenuBinding
import com.chugunov.foodapp.presentation.adapters.BannersAdapter
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter
import com.google.android.material.chip.Chip

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    private val viewModel: MainViewModel by lazy {
        val application = requireActivity().application
        val viewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCitiesSpinner()
        viewModelObservers()
        itemsAdapter = ItemsAdapter()
        binding.rvItems.adapter = itemsAdapter
        val chipGroup = binding.chipGroup
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedCategory = group.findViewById<Chip>(checkedId)?.text.toString()
            filterItemsByCategory(selectedCategory)
            filterItemsByCategory(selectedCategory)
            Log.d("MenuFragment", selectedCategory)
        }
    }



    private fun viewModelObservers() {
        viewModel.bannersList.observe(viewLifecycleOwner) {
            val adapter = BannersAdapter()
            binding.rvBanners.adapter = adapter
            adapter.submitList(it)
        }
        viewModel.itemList.observe(viewLifecycleOwner) {
            itemsAdapter.submitList(it)
        }
    }

    private fun filterItemsByCategory(selectedCategory: String) {
        val fullItemList = viewModel.itemList.value
        if (selectedCategory == "null") {
            itemsAdapter.submitList(fullItemList)
        } else {
            val filteredList = fullItemList?.filter { foodModel ->
                foodModel.category == selectedCategory
            }
            itemsAdapter.submitList(filteredList)
        }
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
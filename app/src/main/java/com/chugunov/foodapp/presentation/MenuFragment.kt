package com.chugunov.foodapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.databinding.FragmentMenuBinding
import com.chugunov.foodapp.presentation.adapters.BannersAdapter
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    private val viewModel: MainViewModel by lazy {
        val application = requireActivity().application
        val viewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

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
        val adapter = BannersAdapter()
        val itemsAdapter = ItemsAdapter()
        binding.rvBanners.adapter = adapter
        binding.rvItems.adapter = itemsAdapter
        viewModel.bannersList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.itemList.observe(viewLifecycleOwner) {
            itemsAdapter.submitList(it)
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
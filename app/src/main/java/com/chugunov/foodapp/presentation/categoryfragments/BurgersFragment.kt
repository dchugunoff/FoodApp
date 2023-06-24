package com.chugunov.foodapp.presentation.categoryfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chugunov.foodapp.MainActivity
import com.chugunov.foodapp.databinding.FragmentBurgersItemsBinding
import com.chugunov.foodapp.presentation.MainViewModel
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter

class BurgersFragment : Fragment() {

    private var _binding: FragmentBurgersItemsBinding? = null
    private val binding: FragmentBurgersItemsBinding
        get() = _binding ?: throw RuntimeException("FragmentBurgersItemsBinding == null")


    private val viewModel: MainViewModel by lazy {
        (requireActivity() as MainActivity).sharedViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBurgersItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = (requireActivity() as MainActivity).adapter
        binding.rvBurgersItems.adapter = itemAdapter
        viewModel.itemList.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)
        }
        Log.d("BurgerFragment", "$viewModel")
        Log.d("BurgerFragment", "$itemAdapter")
        Log.d("BurgerFragment", "${viewModel.itemList.value}")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
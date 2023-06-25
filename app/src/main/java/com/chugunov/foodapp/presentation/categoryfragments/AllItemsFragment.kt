package com.chugunov.foodapp.presentation.categoryfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.MainActivity
import com.chugunov.foodapp.databinding.FragmentAllItemsBinding
import com.chugunov.foodapp.presentation.MainViewModel
import com.chugunov.foodapp.presentation.MainViewModelFactory
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter

class AllItemsFragment : Fragment() {

    private var _binding: FragmentAllItemsBinding? = null
    private val binding: FragmentAllItemsBinding
        get() = _binding ?: throw RuntimeException("FragmentAllItemsBinding == null")

    private val viewModel: MainViewModel by lazy {
        (requireActivity() as MainActivity).sharedViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = (requireActivity() as MainActivity).adapter
        binding.rvItems.adapter = itemAdapter
        viewModel.itemList.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)
        }
        Log.d("AllItemsFragment", "$viewModel")
//        Log.d("AllItemsFragment", "$itemAdapter")
        Log.d("AllItemsFragment", "${viewModel.itemList.value}")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
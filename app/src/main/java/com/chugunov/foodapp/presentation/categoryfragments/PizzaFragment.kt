package com.chugunov.foodapp.presentation.categoryfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.R
import com.chugunov.foodapp.presentation.MainViewModel
import com.chugunov.foodapp.presentation.MainViewModelFactory

class PizzaFragment : Fragment(R.layout.fragment_pizza_items) {


    val viewModel: MainViewModel by lazy {
        val application = requireActivity().application
        val viewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
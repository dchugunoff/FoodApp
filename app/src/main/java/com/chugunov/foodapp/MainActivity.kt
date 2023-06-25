package com.chugunov.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chugunov.foodapp.presentation.MainViewModel
import com.chugunov.foodapp.presentation.MainViewModelFactory
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter

class MainActivity : AppCompatActivity() {

    val sharedViewModel: MainViewModel by lazy {
        val application = application
        val viewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    val adapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
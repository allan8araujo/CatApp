package com.example.catapp.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.catapp.R
import com.example.catapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val controller by lazy {
        findNavController(R.id.activity_main_navHost)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        NavigationUI.setupWithNavController(binding.bottomNavBar, controller)

        binding.bottomNavBar.setOnClickListener {
            if (it.id == R.id.catFragment) {
                controller.navigate(R.id.catFragment)
            } else if (it.id == R.id.historyFragment) {
                controller.navigate(R.id.to_historyFragment)
            }
        }
    }
}

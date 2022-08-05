package com.example.catapp.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.catapp.R
import com.example.catapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavBar()
    }

    private fun setupNavBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_navHost) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)

        binding.bottomNavBar.setOnClickListener {
            if (it.id == R.id.catFragment) {
                navController.navigate(R.id.catFragment)
            } else if (it.id == R.id.historyFragment) {
                navController.navigate(R.id.to_historyFragment)
            }
        }
    }
}

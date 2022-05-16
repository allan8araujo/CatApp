package com.example.catapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.ui.fragments.HistoryFragment

class MainActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HistoryFragment())
        transaction.commit()
    }
}

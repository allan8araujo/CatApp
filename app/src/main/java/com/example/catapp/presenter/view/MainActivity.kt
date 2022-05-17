package com.example.catapp.presenter.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.catapp.R
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.presenter.view.fragments.CatFragment
import com.example.catapp.presenter.view.fragments.HistoryFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        replaceFragment(CatFragment())
        val navHistoryButton = findViewById<View>(R.id.ic_hist)
            .setOnClickListener(this)
        val navImageButton =
            findViewById<View>(R.id.ic_image)
                .setOnClickListener(this)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onClick(view: View?) {
        if (view == findViewById(R.id.ic_hist)) {
            replaceFragment(HistoryFragment())
        } else if (view == findViewById(R.id.ic_image)) {
            replaceFragment(CatFragment())

        }
    }
}
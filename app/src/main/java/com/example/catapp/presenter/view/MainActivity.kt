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
    private val navHistoryButton by lazy {
        findViewById<View>(R.id.ic_hist)
    }

    private val navImageButton by lazy {
        findViewById<View>(R.id.ic_image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        replaceFragment(CatFragment())
        navHistoryButton.setOnClickListener(this)
        navImageButton.setOnClickListener(this)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onClick(view: View?) {
        if (view == navHistoryButton) {
            replaceFragment(HistoryFragment())
        } else if (view == navImageButton) {
            replaceFragment(CatFragment())
        }
    }
}

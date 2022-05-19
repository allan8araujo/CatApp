package com.example.catapp.presenter.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.R
import com.example.catapp.data.repository.Repository
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.presenter.view.adapters.FragmentReplacerAdapter
import com.example.catapp.presenter.view.fragments.CatFragment
import com.example.catapp.presenter.view.fragments.HistoryFragment
import com.example.catapp.presenter.viewModel.GetCatViewModel
import com.example.catapp.presenter.viewModel.MainViewModelFactory

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

        navHistoryButton.setOnClickListener(this)
        navImageButton.setOnClickListener(this)
        if (savedInstanceState == null) {
            callReplaceFragment(CatFragment())
        }
    }

    override fun onClick(view: View?) {
        if (view == navHistoryButton) {
            callReplaceFragment(HistoryFragment())
        } else if (view == navImageButton) {
            callReplaceFragment(CatFragment())
        }
    }

    private fun callReplaceFragment(
        fragment: Fragment,
        transaction: FragmentTransaction = supportFragmentManager.beginTransaction(),
    ) {
        FragmentReplacerAdapter().replaceFragment(fragment, transaction)
    }
}

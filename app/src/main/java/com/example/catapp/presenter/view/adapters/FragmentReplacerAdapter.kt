package com.example.catapp.presenter.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.catapp.R

class FragmentReplacerAdapter {
    fun replaceFragment(fragment: Fragment, transaction: FragmentTransaction) {
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}

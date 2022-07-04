package com.example.catapp.presenter.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.catapp.R
import com.example.catapp.data.Repository
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.util.MainViewModelFactory
import com.example.catapp.presenter.viewModel.CatViewModel
import com.example.database.CatDataBase
import java.io.File
import java.io.FileOutputStream

class CatFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCatBinding
    private lateinit var progressBar: ProgressBar

    private val database by lazy { CatDataBase.CatRoomDatabase.getDataBase(requireContext()) }
    private val catViewModel: CatViewModel by activityViewModels {
        MainViewModelFactory(Repository(database.appDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root
        progressBar = binding.pbLoading
        catViewModel.getImage()

        progressBar.visibility = View.VISIBLE
        tryResponseObserve(binding, progressBar)
        binding.buttonCatSearch.setOnClickListener(this)
        binding.buttonCatShare.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        if (view.id == binding.buttonCatSearch.id) {
            progressBar.visibility = View.VISIBLE
            catViewModel.getImage()
        }
        if (view.id == binding.buttonCatShare.id) {
            setupIntent(binding)
        }
    }

    private fun tryResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {
        try {
            catViewModel.catResponse.observe(viewLifecycleOwner) { responseBody ->
                catViewModel.observeCatResponse(binding, progressBar, responseBody)
            }
        } catch (e: Exception) {
            findNavController().navigate(R.id.to_historyFragment)
        }
    }

    /** this need to be here, intent is view responsability**/

    private fun setupIntent(binding: FragmentCatBinding) {
        val imageFolder = File((activity as AppCompatActivity).cacheDir, "images")
        val file = catViewModel.getCatUri(binding.imgCat.drawToBitmap(),imageFolder)
        val contentUri = FileProvider.getUriForFile(requireActivity(), "com.example.catapp.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe o gato!")
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Compartilhe via: "))
    }
}

package com.example.catapp.presenter.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.viewModel.CatViewModel
import java.io.File
import java.io.FileOutputStream

class CatFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCatBinding
    private lateinit var progressBar: ProgressBar
    private val catViewModel: CatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root
        val progressBar = binding.pbLoading

        progressBar.visibility = View.VISIBLE
        tryResponseObserve(binding, progressBar)
        binding.buttonCatSearch.setOnClickListener(this)
        binding.buttonCatShare.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        if (view.id == binding.buttonCatSearch.id) {
            progressBar?.visibility = View.VISIBLE
            catViewModel.getImage()
        }
        if (view.id == binding.buttonCatSearch.id) {
            setupIntent(binding)
        }
    }

    private fun tryResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {
        try {
            catViewModel.catResponse.observe(viewLifecycleOwner) { responseBody ->
                catViewModel.observeCatResponse(binding, progressBar, responseBody)
            }
        } catch (e: Exception) {
            Log.i("@@@@@@", e.message.toString())
        }
    }

    private fun setupIntent(binding: FragmentCatBinding) {
        val contentUri = getCatUri(binding.imgCat.drawToBitmap())
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe o gato!")
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Compartilhe via: "))
    }

    private fun getCatUri(bitmap: Bitmap): Uri? {
        val imageFolder = File((activity as AppCompatActivity).cacheDir, "images")
        var contentUri: Uri? = null

        imageFolder.mkdir()
        val file = File(imageFolder, "shared_image.png")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
        stream.flush()
        stream.close()
        contentUri =
            FileProvider.getUriForFile(requireActivity(), "com.example.catapp.fileprovider", file)
        return contentUri
    }
}

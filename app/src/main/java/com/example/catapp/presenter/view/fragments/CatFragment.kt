package com.example.catapp.presenter.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.bumptech.glide.Glide
import com.example.catapp.databinding.FragmentCatBinding
import com.example.catapp.presenter.view.adapters.ProgressBarListener
import com.example.catapp.presenter.viewModel.CatViewModel
import java.io.File
import java.io.FileOutputStream

class CatFragment : Fragment() {
    private val catViewModel: CatViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root
        val progressBar = binding.pbLoading

        progressBar.visibility = View.VISIBLE
        catViewModel.getImage()
        myResponseObserve(binding, progressBar)

        binding.buttonCatSearch.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            catViewModel.getImage()
        }
        binding.buttonCatShare.setOnClickListener {
            val contentUri = getCatUri(binding.imgCat.drawToBitmap())
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe o gato!")
            intent.putExtra(Intent.EXTRA_STREAM, contentUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(intent, "Compartilhe via: "))
        }
        return view
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

    private fun myResponseObserve(binding: FragmentCatBinding, progressBar: ProgressBar) {
        try {
            catViewModel.myResponse.observe(viewLifecycleOwner) { responseBody ->
                val responseToBitmap =
                    BitmapFactory.decodeStream(responseBody.byteStream())

                Glide.with(binding.root.context)
                    .load(responseToBitmap)
                    .centerCrop()
                    .listener(ProgressBarListener(progressBar, catViewModel))
                    .into(binding.imgCat)
            }
        } catch (e: Exception) {
            Log.i("@@@@@@", e.message.toString())
        }
    }
}

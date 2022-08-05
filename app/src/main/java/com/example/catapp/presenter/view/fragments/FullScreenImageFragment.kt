package com.example.catapp.presenter.view.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abstractions.CatPhoto
import com.example.catapp.R
import com.example.catapp.databinding.FragmentFullScreenImageBinding
import com.example.catapp.presenter.viewModel.MainScreenViewModel
import java.io.File

class FullScreenImageFragment : Fragment(R.layout.fragment_full_screen_image) {
    private val catFragmentsViewModel: MainScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentFullScreenImageBinding.inflate(inflater, container, false)
        val view = binding.root
        val fullscreenImage = binding.catImageView

        catFragmentsViewModel.selectedItem.value.let { catPhoto ->
            val dialogClickListener = setupDialogOnDelete(catPhoto)
            fullscreenImage.setImageBitmap(catPhoto?.image)
            binding.deleteButton.setOnClickListener {
                setupDialogClickListener(dialogClickListener)
            }
        }

        binding.backToListButton.setOnClickListener {
            findNavController().navigate(R.id.back_to_historyFragment)
        }
        binding.shareButton.setOnClickListener {
            setupIntent(binding)
        }

        return view
    }

    private fun setupDialogClickListener(dialogClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Are you sure?")
            .setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }

    private fun setupDialogOnDelete(catPhoto: CatPhoto?): DialogInterface.OnClickListener {
        val dialogClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        findNavController().navigate(R.id.back_to_historyFragment)
                        catFragmentsViewModel.delete(catPhoto)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }
        return dialogClickListener
    }

    private fun setupIntent(binding: FragmentFullScreenImageBinding) {
        val imageFolder = File((activity as AppCompatActivity).cacheDir, "images")
        val file =
            catFragmentsViewModel.getCatUri(binding.catImageView.drawToBitmap(), imageFolder)
        val contentUri =
            FileProvider.getUriForFile(requireActivity(), "com.example.catapp.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe o gato!")
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Compartilhe via: "))
    }
}

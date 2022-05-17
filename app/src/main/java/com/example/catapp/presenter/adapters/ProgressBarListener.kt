package com.example.catapp.presenter.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ProgressBarListener(private val progressbar: ProgressBar) : RequestListener<Drawable?> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable?>?,
        isFirstResource: Boolean,
    ): Boolean {
        progressbar.visibility = View.GONE
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable?>?,
        dataSource: DataSource?,
        isFirstResource: Boolean,
    ): Boolean {
        progressbar.visibility = View.GONE
        return false
    }
}

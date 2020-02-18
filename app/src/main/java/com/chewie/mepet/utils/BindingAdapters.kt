package com.chewie.mepet.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImageByUri")
fun setImageByUri(imageView: ImageView, resUrl: String?) {
    if (resUrl != null && resUrl.isNotEmpty()) {
        Picasso.get().load(resUrl).into(imageView)
    }
}
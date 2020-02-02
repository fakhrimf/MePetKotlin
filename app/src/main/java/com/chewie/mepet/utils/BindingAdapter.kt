package com.chewie.mepet.utils

import android.widget.ImageView
import androidx.appcompat.resources.R
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImageFromModel")
fun setImageFromModel(imageView: ImageView, encodedString: String?) {
    if (encodedString != null && encodedString.isNotEmpty()){
        imageView.setImageBitmap(BitmapUtility.getDecodedImage(encodedString))
    }else{
        imageView.setImageDrawable(imageView.context.getDrawable(com.chewie.mepet.R.drawable.ic_cat))
    }
}

@BindingAdapter("setImageFromUri")
fun setImageFromUri(imageView: ImageView, url:String?){
    if (url != null && url.isNotEmpty()){
        Picasso.get().load(url).error(com.chewie.mepet.R.drawable.smudge).into(imageView)
    }else imageView.apply {
        setImageResource(com.chewie.mepet.R.drawable.smudge)
    }
}
package com.ugurbuga.codecase.bindings

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun AppCompatImageView.setImageUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}
package com.example.erkinbekovbilimdz_all_4.utils


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get().load(url).into(this)
}
fun ImageView.glideLoadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}
package com.example.erkinbekovbilimdz_all_4.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?){
    Picasso.get().load(url).into(this)
}
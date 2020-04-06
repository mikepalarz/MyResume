package com.palarz.mike.myresume.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView

fun ImageView.setBrowserClickListener(link: String, context: Context) {
    this.setOnClickListener {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            addCategory(Intent.CATEGORY_BROWSABLE)
            data = Uri.parse(link)
        }
        context.startActivity(intent)
    }
}
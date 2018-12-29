package com.palarz.mike.myresume.extensions

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.palarz.mike.myresume.R

fun CardView.collapse() {
    val content = findViewById<RecyclerView>(R.id.rv_content)
    content.visibility = View.GONE

    val arrow = findViewById<ImageView>(R.id.contents_arrow)
    arrow.setImageResource(R.drawable.arrow_collapsed)
}

fun CardView.expand() {
    val content = findViewById<RecyclerView>(R.id.rv_content)
    content.visibility = View.VISIBLE

    val arrow = findViewById<ImageView>(R.id.contents_arrow)
    arrow.setImageResource(R.drawable.arrow_expanded)
}

fun CardView.isExpanded() = findViewById<RecyclerView>(R.id.rv_content).visibility == View.VISIBLE
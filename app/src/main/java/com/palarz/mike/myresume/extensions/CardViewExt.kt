package com.palarz.mike.myresume.extensions

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.palarz.mike.myresume.R


// TODO: Possible solution to delayed start: https://blog.danlew.net/2015/10/20/using-hardware-layers-to-improve-animation-performance/
fun CardView.collapse() {
    val content = findViewById<RecyclerView>(R.id.rv_content)
    val arrow = findViewById<ImageView>(R.id.contents_arrow)
    val currentHeight = content.layoutParams.height

    val rvAnimator = ValueAnimator.ofInt(currentHeight, 0).apply {
        addUpdateListener{ updatedAnimation ->
            val params = content.layoutParams
            params.height = updatedAnimation.animatedValue as Int
            content.layoutParams = params
        }
        duration = 1000
    }

    val arrowAnimator = ValueAnimator.ofFloat(00f, 180f).apply {
        addUpdateListener { updatedAnimation ->
            arrow.rotation = updatedAnimation.animatedValue as Float
        }

        duration = 1000
        startDelay = 250
    }

    AnimatorSet().apply {
        playTogether(rvAnimator, arrowAnimator)
        start()
    }

}

fun CardView.expand(maxHeight: Int) {
    val content = findViewById<RecyclerView>(R.id.rv_content)
    val arrow = findViewById<ImageView>(R.id.contents_arrow)

    val rvAnimator = ValueAnimator.ofInt(0, maxHeight).apply {
        addUpdateListener{updatedAnimation ->
            val params = content.layoutParams
            params.height = updatedAnimation.animatedValue as Int
            content.layoutParams = params
        }
        duration = 1000
    }

    val arrowAnimator = ValueAnimator.ofFloat(180f, 0f).apply {
        addUpdateListener { updatedAnimation ->
            arrow.rotation = updatedAnimation.animatedValue as Float
        }

        duration = 1000
        startDelay = 250
    }

    AnimatorSet().apply {
        playTogether(rvAnimator, arrowAnimator)
        start()
    }

}

fun CardView.isCollapsed(): Boolean {
    val content = findViewById<RecyclerView>(R.id.rv_content)
    return content.layoutParams.height == 0

}
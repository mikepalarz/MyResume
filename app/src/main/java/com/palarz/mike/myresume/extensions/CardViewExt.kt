package com.palarz.mike.myresume.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
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

        /*
        This needs to be added here since content is contained within a ConstraintLayout. The ConstraintLayout interprets
        a height of 0 as "match constraints", so the animation will always revert back to expanding content once it's
        finished. To prevent this, I've created a listener for the end of the animation which sets the visibility to
        GONE.
         */
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                content.visibility = View.GONE
            }
        })

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

        // Just as we did within collapse(), we need this since content is within a ConstraintLayout.
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator?) {
                content.visibility = View.VISIBLE
            }
        })

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
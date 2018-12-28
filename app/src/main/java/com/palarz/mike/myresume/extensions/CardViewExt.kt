package com.palarz.mike.myresume.extensions

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.FrameLayout

fun CardView.collapse(collapsedHeight: Int) {

    val initialHeight = measuredHeight
    val distanceToCollapse = initialHeight - collapsedHeight
    val animation = object: Animation() {

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height = (initialHeight - (distanceToCollapse * interpolatedTime)).toInt()
            requestLayout()
        }

        override fun willChangeBounds() = true
    }

    animation.duration = distanceToCollapse.toLong()
    startAnimation(animation)
}

fun CardView.expand() {
    val initialHeight = height

    measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    val targetHeight = measuredHeight

    val distanceToExpand = targetHeight - initialHeight

    val animation = object : Animation(){

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height = initialHeight + (distanceToExpand * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds() = true
    }

    animation.duration = distanceToExpand.toLong()
    startAnimation(animation)
}

fun CardView.isExpanded() = layoutParams.height == FrameLayout.LayoutParams.WRAP_CONTENT
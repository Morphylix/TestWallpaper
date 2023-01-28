package com.morphylix.android.testwallpaper.util

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator

fun animateAppear(view: View) {
    val anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
    anim.duration = 1000
    anim.interpolator = LinearInterpolator()
    anim.start()
}
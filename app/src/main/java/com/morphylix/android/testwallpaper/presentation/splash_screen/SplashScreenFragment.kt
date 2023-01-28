package com.morphylix.android.testwallpaper.presentation.splash_screen

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import com.morphylix.android.testwallpaper.databinding.FragmentSplashScreenBinding
import com.morphylix.android.testwallpaper.presentation.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>({ inflater, container ->
    FragmentSplashScreenBinding.inflate(inflater, container, false)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = activity?.splashScreen
            splashScreen?.setOnExitAnimationListener { splashScreenView ->
                splashScreenView.remove()
            }
        }
        super.onViewCreated(view, savedInstanceState)

        val anim = ObjectAnimator.ofInt(binding.splashScreenProgressBar, "progress", 0, 100)
        anim.duration = 1000
        anim.doOnEnd {
            val action =
                SplashScreenFragmentDirections.actionSplashScreenFragmentToCategoryChoiceFragment()
            findNavController().navigate(action)
        }
        anim.start()
    }

}
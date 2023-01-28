package com.morphylix.android.testwallpaper.presentation.pic_details

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import com.morphylix.android.testwallpaper.R
import com.morphylix.android.testwallpaper.databinding.FragmentPicDetailsBinding
import com.morphylix.android.testwallpaper.presentation.BaseFragment
import com.morphylix.android.testwallpaper.util.ErrorHandler
import com.morphylix.android.testwallpaper.util.animateAppear
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.IOException

private const val TAG = "PicDetailsFragment"

class PicDetailsFragment : BaseFragment<FragmentPicDetailsBinding>({ inflater, container ->
    FragmentPicDetailsBinding.inflate(inflater, container, false)
}) {

    private val args: PicDetailsFragmentArgs by navArgs()
    private lateinit var wallpaperManager: WallpaperManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setAsWallpaperButton.visibility = if (savedInstanceState != null) {
            View.GONE
        } else {
            View.VISIBLE
        }

        wallpaperManager = WallpaperManager.getInstance(requireContext().applicationContext)
        with(binding) {

            if (pictureImageView.drawable == null) {
                pictureImageView.isEnabled = false
            }

            setAsWallpaperButton.setOnClickListener {
                try {
                    val bitmap: Bitmap? = pictureImageView.drawable?.toBitmap()
                    if (bitmap != null) {
                        wallpaperManager.setBitmap(bitmap)
                        makeInfoToast()
                    }
                } catch (e: IOException) {
                    ErrorHandler.handleException(e, requireContext())
                }
            }

            val target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    binding.picDetailsProgressBar.visibility = View.GONE
                    binding.pictureImageView.setImageBitmap(bitmap)
                    pictureImageView.isEnabled = true
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
                    ErrorHandler.handleException(e, requireContext())
                    with(binding) {
                        setAsWallpaperButton.visibility = View.GONE
                        picDetailsProgressBar.visibility = View.GONE
                        errorImageView.visibility = View.VISIBLE
                        animateAppear(errorImageView)
                    }
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {
                    binding.picDetailsProgressBar.visibility = View.VISIBLE
                }
            }
            Picasso.get()
                .load(args.url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(target)
        }
    }

    private fun makeInfoToast() {
        Toast.makeText(requireContext(), R.string.wallpaper_success, Toast.LENGTH_SHORT).show()
    }
}
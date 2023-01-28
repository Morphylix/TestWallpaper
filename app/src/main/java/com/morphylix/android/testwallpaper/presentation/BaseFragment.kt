package com.morphylix.android.testwallpaper.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.morphylix.android.testwallpaper.R
import com.morphylix.android.testwallpaper.util.isInternetAvailable

open class BaseFragment<BINDING : ViewBinding>(
    val inflateFun: (inflater: LayoutInflater, container: ViewGroup?) -> BINDING
) : Fragment() {

    private var _binding: BINDING? = null
    val binding: BINDING
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateFun(inflater, container)
        val connected = isInternetAvailable(requireContext())
        if (!connected) {
            makeWarningToast()
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun makeWarningToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error, getString(R.string.no_connection)),
            Toast.LENGTH_LONG
        ).show()
    }
}
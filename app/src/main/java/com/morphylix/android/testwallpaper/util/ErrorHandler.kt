package com.morphylix.android.testwallpaper.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.morphylix.android.testwallpaper.R
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

private const val TAG = "ErrorHandler"

object ErrorHandler {


    fun handleException(e: Exception, context: Context): String {
        val error = when (e) {
            is HttpException -> {
                when (e.code()) {
                    404 -> context.getString(R.string.page_not_found)
                    in 500..599 -> context.getString(R.string.server_error)
                    else -> context.getString(R.string.something_went_wrong)
                }
            }
            is UnknownHostException -> {
                context.getString(R.string.no_connection)
            }
            is SocketException -> {
                context.getString(R.string.vpn)
            }
            else -> context.getString(R.string.something_went_wrong)
        }
        val errorString = context.getString(R.string.error, error)
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
        Log.i(TAG, "handling exception $errorString")
        return errorString
    }
}
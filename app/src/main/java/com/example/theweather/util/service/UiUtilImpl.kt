package com.example.theweather.util.service

import android.content.Context
import android.net.ConnectivityManager

class UiUtilImpl(private val context: Context) : UiUtil {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
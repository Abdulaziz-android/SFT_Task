package com.yakubjonov.domain.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHelper(private val context: Context) {

    @Suppress("UNREACHABLE_CODE")
    fun isNetworkConnected(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            result = when{
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> return false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when(type){
                        ConnectivityManager.TYPE_WIFI ->return true
                        ConnectivityManager.TYPE_MOBILE ->return true
                        ConnectivityManager.TYPE_ETHERNET ->return true
                        else -> return false
                    }
                }
            }
        }
        return result
    }

}
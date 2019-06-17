package com.remotearth.fake_coder.chatapp.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityUtil(private var applicationContext: Context) {

    val isConnectedToInternet: Boolean?
        get() {
            val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return manager.activeNetworkInfo != null && manager.activeNetworkInfo.isConnected
        }
}
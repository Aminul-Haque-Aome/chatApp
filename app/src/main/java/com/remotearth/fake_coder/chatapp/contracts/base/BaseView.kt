package com.remotearth.fake_coder.chatapp.contracts.base

import android.view.View

interface BaseView {
    fun isInternetAvailable(): Boolean?
    fun showToast(message: String)
    fun showSnackBar(message: String, parentLayout: View)
    fun showProgress(message: String)
    fun hideProgress()
}
package com.remotearth.fake_coder.chatapp.viewModels.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    private var isLoading = ObservableField<Boolean>()

    fun showLoader() {
        isLoading.set(true)
    }

    fun hideLoader() {
        isLoading.set(false)
    }
}
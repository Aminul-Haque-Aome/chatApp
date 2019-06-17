package com.remotearth.fake_coder.chatapp.callbacks

interface FireBaseStorageCallback {
    fun saveUrl(url: String)
    fun onImageUploadFailed(error: String)
    fun onImageSaveFailed(error: String)
}
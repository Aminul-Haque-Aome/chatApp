package com.remotearth.fake_coder.chatapp.services

import android.net.Uri
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseStorageCallback

interface FireBaseStorageService {
    fun uploadProfileImage(userId: String, imageUri: Uri, fireBaseStorageCallback: FireBaseStorageCallback)
}
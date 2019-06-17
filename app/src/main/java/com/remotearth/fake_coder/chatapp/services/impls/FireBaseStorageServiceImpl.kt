package com.remotearth.fake_coder.chatapp.services.impls

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseStorageCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseStorageService

class FireBaseStorageServiceImpl : FireBaseStorageService {
    private var storageReference: StorageReference? = FirebaseStorage.getInstance().reference

    override fun uploadProfileImage(userId: String, imageUri: Uri, fireBaseStorageCallback: FireBaseStorageCallback) {
        val ref = storageReference?.child("images/$userId.jpg")

        ref?.putFile(imageUri)
            ?.addOnSuccessListener {
                ref.downloadUrl
                    .addOnSuccessListener { fireBaseStorageCallback.saveUrl(it.toString()) }
                    .addOnFailureListener { fireBaseStorageCallback.onImageSaveFailed(it.message.toString()) }
            }?.addOnFailureListener { fireBaseStorageCallback.onImageUploadFailed(it.message.toString()) }
    }
}
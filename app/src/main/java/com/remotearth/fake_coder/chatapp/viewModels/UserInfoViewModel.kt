package com.remotearth.fake_coder.chatapp.viewModels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseStorageCallback
import com.remotearth.fake_coder.chatapp.contracts.UserInfoView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseStorageService
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class UserInfoViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val fireBaseStorageService: FireBaseStorageService,
    private val userInfoView: UserInfoView
) : BaseViewModel() {

    var currentUser: MutableLiveData<User> = MutableLiveData()

    fun getLoggedInUserInfo() {
        if (userInfoView.isInternetAvailable()!!) {
            getUserInfo()
        } else {
            userInfoView.showToast("Please Check Your Internet")
        }
    }

    private fun getUserInfo() {
        showLoader()

        fireBaseRealTimeDataBaseService.retrieveUser(fireBaseAuthService.getFireBaseUser()?.uid!!, object: FireBaseRealTimeDataBaseCallback.Retrieve {
            override fun onRetrieveSuccess(user: User) {
                hideLoader()
                currentUser.postValue(user)
            }

            override fun onRetrieveFailed(error: String) {
                hideLoader()
                userInfoView.showToast(error)
            }
        })
    }

    fun setProfilePicture(imageUri: Uri?) {
        fireBaseStorageService.uploadProfileImage(FirebaseAuth.getInstance().uid!!, imageUri!!, object: FireBaseStorageCallback {
            override fun saveUrl(url: String) {
                val hashMap = HashMap<String, String>()
                hashMap[Constant.USER_FIELD_IMAGE_URL] = url

                updateProfileImageUrl(hashMap)
            }

            override fun onImageUploadFailed(error: String) {
                userInfoView.hideUploadProgress()
                userInfoView.showToast(error)
            }

            override fun onImageSaveFailed(error: String) {
                userInfoView.hideUploadProgress()
                userInfoView.showToast(error)
            }
        })
    }

    private fun updateProfileImageUrl(hashMap: HashMap<String, String>) {
        fireBaseRealTimeDataBaseService.updateToken(fireBaseAuthService.getFireBaseUser()?.uid!!, hashMap, object: FireBaseRealTimeDataBaseCallback.Update {
            override fun onUpdateSuccess() {
                userInfoView.hideUploadProgress()
            }

            override fun onUpdateFailed(error: String) {
                userInfoView.hideUploadProgress()
                userInfoView.showToast(error)
            }
        })
    }

    fun selectProfilePictureFromGallery() {
        userInfoView.fetchImageFromGallery()
    }
}

package com.remotearth.fake_coder.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.contracts.UserInfoView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class UserInfoViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
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
}

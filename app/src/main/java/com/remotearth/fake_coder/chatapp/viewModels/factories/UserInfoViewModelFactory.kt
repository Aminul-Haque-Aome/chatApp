package com.remotearth.fake_coder.chatapp.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotearth.fake_coder.chatapp.contracts.UserInfoView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseStorageService
import com.remotearth.fake_coder.chatapp.viewModels.UserInfoViewModel

class UserInfoViewModelFactory(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val fireBaseStorageService: FireBaseStorageService,
    private val userInfoView: UserInfoView
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserInfoViewModel::class.java)) {
            return UserInfoViewModel(fireBaseAuthService, fireBaseRealTimeDataBaseService, fireBaseStorageService, userInfoView) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
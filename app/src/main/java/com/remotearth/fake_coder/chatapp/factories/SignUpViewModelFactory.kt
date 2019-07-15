package com.remotearth.fake_coder.chatapp.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.remotearth.fake_coder.chatapp.screens.fragments.signUp.SignUpView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.remotearth.fake_coder.chatapp.screens.fragments.signUp.SignUpViewModel

class SignUpViewModelFactory(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val fireBaseTokenService: FireBaseTokenService,
    private val signUpView: SignUpView
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(
                fireBaseAuthService,
                fireBaseRealTimeDataBaseService,
                fireBaseTokenService,
                signUpView
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
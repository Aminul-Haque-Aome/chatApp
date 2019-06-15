package com.remotearth.fake_coder.chatapp.viewModels

import com.remotearth.fake_coder.chatapp.contracts.LoginView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class LoginViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val loginView: LoginView
) : BaseViewModel() {

    fun navigateToSignUp() {
        loginView.navigateToSignUp()
    }

}

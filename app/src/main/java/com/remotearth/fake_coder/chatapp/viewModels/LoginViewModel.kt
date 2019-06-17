package com.remotearth.fake_coder.chatapp.viewModels

import com.remotearth.fake_coder.chatapp.Auth
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseTokenReceiveCallBack
import com.remotearth.fake_coder.chatapp.contracts.LoginView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.remotearth.fake_coder.chatapp.utils.StringUtil
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class LoginViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseTokenService: FireBaseTokenService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val loginView: LoginView
) : BaseViewModel() {

    var auth: Auth = Auth()

    fun navigateToSignUp() {
        loginView.navigateToSignUp()
    }

    fun login(auth: Auth) {
        if (loginView.isInternetAvailable()!!) {
            isUserInputAllFields(auth)
        } else {
            loginView.showToast("Check Internet Please")
        }
    }

    private fun isUserInputAllFields(auth: Auth) {
        if (StringUtil.isNullOrEmpty(auth.mail, auth.pass)) {
            loginView.showToast("Please give all info")
        } else {
            signIn(auth)
        }
    }

    private fun signIn(auth: Auth) {
        showLoader()

        fireBaseAuthService.login(auth, object: FireBaseAuthCallBack.Login {
            override fun onLoginSuccess() {
                generateNewToken()
            }

            override fun onLoginFailed(error: String) {
                hideLoader()
                loginView.showToast(error)
            }
        })
    }

    private fun generateNewToken() {
        fireBaseTokenService.generateToken(object: FireBaseTokenReceiveCallBack {
            override fun onTokenReceived(token: String?) {
                updateToken(token)
            }

            override fun onTokenReceivedFailed() {
                hideLoader()
                loginView.navigateToChatList()
            }
        })
    }

    private fun updateToken(token: String?) {
        fireBaseRealTimeDataBaseService.updateToken(fireBaseAuthService.getFireBaseUser()?.uid!!, token!!, object: FireBaseRealTimeDataBaseCallback.Update {
            override fun onUpdateSuccess() {
                hideLoader()
                loginView.navigateToChatList()
            }

            override fun onUpdateFailed(error: String) {
                hideLoader()
                loginView.showToast(error)
            }
        })
    }

}

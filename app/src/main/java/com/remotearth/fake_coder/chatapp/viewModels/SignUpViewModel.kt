package com.remotearth.fake_coder.chatapp.viewModels

import com.google.firebase.auth.FirebaseUser
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseTokenReceiveCallBack
import com.remotearth.fake_coder.chatapp.contracts.SignUpView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.services.FireBaseTokenService
import com.remotearth.fake_coder.chatapp.utils.StringUtil
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class SignUpViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val fireBaseTokenService: FireBaseTokenService,
    private val signUpView: SignUpView
) : BaseViewModel() {

    var user: User = User()

    fun signUp(user: User) {
        if (signUpView.isInternetAvailable()!!) {
            isUserInputAllFields(user)
        } else {
            signUpView.showToast("Check Internet Please")
        }
    }

    private fun isUserInputAllFields(user: User) {
        if (StringUtil.isNullOrEmpty(user.name, user.email, user.password)) {
            signUpView.showToast("Please give all info")
        } else {
            registerUser(user)
        }
    }

    private fun registerUser(user: User) {
        showLoader()

        fireBaseAuthService.signUp(user, object: FireBaseAuthCallBack.SignUp {
            override fun onSignUpSuccess(user: User) {
                getToken(user)
            }

            override fun onSignUpFailed(error: String) {
                hideLoader()
                signUpView.showToast(error)
            }
        })
    }

    private fun getToken(user: User) {
        fireBaseTokenService.generateToken(object: FireBaseTokenReceiveCallBack {
            override fun onTokenReceived(token: String) {
                user.token = token
                saveUserInfoToDataBase(user)
            }

            override fun onTokenReceivedFailed() {
                saveUserInfoToDataBase(user)
            }
        })
    }

    private fun saveUserInfoToDataBase(user: User) {
        fireBaseRealTimeDataBaseService.addUser(user, object: FireBaseRealTimeDataBaseCallback.Add {
            override fun onUserAddSuccess() {
                hideLoader()
                signUpView.navigateToChatList()
            }

            override fun onUserAddFailed() {
                deleteFireBaseAccount(fireBaseAuthService.getFireBaseUser())
            }
        })
    }

    private fun deleteFireBaseAccount(fireBaseUser: FirebaseUser?) {
        fireBaseAuthService.deleteAccount(fireBaseUser, object: FireBaseAuthCallBack.AccountDelete {
            override fun onDeleteFailed() {
                hideLoader()
                signUpView.showToast("Please Try Another Email to Sign In Again")
            }

            override fun onDeleteSuccess() {
                hideLoader()
                signUpView.showToast("Please Try to Sign In Again")
            }
        })
    }
}

package com.remotearth.fake_coder.chatapp.callbacks

import com.remotearth.fake_coder.chatapp.User

interface FireBaseAuthCallBack {

    interface Login {
        fun onLoginSuccess()
        fun onLoginFailed(error: String)
    }

    interface SignUp {
        fun onSignUpSuccess(user: User)
        fun onSignUpFailed(error: String)
    }

    interface AccountDelete {
        fun onDeleteSuccess()
        fun onDeleteFailed()
    }

}
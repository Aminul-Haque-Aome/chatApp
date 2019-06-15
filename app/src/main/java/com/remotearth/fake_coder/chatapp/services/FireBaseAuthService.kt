package com.remotearth.fake_coder.chatapp.services

import com.google.firebase.auth.FirebaseUser
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack

interface FireBaseAuthService {
    fun getFireBaseUser(): FirebaseUser?
    fun signUp(user: User, fireBaseAuthCallBack: FireBaseAuthCallBack.SignUp)
    fun login(email: String, password: String, fireBaseAuthCallBack: FireBaseAuthCallBack.Login)
    fun logout()
}
package com.remotearth.fake_coder.chatapp.services

import com.google.firebase.auth.FirebaseUser
import com.remotearth.fake_coder.chatapp.pojos.Auth
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack

interface FireBaseAuthService {
    fun getFireBaseUser(): FirebaseUser?
    fun signUp(user: User, fireBaseAuthCallBack: FireBaseAuthCallBack.SignUp)
    fun login(auth: Auth, fireBaseAuthCallBack: FireBaseAuthCallBack.Login)
    fun logout()
    fun deleteAccount(fireBaseUser: FirebaseUser?, fireBaseAuthCallBack: FireBaseAuthCallBack.AccountDelete)
}
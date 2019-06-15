package com.remotearth.fake_coder.chatapp.services.impls

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService

class FireBaseAuthServiceImpl : FireBaseAuthService {

    private var fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun getFireBaseUser(): FirebaseUser? {
        return fireBaseAuth.currentUser
    }

    override fun signUp(user: User, fireBaseAuthCallBack: FireBaseAuthCallBack.SignUp) {
        fireBaseAuth.createUserWithEmailAndPassword(user.email!!, user.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.id = fireBaseAuth.currentUser?.uid
                    fireBaseAuthCallBack.onSignUpSuccess(user)
                } else {
                    fireBaseAuthCallBack.onSignUpFailed(task.exception?.message.toString())
                }
            }
    }

    override fun login(email: String, password: String, fireBaseAuthCallBack: FireBaseAuthCallBack.Login) {
        fireBaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseAuthCallBack.onLoginSuccess()
                } else {
                    fireBaseAuthCallBack.onLoginFailed()
                }
            }
    }

    override fun logout() {
        fireBaseAuth.signOut()
    }
}

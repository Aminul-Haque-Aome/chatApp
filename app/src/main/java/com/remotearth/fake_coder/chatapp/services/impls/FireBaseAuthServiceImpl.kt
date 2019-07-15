package com.remotearth.fake_coder.chatapp.services.impls

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.remotearth.fake_coder.chatapp.pojos.Auth
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseAuthCallBack
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import timber.log.Timber

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
                    Timber.e(task.exception)
                    fireBaseAuthCallBack.onSignUpFailed(task.exception?.message.toString())
                }
            }
    }

    override fun login(auth: Auth, fireBaseAuthCallBack: FireBaseAuthCallBack.Login) {
        fireBaseAuth.signInWithEmailAndPassword(auth.mail!!, auth.pass!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseAuthCallBack.onLoginSuccess()
                } else {
                    Timber.e(task.exception)
                    fireBaseAuthCallBack.onLoginFailed(task.exception?.message.toString())
                }
            }
    }

    override fun logout() {
        fireBaseAuth.signOut()
    }

    override fun deleteAccount(fireBaseUser: FirebaseUser?, fireBaseAuthCallBack: FireBaseAuthCallBack.AccountDelete) {
        fireBaseUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                fireBaseAuthCallBack.onDeleteSuccess()
            } else {
                fireBaseAuthCallBack.onDeleteFailed()
            }
        }
    }
}

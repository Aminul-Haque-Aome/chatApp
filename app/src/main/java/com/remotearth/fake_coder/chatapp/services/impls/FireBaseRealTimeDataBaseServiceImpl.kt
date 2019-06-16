package com.remotearth.fake_coder.chatapp.services.impls

import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.google.firebase.database.FirebaseDatabase
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import timber.log.Timber

class FireBaseRealTimeDataBaseServiceImpl : FireBaseRealTimeDataBaseService {

    private var databaseReference = FirebaseDatabase.getInstance().reference

    override fun addUser(user: User, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Add) {
        databaseReference.child(Constant.USER_TABLE).child(user.id!!).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseRealTimeDataBaseCallback.onUserAddSuccess()
                } else {
                    Timber.e(task.exception)
                    fireBaseRealTimeDataBaseCallback.onUserAddFailed()
                }
            }
    }
}
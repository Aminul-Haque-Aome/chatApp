package com.remotearth.fake_coder.chatapp.services.impls

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import timber.log.Timber

class FireBaseRealTimeDataBaseServiceImpl : FireBaseRealTimeDataBaseService {
    private var databaseReference = FirebaseDatabase.getInstance().reference

    override fun addUser(user: User, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Add) {
        databaseReference
            .child(Constant.USER_TABLE)
            .child(user.id!!)
            .setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseRealTimeDataBaseCallback.onUserAddSuccess()
                } else {
                    Timber.e(task.exception)
                    fireBaseRealTimeDataBaseCallback.onUserAddFailed()
                }
            }
    }

    override fun retrieveUser(
        uid: String,
        fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Retrieve
    ) {
        databaseReference
            .child(Constant.USER_TABLE)
            .child(uid)
            .addValueEventListener(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.e(error.details)
                    fireBaseRealTimeDataBaseCallback.onRetrieveFailed(error.details)
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(User::class.java)
                    fireBaseRealTimeDataBaseCallback.onRetrieveSuccess(user!!)
                }
            })
    }

    override fun updateToken(
        userId: String,
        newToken: String,
        fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Update
    ) {
        val tokenMapper = HashMap<String, String>()
        tokenMapper["token"] = newToken

        databaseReference
            .child(Constant.USER_TABLE)
            .child(userId)
            .updateChildren(tokenMapper as Map<String, Any>)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseRealTimeDataBaseCallback.onUpdateSuccess()
                } else {
                    Timber.e(task.exception)
                    fireBaseRealTimeDataBaseCallback.onUpdateFailed(task.exception?.message.toString())
                }
            }
    }
}
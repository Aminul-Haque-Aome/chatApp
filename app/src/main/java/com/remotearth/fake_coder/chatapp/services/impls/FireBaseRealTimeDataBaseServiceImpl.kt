package com.remotearth.fake_coder.chatapp.services.impls

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.remotearth.fake_coder.chatapp.Message
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

    override fun retrieveUser(uid: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Retrieve) {
        databaseReference
            .child(Constant.USER_TABLE)
            .child(uid)
            .addValueEventListener(object : ValueEventListener {
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

    override fun retrieveAllUsers(fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval) {
        databaseReference
            .child(Constant.USER_TABLE)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userList: MutableList<User> = ArrayList()

                    for (userSnapShort in dataSnapshot.children) {
                        val user = userSnapShort.getValue(User::class.java)
                        user?.let { userList.add(it) }
                    }

                    fireBaseRealTimeDataBaseCallback.onRetrieveSuccess(userList)
                }

                override fun onCancelled(error: DatabaseError) {
                    fireBaseRealTimeDataBaseCallback.onRetrieveFailed(error.details)
                }
            })
    }

    override fun updateUserField(userId: String, fieldMapping: Map<String, String>, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Update) {
        databaseReference
            .child(Constant.USER_TABLE)
            .child(userId)
            .updateChildren(fieldMapping)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fireBaseRealTimeDataBaseCallback.onUpdateSuccess()
                } else {
                    Timber.e(task.exception)
                    fireBaseRealTimeDataBaseCallback.onUpdateFailed(task.exception?.message.toString())
                }
            }
    }



    override fun isThreadExist(senderId: String, receiverId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.ThreadExistence) {
        databaseReference
            .child(Constant.THREAD_TABLE)
            .child(senderId)
            .child(receiverId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapShot: DataSnapshot) {
                    if (dataSnapShot.exists()) {
                        fireBaseRealTimeDataBaseCallback.onThreadExist(true)
                    } else {
                        fireBaseRealTimeDataBaseCallback.onThreadExist(false)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e(databaseError.toException())
                }
            })
    }

    override fun createThreadTable(senderId: String, receiverId: String) {
        getThread(receiverId, senderId, object: FireBaseRealTimeDataBaseCallback.ThreadRetrieval {
            override fun onRetrieveSuccess(thread: String) {
                databaseReference
                    .child(Constant.THREAD_TABLE)
                    .child(senderId)
                    .child(receiverId)
                    .setValue(thread)
            }

            override fun onRetrieveFailed(error: String) {}

            override fun threadNotExistListener() {
                val threadName = databaseReference.child(Constant.THREAD_TABLE).child(senderId).child(receiverId).push().key
                databaseReference
                    .child(Constant.THREAD_TABLE)
                    .child(senderId)
                    .child(receiverId)
                    .setValue(threadName)
            }
        })
    }

    override fun getThread(senderId: String, receiverId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.ThreadRetrieval) {
        databaseReference
            .child(Constant.THREAD_TABLE)
            .child(senderId)
            .child(receiverId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapShot: DataSnapshot) {
                    if (dataSnapShot.exists()) {
                        fireBaseRealTimeDataBaseCallback.onRetrieveSuccess(dataSnapShot.getValue(String::class.java)!!)
                    } else {
                        fireBaseRealTimeDataBaseCallback.threadNotExistListener()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e(databaseError.toException())
                    fireBaseRealTimeDataBaseCallback.onRetrieveFailed(databaseError.details)
                }
            })
    }


    override fun sendMessage(message: Message, threadName: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.MessageSent) {
        val pushKey = databaseReference.child(Constant.CHAT_TABLE).child(threadName).push().key

        databaseReference
            .child(Constant.CHAT_TABLE)
            .child(threadName)
            .child(pushKey!!)
            .setValue(message)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    fireBaseRealTimeDataBaseCallback.onMessageSentSuccess()
                } else {
                    fireBaseRealTimeDataBaseCallback.onMessageSentFailed()
                }
            }
    }

}
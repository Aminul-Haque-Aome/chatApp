package com.remotearth.fake_coder.chatapp.services

import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback

interface FireBaseRealTimeDataBaseService {
    fun addUser(user: User, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Add)
    fun retrieveUser(uid: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Retrieve)
    fun retrieveAllUsers(fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval)
    fun updateUserField(userId: String, fieldMapping: Map<String, String>, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Update)
}
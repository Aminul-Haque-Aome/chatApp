package com.remotearth.fake_coder.chatapp.services

import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback

interface FireBaseRealTimeDataBaseService {
    fun addUser(user: User, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Add)
    fun retrieveUser(uid: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Retrieve)
    fun retrieveAllUsers(fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval)
    fun retrieveAllUsers(count: Int, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval)
    fun retrieveAllUsersAfter(key: String, count: Int, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval)
    fun retrieveAllUsersBefore(key: String, count: Int, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UserListRetrieval)
    fun updateUserField(userId: String, fieldMapping: Map<String, String>, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.Update)

    fun isThreadExist(senderId: String, receiverId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.ThreadExistence)
    fun createThreadTable(senderId: String, receiverId: String)
    fun getThread(senderId: String, receiverId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.ThreadRetrieval)

    fun sendMessage(message: Message, threadName: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.MessageSent)
    fun loadAllMessageOfSpecificThread(threadName: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.GetAllMessage)
    fun updateMessageSeenStatus(threadName: String, userId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.UpdateSeenStatus)

    fun modifyTypingStatus(threadName: String, userId: String, status: Boolean)
    fun checkIfUserIsTypingOrNot(threadName: String, userId: String, fireBaseRealTimeDataBaseCallback: FireBaseRealTimeDataBaseCallback.TypingStatus)
}
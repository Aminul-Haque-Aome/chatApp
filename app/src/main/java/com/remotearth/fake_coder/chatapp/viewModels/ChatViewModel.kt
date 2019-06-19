package com.remotearth.fake_coder.chatapp.viewModels

import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.contracts.ChatView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class ChatViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val chatView: ChatView
) : BaseViewModel() {

    var chatThreadName: String? = null

    fun getSenderId(): String {
        return fireBaseAuthService.getFireBaseUser()?.uid!!
    }

    fun isThreadExist(senderId: String, user: User) {
        fireBaseRealTimeDataBaseService.isThreadExist(senderId, user.id!!, object: FireBaseRealTimeDataBaseCallback.ThreadExistence {
            override fun onThreadExist(isExist: Boolean) {
                if (!isExist) {
                    chatView.createChatThread(user)
                }
                getThread(senderId, user.id!!)
            }
        })
    }

    fun createThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.createThreadTable(senderId, receiverId)
    }

    private fun getThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.getThread(senderId, receiverId, object: FireBaseRealTimeDataBaseCallback.ThreadRetrieval {
            override fun threadNotExistListener() {}

            override fun onRetrieveSuccess(thread: String) {
                chatThreadName = thread
                chatView.showToast(chatThreadName!!)
            }

            override fun onRetrieveFailed(error: String) {
                chatView.showToast(error)
            }
        })
    }

}

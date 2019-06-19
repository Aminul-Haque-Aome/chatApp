package com.remotearth.fake_coder.chatapp.viewModels

import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.callbacks.FireBaseRealTimeDataBaseCallback
import com.remotearth.fake_coder.chatapp.contracts.ChatView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.utils.DateTimeUtil
import com.remotearth.fake_coder.chatapp.utils.StringUtil
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class ChatViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val chatView: ChatView
) : BaseViewModel() {

    var chatThreadName: String? = null
    var message: Message = Message(userId = getSenderId(), isSeen = false)

    fun isThreadExist(senderId: String, user: User) {
        fireBaseRealTimeDataBaseService.isThreadExist(senderId, user.id!!, object: FireBaseRealTimeDataBaseCallback.ThreadExistence {
            override fun onThreadExist(isExist: Boolean) {
                if (!isExist) {
                    chatView.createThread(user)
                }
                getThread(senderId, user.id!!)
            }
        })
    }

    fun getSenderId(): String {
        return fireBaseAuthService.getFireBaseUser()?.uid!!
    }

    fun createThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.createThreadTable(senderId, receiverId)
    }

    private fun getThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.getThread(senderId, receiverId, object: FireBaseRealTimeDataBaseCallback.ThreadRetrieval {
            override fun threadNotExistListener() {}

            override fun onRetrieveSuccess(thread: String) {
                chatThreadName = thread
            }

            override fun onRetrieveFailed(error: String) {
                chatView.showToast(error)
            }
        })
    }

    fun sendMessage(message: Message) {
        message.timestamp = DateTimeUtil.getTimeStamp()

        if (chatView.isInternetAvailable()!!) {
            isUserInputAllFields(message)
        } else {
            chatView.showToast("Please Connect to Internet")
        }
    }

    private fun isUserInputAllFields(message: Message) {
        if (StringUtil.isNullOrEmpty(message.text)) {
            chatView.showToast("Please write a message")
        } else {
            send(message)
        }
    }

    private fun send(message: Message) {
        fireBaseRealTimeDataBaseService.sendMessage(message, this.chatThreadName!!, object: FireBaseRealTimeDataBaseCallback.MessageSent {
            override fun onMessageSentSuccess() {
                chatView.clearTextFieldAndRefreshData()
            }

            override fun onMessageSentFailed() {
                chatView.showToast("Message Sent Failed")
            }
        })
    }

}

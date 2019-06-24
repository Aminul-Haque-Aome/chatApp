package com.remotearth.fake_coder.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
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
    var messageList: MutableLiveData<List<Message>> = MutableLiveData()

    fun isThreadExist(receiverId: String) {
        if (chatView.isInternetAvailable()!!) {
            checkThreadExistOrNot(getSenderId(), receiverId)
        } else {
            chatView.showToast("Please connect to Internet")
        }
    }

    private fun checkThreadExistOrNot(senderId: String, receiverId: String) {
        showLoader()

        fireBaseRealTimeDataBaseService.isThreadExist(
            senderId,
            receiverId,
            object : FireBaseRealTimeDataBaseCallback.ThreadExistence {
                override fun onThreadExist(isExist: Boolean) {
                    if (!isExist) {
                        createThread(getSenderId(), receiverId)
                    }
                    getThread(getSenderId(), receiverId)
                }
            })
    }

    private fun createThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.createThreadTable(senderId, receiverId)
    }

    fun getSenderId(): String {
        return fireBaseAuthService.getFireBaseUser()?.uid!!
    }

    private fun getThread(senderId: String, receiverId: String) {
        fireBaseRealTimeDataBaseService.getThread(
            senderId,
            receiverId,
            object : FireBaseRealTimeDataBaseCallback.ThreadRetrieval {
                override fun threadNotExistListener() {}

                override fun onRetrieveSuccess(threadName: String) {
                    chatThreadName = threadName
                    updatePreviousMessageSeenStatus()
                    getAllMessageOfTheThread()
                    checkReceiverTypingStatus(chatThreadName!!, receiverId)
                }

                override fun onRetrieveFailed(error: String) {
                    hideLoader()
                    chatView.showToast(error)
                }
            })
    }

    fun updatePreviousMessageSeenStatus() {
        if (chatView.isInternetAvailable()!!) {
            fireBaseRealTimeDataBaseService.updateMessageSeenStatus(
                chatThreadName!!,
                getSenderId(),
                object : FireBaseRealTimeDataBaseCallback.UpdateSeenStatus {
                    override fun onUpdateFailed(messages: String) {
                        chatView.showToast(messages)
                    }
                })
        } else {
            chatView.showToast("Please connect to Internet")
        }
    }

    private fun getAllMessageOfTheThread() {
        fireBaseRealTimeDataBaseService.loadAllMessageOfSpecificThread(
            chatThreadName!!,
            object : FireBaseRealTimeDataBaseCallback.GetAllMessage {
                override fun onRetrieveSuccess(messages: ArrayList<Message>) {
                    hideLoader()
                    messageList.value = ArrayList(messages.reversed())
                }

                override fun onRetrieveFailed(messages: String) {
                    hideLoader()
                    chatView.showToast(messages)
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
        fireBaseRealTimeDataBaseService.sendMessage(
            message,
            this.chatThreadName!!,
            object : FireBaseRealTimeDataBaseCallback.MessageSent {
                override fun onMessageSentSuccess() {
                    chatView.clearTextFieldAndRefreshData()
                }

                override fun onMessageSentFailed() {
                    chatView.showToast("Message Sent Failed")
                }
            })
    }

    fun changeUserTypingStatus(isTyping: Boolean) {
        if (chatView.isInternetAvailable()!!) {
            chatThreadName?.let {
                fireBaseRealTimeDataBaseService.modifyTypingStatus(it, getSenderId(), isTyping)
            }
        }
    }

    private fun checkReceiverTypingStatus(threadName: String, userId: String) {
        if (chatView.isInternetAvailable()!!) {
            fireBaseRealTimeDataBaseService.checkIfUserIsTypingOrNot(
                threadName,
                userId,
                object : FireBaseRealTimeDataBaseCallback.TypingStatus {
                    override fun onRetrieveSuccess(isTyping: Boolean) {
                        if (isTyping) {
                            chatView.showTypingIndicator()
                        } else {
                            chatView.hideTypingIndicator()
                        }
                    }
                })
        }
    }

    fun navigateBack() {
        chatView.navigateBackToChatList()
    }
}

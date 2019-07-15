package com.remotearth.fake_coder.chatapp.callbacks

import com.remotearth.fake_coder.chatapp.pojos.Message
import com.remotearth.fake_coder.chatapp.pojos.User

interface FireBaseRealTimeDataBaseCallback {

    interface Add {
        fun onUserAddSuccess()
        fun onUserAddFailed()
    }

    interface Update {
        fun onUpdateSuccess()
        fun onUpdateFailed(error: String)
    }

    interface Retrieve {
        fun onRetrieveSuccess(user: User)
        fun onRetrieveFailed(error: String)
    }

    interface UserListRetrieval {
        fun onRetrieveSuccess(users: List<User>)
        fun onRetrieveFailed(error: String)
    }

    interface ThreadRetrieval {
        fun onRetrieveSuccess(threadName: String)
        fun onRetrieveFailed(error: String)
        fun threadNotExistListener()
    }

    interface ThreadExistence {
        fun onThreadExist(isExist: Boolean)
    }

    interface MessageSent {
        fun onMessageSentSuccess()
        fun onMessageSentFailed()
    }

    interface GetAllMessage {
        fun onRetrieveSuccess(messages: ArrayList<Message>)
        fun onRetrieveFailed(messages: String)
    }

    interface UpdateSeenStatus {
        fun onUpdateFailed(messages: String)
    }

    interface TypingStatus {
        fun onRetrieveSuccess(isTyping: Boolean)
    }
}
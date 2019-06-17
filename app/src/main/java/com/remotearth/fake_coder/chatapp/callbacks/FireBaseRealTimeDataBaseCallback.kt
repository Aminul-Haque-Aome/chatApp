package com.remotearth.fake_coder.chatapp.callbacks

import com.remotearth.fake_coder.chatapp.User

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
}
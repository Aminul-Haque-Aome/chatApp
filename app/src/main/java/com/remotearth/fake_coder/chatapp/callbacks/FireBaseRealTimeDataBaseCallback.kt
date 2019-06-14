package com.remotearth.fake_coder.chatapp.callbacks

interface FireBaseRealTimeDataBaseCallback {

    interface Add {
        fun onUserAddSuccess()
        fun onUserAddFailed()
    }
}
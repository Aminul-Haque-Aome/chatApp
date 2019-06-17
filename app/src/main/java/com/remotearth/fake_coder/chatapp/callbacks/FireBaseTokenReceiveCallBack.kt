package com.remotearth.fake_coder.chatapp.callbacks

interface FireBaseTokenReceiveCallBack {
    fun onTokenReceived(token: String)
    fun onTokenReceivedFailed()
}
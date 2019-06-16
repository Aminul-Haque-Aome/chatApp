package com.remotearth.fake_coder.chatapp.services

import com.remotearth.fake_coder.chatapp.callbacks.FireBaseTokenReceiveCallBack

interface FireBaseTokenService {
    fun generateToken(fireBaseTokenReceiveCallBack: FireBaseTokenReceiveCallBack)
}
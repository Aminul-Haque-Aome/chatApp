package com.remotearth.fake_coder.chatapp.contracts

import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.contracts.base.BaseView

interface ChatView: BaseView {
    fun createChatThread(user: User)
}
package com.remotearth.fake_coder.chatapp.contracts

import com.remotearth.fake_coder.chatapp.contracts.base.BaseView

interface LoginView: BaseView {
    fun navigateToSignUp()
    fun navigateToChatList()
}
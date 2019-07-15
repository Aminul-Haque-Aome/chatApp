package com.remotearth.fake_coder.chatapp.screens.fragments.login

import com.remotearth.fake_coder.chatapp.base.BaseView

interface LoginView: BaseView {
    fun navigateToSignUp()
    fun navigateToChatList()
}
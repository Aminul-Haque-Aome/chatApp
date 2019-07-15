package com.remotearth.fake_coder.chatapp.screens.fragments.chat

import com.remotearth.fake_coder.chatapp.base.BaseView

interface ChatView: BaseView {
    fun clearTextFieldAndRefreshData()
    fun showTypingIndicator()
    fun hideTypingIndicator()
    fun navigateBackToChatList()
}
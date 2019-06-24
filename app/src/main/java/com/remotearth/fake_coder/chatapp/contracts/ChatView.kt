package com.remotearth.fake_coder.chatapp.contracts

import com.remotearth.fake_coder.chatapp.contracts.base.BaseView

interface ChatView: BaseView {
    fun clearTextFieldAndRefreshData()
    fun showTypingIndicator()
    fun hideTypingIndicator()
    fun navigateBackToChatList()
}
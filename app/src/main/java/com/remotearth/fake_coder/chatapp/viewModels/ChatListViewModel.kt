package com.remotearth.fake_coder.chatapp.viewModels

import com.remotearth.fake_coder.chatapp.contracts.ChatListView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.viewModels.base.BaseViewModel

class ChatListViewModel(
    private val fireBaseAuthService: FireBaseAuthService,
    private val chatListView: ChatListView
) : BaseViewModel() {

    fun checkUserStatus() {
        if (fireBaseAuthService.getFireBaseUser() == null) {
            chatListView.navigateToLogin()
        }
    }

    fun showProfile() {
        chatListView.navigateToUserInfo()
    }

}

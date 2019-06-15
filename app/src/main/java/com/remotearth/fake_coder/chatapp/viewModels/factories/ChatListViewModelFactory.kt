package com.remotearth.fake_coder.chatapp.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotearth.fake_coder.chatapp.contracts.ChatListView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.viewModels.ChatListViewModel

class ChatListViewModelFactory(
    private val fireBaseAuthService: FireBaseAuthService,
    private val chatListView: ChatListView
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
            return ChatListViewModel(fireBaseAuthService, chatListView) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
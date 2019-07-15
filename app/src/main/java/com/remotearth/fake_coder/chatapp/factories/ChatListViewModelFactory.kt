package com.remotearth.fake_coder.chatapp.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotearth.fake_coder.chatapp.screens.fragments.chatList.ChatListView
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.FireBaseRealTimeDataBaseService
import com.remotearth.fake_coder.chatapp.screens.fragments.chatList.ChatListViewModel

class ChatListViewModelFactory(
    private val fireBaseAuthService: FireBaseAuthService,
    private val fireBaseRealTimeDataBaseService: FireBaseRealTimeDataBaseService,
    private val chatListView: ChatListView
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
            return ChatListViewModel(
                fireBaseAuthService,
                fireBaseRealTimeDataBaseService,
                chatListView
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
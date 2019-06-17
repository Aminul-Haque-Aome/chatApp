package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.contracts.ChatListView
import com.remotearth.fake_coder.chatapp.databinding.ChatListFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.FireBaseAuthService
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.viewModels.ChatListViewModel
import com.remotearth.fake_coder.chatapp.viewModels.factories.ChatListViewModelFactory

class ChatListFragment : BaseFragment(), ChatListView {

    private lateinit var viewModel: ChatListViewModel
    private lateinit var chatListFragmentBinding: ChatListFragmentBinding

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.chat_list_fragment,
            container,
            false
        )
        return chatListFragmentBinding.root
    }

    override fun initWidget() {}

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ChatListViewModelFactory(
                FireBaseAuthServiceImpl(),
                this
            )
        ).get(ChatListViewModel::class.java)

        chatListFragmentBinding.chatListViewModel = viewModel
    }

    override fun bundleCommunication() {}

    override fun onStart() {
        super.onStart()
        viewModel.checkUserStatus()
    }

    override fun navigateToLogin() {
        navigateTo(R.id.chatList_to_login)
    }

    override fun navigateToUserInfo() {
        navigateTo(R.id.chatList_to_userInfo)
    }

}

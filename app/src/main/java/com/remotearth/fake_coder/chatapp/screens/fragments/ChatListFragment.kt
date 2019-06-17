package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.adapters.UserListAdapter
import com.remotearth.fake_coder.chatapp.contracts.ChatListView
import com.remotearth.fake_coder.chatapp.databinding.ChatListFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.viewModels.ChatListViewModel
import com.remotearth.fake_coder.chatapp.viewModels.factories.ChatListViewModelFactory
import kotlinx.android.synthetic.main.chat_list_fragment.*

class ChatListFragment : BaseFragment(), ChatListView {

    private lateinit var viewModel: ChatListViewModel
    private lateinit var chatListFragmentBinding: ChatListFragmentBinding
    private val userListAdapter = UserListAdapter()

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.chat_list_fragment,
            container,
            false
        )
        return chatListFragmentBinding.root
    }

    override fun initWidget() {
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
        userListRecyclerView.adapter = userListAdapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ChatListViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(ChatListViewModel::class.java)

        chatListFragmentBinding.chatListViewModel = viewModel
        viewModel.userList.observe(this, Observer { userListAdapter.replaceData(it as ArrayList<User>) })
    }

    override fun bundleCommunication() {}

    override fun onStart() {
        super.onStart()
        viewModel.checkUserStatus()
        viewModel.loadAllUsers()
    }

    override fun navigateToLogin() {
        navigateTo(R.id.chatList_to_login)
    }

    override fun navigateToUserInfo() {
        navigateTo(R.id.chatList_to_userInfo)
    }

}

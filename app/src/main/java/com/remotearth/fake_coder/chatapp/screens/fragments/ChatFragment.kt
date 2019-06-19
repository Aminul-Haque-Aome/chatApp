package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.contracts.ChatView
import com.remotearth.fake_coder.chatapp.databinding.ChatFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.viewModels.ChatViewModel
import com.remotearth.fake_coder.chatapp.viewModels.factories.ChatViewModelFactory

class ChatFragment : BaseFragment(), ChatView {

    private lateinit var viewModel: ChatViewModel
    private lateinit var chatFragmentBinding: ChatFragmentBinding

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.chat_fragment,
            container,
            false
        )
        return chatFragmentBinding.root
    }

    override fun initWidget() {}

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ChatViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(ChatViewModel::class.java)
    }

    override fun bundleCommunication() {
        val receiver = arguments?.getParcelable<User>(Constant.BUNDLE_USER)
        viewModel.isThreadExist(viewModel.getSenderId(), receiver!!)
    }

    override fun createChatThread(user: User) {
        viewModel.createThread(viewModel.getSenderId(), user.id!!)
    }

}

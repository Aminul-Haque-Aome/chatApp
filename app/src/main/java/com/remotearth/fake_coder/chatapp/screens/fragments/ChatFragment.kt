package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.databinding.ChatFragmentBinding
import com.remotearth.fake_coder.chatapp.databinding.UserInfoFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.viewModels.ChatViewModel

class ChatFragment : BaseFragment() {

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
            this
        ).get(ChatViewModel::class.java)
    }

    override fun bundleCommunication() {
        val user = arguments?.getParcelable<User>(Constant.BUNDLE_USER)
        showToast(user?.email!!)
    }

}

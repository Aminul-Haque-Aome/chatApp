package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.viewModels.ChatViewModel

class ChatFragment : BaseFragment() {

    private lateinit var viewModel: ChatViewModel

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.chat_fragment, container, false)
    }

    override fun initWidget() {

    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this
        ).get(ChatViewModel::class.java)
    }

    override fun bundleCommunication() {

    }

}

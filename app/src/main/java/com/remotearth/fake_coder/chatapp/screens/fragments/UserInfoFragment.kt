package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.viewModels.UserInfoViewModel

class UserInfoFragment : BaseFragment() {

    private lateinit var viewModel: UserInfoViewModel

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override fun initWidget() {}

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
    }

    override fun bundleCommunication() {}

}

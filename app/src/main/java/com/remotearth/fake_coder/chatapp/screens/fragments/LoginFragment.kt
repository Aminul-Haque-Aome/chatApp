package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.remotearth.fake_coder.chatapp.viewModels.LoginViewModel
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.databinding.LoginFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val loginFragmentBinding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        return loginFragmentBinding.root
    }

    override fun initialization() {

    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun bundleCommunication() {

    }

}

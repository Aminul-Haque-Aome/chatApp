package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.remotearth.fake_coder.chatapp.viewModels.LoginViewModel
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.contracts.LoginView
import com.remotearth.fake_coder.chatapp.databinding.LoginFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.viewModels.factories.LoginViewModelFactory

class LoginFragment : BaseFragment(), LoginView {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginFragmentBinding: LoginFragmentBinding

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        return loginFragmentBinding.root
    }

    override fun initWidget() {

    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(
                FireBaseAuthServiceImpl(),
                this
            )
        ).get(LoginViewModel::class.java)

        loginFragmentBinding.loginViewModel = viewModel
    }

    override fun bundleCommunication() {

    }

    override fun navigateToSignUp() {
        navigateTo(R.id.login_to_signUp)
    }

    override fun navigateToChatList() {

    }

}

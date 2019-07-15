package com.remotearth.fake_coder.chatapp.screens.fragments.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.databinding.LoginFragmentBinding
import com.remotearth.fake_coder.chatapp.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseTokenServiceImpl
import com.remotearth.fake_coder.chatapp.factories.LoginViewModelFactory

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
                FireBaseTokenServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(LoginViewModel::class.java)

        loginFragmentBinding.loginViewModel = viewModel
        loginFragmentBinding.auth = viewModel.auth
    }

    override fun bundleCommunication() {

    }

    override fun navigateToSignUp() {
        navigateTo(R.id.login_to_signUp)
    }

    override fun navigateToChatList() {
        navigateTo(R.id.login_to_chatList)
    }

}

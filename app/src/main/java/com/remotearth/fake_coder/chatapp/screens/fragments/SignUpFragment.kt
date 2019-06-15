package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.contracts.SignUpView
import com.remotearth.fake_coder.chatapp.databinding.SignUpFragmentBinding
import com.remotearth.fake_coder.chatapp.viewModels.SignUpViewModel
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.viewModels.factories.SignUpViewModelFactory

class SignUpFragment : BaseFragment(), SignUpView {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var signUpFragmentBinding: SignUpFragmentBinding

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        signUpFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.sign_up_fragment,
            container,
            false
        )
        return signUpFragmentBinding.root
    }

    override fun initWidget() {}

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            SignUpViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(SignUpViewModel::class.java)

        signUpFragmentBinding.signUpViewModel = viewModel
        signUpFragmentBinding.user = viewModel.user
    }

    override fun bundleCommunication() {}

    override fun navigateToChatList() {
        navigateTo(R.id.signUp_to_chatList)
    }

}

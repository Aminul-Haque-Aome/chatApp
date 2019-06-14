package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.viewModels.SignUpViewModel
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment

class SignUpFragment : BaseFragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun initialization() {

    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun bundleCommunication() {

    }

}

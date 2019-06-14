package com.remotearth.fake_coder.chatapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


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

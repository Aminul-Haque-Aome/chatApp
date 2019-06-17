package com.remotearth.fake_coder.chatapp.screens.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.contracts.UserInfoView
import com.remotearth.fake_coder.chatapp.databinding.UserInfoFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.viewModels.UserInfoViewModel
import com.remotearth.fake_coder.chatapp.viewModels.factories.UserInfoViewModelFactory

class UserInfoFragment : BaseFragment(), UserInfoView {

    private lateinit var viewModel: UserInfoViewModel
    private lateinit var userInfoFragmentBinding: UserInfoFragmentBinding

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userInfoFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_info_fragment,
            container,
            false
        )
        return userInfoFragmentBinding.root
    }

    override fun initWidget() {}

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            UserInfoViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(UserInfoViewModel::class.java)

        viewModel.currentUser.observe(this, Observer { userInfoFragmentBinding.user = it })
        userInfoFragmentBinding.userInfoViewModel = viewModel
    }

    override fun bundleCommunication() {}

    override fun onStart() {
        super.onStart()
        viewModel.getLoggedInUserInfo()
    }

    override fun fetchImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constant.PICK_IMAGE_FROM_GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            Constant.PICK_IMAGE_FROM_GALLERY_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    if (data != null && data.data != null) {
                        viewModel.setProfilePicture(data.data)
                    }
                } else {
                    showToast("You don't choose any Image")
                }
            }

        }
    }

}

package com.remotearth.fake_coder.chatapp.screens.fragments.userInfo

import com.remotearth.fake_coder.chatapp.base.BaseView

interface UserInfoView: BaseView {
    fun fetchImageFromGallery()
    fun showUploadProgress()
    fun hideUploadProgress()
}
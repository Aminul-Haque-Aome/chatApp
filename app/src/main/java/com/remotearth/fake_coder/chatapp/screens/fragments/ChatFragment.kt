package com.remotearth.fake_coder.chatapp.screens.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.remotearth.fake_coder.chatapp.Message

import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.adapters.ChatAdapter
import com.remotearth.fake_coder.chatapp.contracts.ChatView
import com.remotearth.fake_coder.chatapp.databinding.ChatFragmentBinding
import com.remotearth.fake_coder.chatapp.screens.fragments.base.BaseFragment
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseAuthServiceImpl
import com.remotearth.fake_coder.chatapp.services.impls.FireBaseRealTimeDataBaseServiceImpl
import com.remotearth.fake_coder.chatapp.utils.config.Constant
import com.remotearth.fake_coder.chatapp.viewModels.ChatViewModel
import com.remotearth.fake_coder.chatapp.viewModels.factories.ChatViewModelFactory
import kotlinx.android.synthetic.main.chat_fragment.*

class ChatFragment : BaseFragment(), ChatView {

    private lateinit var viewModel: ChatViewModel
    private lateinit var chatFragmentBinding: ChatFragmentBinding

    private lateinit var chatAdapter: ChatAdapter

    override fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chatFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.chat_fragment,
            container,
            false
        )
        return chatFragmentBinding.root
    }

    override fun initWidget() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.reverseLayout = true
        messageRecyclerView.layoutManager = linearLayoutManager

        chatAdapter = ChatAdapter(FirebaseAuth.getInstance().currentUser?.uid!!)
        messageRecyclerView.adapter = chatAdapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ChatViewModelFactory(
                FireBaseAuthServiceImpl(),
                FireBaseRealTimeDataBaseServiceImpl(),
                this
            )
        ).get(ChatViewModel::class.java)

        chatFragmentBinding.chatViewModel = viewModel
        chatFragmentBinding.message = viewModel.message

        viewModel.messageList.observe(this, Observer { chatAdapter.replaceData(it as ArrayList<Message>) })
    }

    override fun bundleCommunication() {
        val receiver = arguments?.getParcelable<User>(Constant.BUNDLE_USER)
        viewModel.isThreadExist(receiver?.id!!)
    }

    override fun clearTextFieldAndRefreshData() {
        viewModel.updatePreviousMessageSeenStatus()
        chatAdapter.notifyDataSetChanged()
        textMessage.setText("")
    }

}

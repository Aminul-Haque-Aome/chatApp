package com.remotearth.fake_coder.chatapp.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.databinding.MessageReceiverLayoutBinding

class ReceivedViewHolder (private val bindingView: MessageReceiverLayoutBinding) : RecyclerView.ViewHolder(bindingView.root) {

    fun bind(message: Message, onClickListener: (Message) -> Unit) {
        bindingView.message = message
        bindingView.root.setOnClickListener { onClickListener(message) }
    }

}
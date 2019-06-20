package com.remotearth.fake_coder.chatapp.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.databinding.MessageSenderLayoutBinding

class SentViewHolder (private val bindingView: MessageSenderLayoutBinding) : RecyclerView.ViewHolder(bindingView.root) {

    fun bind(message: Message) {
        bindingView.message = message
    }

}
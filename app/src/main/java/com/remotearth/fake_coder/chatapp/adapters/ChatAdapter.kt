package com.remotearth.fake_coder.chatapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.ReceivedViewHolder
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.SentViewHolder
import com.remotearth.fake_coder.chatapp.databinding.MessageReceiverLayoutBinding
import com.remotearth.fake_coder.chatapp.databinding.MessageSenderLayoutBinding

class ChatAdapter(private val fireBaseUserId: String) : ListAdapter<Message, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private const val SENDER = 0
        private const val RECEIVER = 1

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message) = (oldItem.timestamp == newItem.timestamp)
            override fun areContentsTheSame(oldItem: Message, newItem: Message) = (oldItem == newItem)
        }
    }

    override fun getItemViewType(position: Int) = when {
        getItem(position).userId.equals(fireBaseUserId) -> SENDER
        else -> RECEIVER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            SENDER -> {
                val view = MessageSenderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SentViewHolder(view)
            }

            RECEIVER -> {
                val view = MessageReceiverLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ReceivedViewHolder(view)
            }

            else -> {
                val view = MessageSenderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SentViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SENDER -> {
                val sentViewHolder = holder as SentViewHolder
                sentViewHolder.bind(getItem(position))
            }

            RECEIVER -> {
                val receivedViewHolder = holder as ReceivedViewHolder
                receivedViewHolder.bind(getItem(position))
            }
        }
    }
}
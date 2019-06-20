package com.remotearth.fake_coder.chatapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.ReceivedViewHolder
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.SentViewHolder
import com.remotearth.fake_coder.chatapp.databinding.MessageReceiverLayoutBinding
import com.remotearth.fake_coder.chatapp.databinding.MessageSenderLayoutBinding
import timber.log.Timber

class ChatAdapter(private val fireBaseUserId: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: ArrayList<Message> = ArrayList()

    private companion object {
        const val SENDER = 0
        const val RECEIVER = 1
    }

    override fun getItemViewType(position: Int) = when {
        messages[position].userId.equals(fireBaseUserId) -> SENDER
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
                sentViewHolder.bind(messages[position])
            }

            RECEIVER -> {
                val receivedViewHolder = holder as ReceivedViewHolder
                receivedViewHolder.bind(messages[position])
            }
        }
    }

    override fun getItemCount() = messages.size

    fun replaceData(messages: ArrayList<Message>) {
        this.messages = messages
        notifyDataSetChanged()
    }
}
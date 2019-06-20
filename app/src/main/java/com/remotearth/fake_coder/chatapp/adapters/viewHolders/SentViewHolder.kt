package com.remotearth.fake_coder.chatapp.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.Message
import com.remotearth.fake_coder.chatapp.R
import com.remotearth.fake_coder.chatapp.databinding.MessageSenderLayoutBinding

class SentViewHolder (private val bindingView: MessageSenderLayoutBinding) : RecyclerView.ViewHolder(bindingView.root) {

    fun bind(message: Message) {
        bindingView.message = message
        bindingView.root.setOnClickListener { changeVisibilityStatus() }
    }

    private fun changeVisibilityStatus() {
        val time: TextView = bindingView.root.findViewById(R.id.timeStampTextView)
        val seenStatus: TextView = bindingView.root.findViewById(R.id.seenStatusTextView)

        if (time.visibility == View.GONE && seenStatus.visibility == View.GONE) {
            time.visibility = View.VISIBLE
            seenStatus.visibility = View.VISIBLE
        } else {
            time.visibility = View.GONE
            seenStatus.visibility = View.GONE
        }
    }

}
package com.remotearth.fake_coder.chatapp.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.databinding.UserItemBinding

class UserListViewHolder(private val bindingView: UserItemBinding) : RecyclerView.ViewHolder(bindingView.root) {

    fun bind(user: User, onClickListener: (User) -> Unit) {
        bindingView.user = user
        bindingView.root.setOnClickListener { onClickListener(user) }
    }

}
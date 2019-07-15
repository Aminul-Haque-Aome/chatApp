package com.remotearth.fake_coder.chatapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.remotearth.fake_coder.chatapp.pojos.User
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.UserListViewHolder
import com.remotearth.fake_coder.chatapp.databinding.UserItemBinding

class UserListAdapter(
    private val onClickListener: (User) -> Unit
): ListAdapter<User, UserListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: User, newItem: User) = (oldItem == newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onClickListener)
    }
}
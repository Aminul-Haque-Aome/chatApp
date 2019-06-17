package com.remotearth.fake_coder.chatapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remotearth.fake_coder.chatapp.User
import com.remotearth.fake_coder.chatapp.adapters.viewHolders.UserListViewHolder
import com.remotearth.fake_coder.chatapp.databinding.UserItemBinding

class UserListAdapter: RecyclerView.Adapter<UserListViewHolder>() {

    private var users: ArrayList<User>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(users?.get(position)!!)
    }

    fun replaceData(users: ArrayList<User>) {
        this.users = users
        notifyDataSetChanged()
    }
}
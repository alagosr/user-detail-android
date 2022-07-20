package com.flagos.userdetail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.flagos.userdetail.databinding.UserDetailItemBinding
import com.flagos.userdetail.domain.Results
import com.flagos.userdetail.presentation.adapter.viewholder.UserDetailViewHolder

class UserDetailAdapter : ListAdapter<Results, UserDetailViewHolder>(UserDetailDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserDetailViewHolder(UserDetailItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class UserDetailDiff() : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean = oldItem == newItem
    }
}

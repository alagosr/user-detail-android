package com.flagos.userdetail.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.flagos.userdetail.R
import com.flagos.userdetail.databinding.UserDetailItemBinding
import com.flagos.userdetail.domain.Results
import com.squareup.picasso.Picasso

class UserDetailViewHolder(private val binding: UserDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userDetail: Results) {
        with(binding) {
            textUserName.text = "${userDetail.name?.first} ${userDetail.name?.last}"
            textUserEmail.text = userDetail.email
            textUserLocation.text = "${userDetail.location?.city}, ${userDetail.location?.state}, ${userDetail.nat}"
            textUserPhoneNumber.text = userDetail.phone
            textUser.text = "@${userDetail.login?.username}"

            Picasso.get()
                .load(userDetail.picture?.medium)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageUser)
        }
    }
}
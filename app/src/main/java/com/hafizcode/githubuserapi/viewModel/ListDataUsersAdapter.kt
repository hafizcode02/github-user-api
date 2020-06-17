package com.hafizcode.githubuserapi.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizcode.githubuserapi.R
import com.hafizcode.githubuserapi.model.DataUsers
import kotlinx.android.synthetic.main.item_user.view.*

class ListDataUsersAdapter(private val listDataUsersGithub: ArrayList<DataUsers>) :
    RecyclerView.Adapter<ListDataUsersAdapter.ListDataHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(items: ArrayList<DataUsers>) {
        listDataUsersGithub.clear()
        listDataUsersGithub.addAll(items)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: DataUsers)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataUsers: DataUsers) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataUsers.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(avatar)

                fullName.text = dataUsers.name
                username.text = dataUsers.username
                count_repository.text = dataUsers.repository
                count_followers.text = dataUsers.followers
                count_following.text = dataUsers.following
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataUsersGithub.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataUsersGithub[position])
    }


}
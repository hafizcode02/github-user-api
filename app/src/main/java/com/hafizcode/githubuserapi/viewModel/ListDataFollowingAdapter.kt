package com.hafizcode.githubuserapi.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizcode.githubuserapi.R
import com.hafizcode.githubuserapi.model.DataFollowing
import kotlinx.android.synthetic.main.item_user.view.*

class ListDataFollowingAdapter(private val listDataFollowing: ArrayList<DataFollowing>) :
    RecyclerView.Adapter<ListDataFollowingAdapter.ListDataHolder>() {

    fun setData(item: ArrayList<DataFollowing>) {
        listDataFollowing.clear()
        listDataFollowing.addAll(item)
        notifyDataSetChanged()
    }

    class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataFollowing: DataFollowing) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(dataFollowing.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(avatar)

                fullName.text = dataFollowing.name
                username.text =
                    itemView.context.getString(R.string.hafizcode02, dataFollowing.username)
                count_repository.text =
                    itemView.context.getString(R.string._100_repository, dataFollowing.repository)
                count_followers.text =
                    itemView.context.getString(R.string.follower, dataFollowing.followers)
                count_following.text =
                    itemView.context.getString(R.string.follower, dataFollowing.following)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataHolder {
        return ListDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDataFollowing.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        holder.bind(listDataFollowing[position])
    }
}
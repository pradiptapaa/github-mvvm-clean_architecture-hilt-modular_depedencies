package id.pradiptapaa.github.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.pradiptapaa.github.R
import id.pradiptapaa.github.infrastructure.utils.loadFromUrl
import id.pradiptapaa.user.domain.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter : PagingDataAdapter<User, UserListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_user, parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(entity: User) {
            itemView.item_user_tv_id.text = entity.login
            itemView.item_user_iv_photo.loadFromUrl(entity.avatar_url!!)
        }
    }
}





package com.thiqah.postsapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thiqah.postsapp.R
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.databinding.PostItemAdapterBinding

class PostsAdapter(private val postsList: ArrayList<PostModel>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeCardViewBinding = DataBindingUtil.inflate<PostItemAdapterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_item_adapter, parent, false
        )
        return ViewHolder(homeCardViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        val post = postsList[position]

        binding.postItemVM = PostItemViewModel(post, this, position)
    }

    fun addItem(postModel: PostModel) {
        postsList.add(0, postModel)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    inner class ViewHolder(itemView: PostItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {
        var binding = itemView
    }
}
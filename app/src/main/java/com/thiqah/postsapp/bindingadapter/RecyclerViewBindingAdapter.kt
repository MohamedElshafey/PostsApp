package com.thiqah.postsapp.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thiqah.postsapp.list.PostsAdapter

object RecyclerViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: PostsAdapter?) {
        if (adapter != null) {
            recyclerView.adapter = adapter
        }
    }
}
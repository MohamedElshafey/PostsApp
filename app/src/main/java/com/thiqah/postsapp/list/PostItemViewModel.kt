package com.thiqah.postsapp.list

import android.content.Intent
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.detail.DetailActivity

class PostItemViewModel(private val postModel: PostModel) : BaseObservable() {

    @Bindable
    val title = postModel.title

    @Bindable
    val body = postModel.body

    fun onClick(view: View) {
        val context = view.context
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("post", postModel)
        context.startActivity(intent)
    }
}
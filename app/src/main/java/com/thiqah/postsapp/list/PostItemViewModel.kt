package com.thiqah.postsapp.list

import android.content.Intent
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.database.DatabaseManager
import com.thiqah.postsapp.database.PostDatabase
import com.thiqah.postsapp.detail.DetailActivity

class PostItemViewModel(
    private val postModel: PostModel, private val adapter: PostsAdapter?
    , private val itemPosition: Int?
) :
    BaseObservable() {

    @Bindable
    val title = postModel.title

    @Bindable
    val body = postModel.body

    fun deletePost(view: View) {
        val postDatabase = PostDatabase.getInstance(view.context)

        val postDAO = postDatabase!!.responseDAO()

        val databaseManager = DatabaseManager(postDAO)

        databaseManager.deleteRowIfExist(postModel.id)

        adapter?.notifyItemRemoved(itemPosition ?: 0)

        notifyChange()
    }

    fun openDetails(view: View) {
        val context = view.context
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("post", postModel)
        context.startActivity(intent)
    }
}
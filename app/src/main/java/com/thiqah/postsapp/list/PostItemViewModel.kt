package com.thiqah.postsapp.list

import android.content.Intent
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.GsonBuilder
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.database.DatabaseManager
import com.thiqah.postsapp.database.PostDatabase
import com.thiqah.postsapp.detail.DetailActivity
import com.thiqah.postsapp.repository.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val restService = retrofit.create(RestService::class.java)

        //The implementation of deleting post on the documentation not completed
        //so i didn't actually complete it.
        restService.deletePost()

        notifyChange()
    }

    fun openDetails(view: View) {
        val context = view.context
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("post", postModel)
        context.startActivity(intent)
    }
}
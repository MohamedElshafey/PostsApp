package com.thiqah.postsapp.list

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.GsonBuilder
import com.thiqah.postsapp.R
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.database.DatabaseManager
import com.thiqah.postsapp.repository.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostsViewModel(private val databaseManager: DatabaseManager) : BaseObservable() {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val restService = retrofit.create(RestService::class.java)

    val observable = restService.getPosts()

    @Bindable
    var adapter: PostsAdapter? = null

    var disposable: Disposable? = null

    init {
        val localPosts = databaseManager.retrieveAll() as ArrayList
        if (localPosts.isNotEmpty()) {
            Log.d("Test_size", " ${localPosts.size}")
            adapter = PostsAdapter(localPosts)
            notifyChange()
        } else {
            disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter = PostsAdapter(it)
                    databaseManager.insertAll(it)
                    notifyChange()
                }
        }
    }


    fun addPost(titleEditText: EditText, bodyEditText: EditText) {
        val context = titleEditText.context
        val title = titleEditText.text.toString()
        val body = bodyEditText.text.toString()
        if (title.isEmpty()) {
            Toast.makeText(
                context,
                context.getString(R.string.title_missing_message),
                Toast.LENGTH_LONG
            ).show()
        } else if (body.isEmpty()) {
            Toast.makeText(
                context,
                context.getString(R.string.body_missing_message),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        val post = PostModel(title = title, body = body, userId = 1)
        databaseManager.updateOrInsert(post)
        adapter?.addItem(post)

    }
}
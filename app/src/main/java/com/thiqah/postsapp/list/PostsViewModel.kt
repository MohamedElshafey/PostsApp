package com.thiqah.postsapp.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.GsonBuilder
import com.thiqah.postsapp.database.DatabaseManager
import com.thiqah.postsapp.repository.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostsViewModel(databaseManager: DatabaseManager) : BaseObservable() {

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
        val localPosts = databaseManager.retrieveAll()
        if (localPosts.isNotEmpty()) {
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
}
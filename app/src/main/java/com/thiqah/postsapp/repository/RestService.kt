package com.thiqah.postsapp.repository

import com.thiqah.postsapp.data.PostModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {

    @GET("/posts")
    fun getPosts(): Observable<ArrayList<PostModel>>

}
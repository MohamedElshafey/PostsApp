package com.thiqah.postsapp.repository

import com.thiqah.postsapp.data.PostModel
import io.reactivex.Observable
import retrofit2.http.*

interface RestService {

    @GET("/posts")
    fun getPosts(): Observable<ArrayList<PostModel>>


    @POST("/posts")
    @Headers("Content-Type:application/json")
    fun addPost(@Body postBody: PostBody): Observable<PostModel>

    //from the API documentation (Important: the resource will not be really deleted on the server but it will be faked as if.)
    @DELETE("posts/1")
    fun deletePost()

}
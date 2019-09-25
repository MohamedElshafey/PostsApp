package com.thiqah.postsapp.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thiqah.postsapp.R
import com.thiqah.postsapp.database.DatabaseManager
import com.thiqah.postsapp.database.PostDatabase
import com.thiqah.postsapp.databinding.ActivityPostsBinding


/**
 * Hello there
 * This is Posts App for Thiqah Co.
 *
 * I want to apologize that i couldn't complete all task points
 * Here are what i finished:
 *  = Post Item contains (Title , Body , ID)
 *  = Two Activities (List & Detail)
 *  = support Add - Delete ( Local and Online)
 *  = each row contains post title
 *  = detail activity contains title and details text
 *  = The app get posts Online from the API if the database is empty
 *  = Addition and Deletion are effecting both online and offline repos.
 *
 *
 * What i couldn't finish because of the deadline but i can definitely do it
 *  = Paging
 *  = Unit Test
 *  = Edit Posts
 *
 * I Used MVVM as architecture pattern in this project
 * Retrofit as Http Client Library
 * Rx
 * Kotlin as programming language
 * Data binding
 * Room as Database Library
 *
 * The application needs some refactoring so please put that into your consideration.
 *
 * Thanks for your Consideration
 */


class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPostsBinding>(this, R.layout.activity_posts)

        val postDatabase = PostDatabase.getInstance(this)

        val postDAO = postDatabase!!.responseDAO()

        val databaseManager = DatabaseManager(postDAO)

        binding.listVM = PostsViewModel(databaseManager)
    }

}

package com.thiqah.postsapp.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thiqah.postsapp.R
import com.thiqah.postsapp.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPostsBinding>(this, R.layout.activity_posts)

        binding.listVM = PostsViewModel()
    }

}

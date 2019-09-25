package com.thiqah.postsapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thiqah.postsapp.R
import com.thiqah.postsapp.data.PostModel
import com.thiqah.postsapp.databinding.ActivityDetailBinding
import com.thiqah.postsapp.list.PostItemViewModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        val post = intent.getParcelableExtra<PostModel>("post")

        binding.detailVM = PostItemViewModel(post, null, null)

    }
}

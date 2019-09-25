package com.thiqah.postsapp.database

import com.thiqah.postsapp.data.PostModel

class DatabaseManager(val postDAO: PostDAO) {

    fun deleteRowIfExist(postId: Int) {
        val row = postDAO.getRow(postId)
        if (row != null) {
            postDAO.delete(row)
        }
    }

    fun getRow(postRow: PostModel) {
        postDAO.update(postRow)
    }

    fun insertAll(posts: ArrayList<PostModel>) {
        posts.forEach {
            postDAO.insert(it)
        }
    }


    fun retrieveAll(): List<PostModel> {
        return postDAO.retrieveAll()
    }

    //Update  the row exist or insert new row.
    fun updateOrInsert(posts: PostModel) {
        val row = postDAO.getRow(posts.id)
        if (row == null) {
            postDAO.insert(posts)
        } else {
            postDAO.update(row)
        }
    }

}
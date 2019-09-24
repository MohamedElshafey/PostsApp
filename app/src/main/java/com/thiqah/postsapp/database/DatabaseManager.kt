package com.thiqah.postsapp.database

import java.util.*

class DatabaseManager(val responseDAO: PostDAO) {

    fun deleteRowIfExist(postId: Int) {
        val row = responseDAO.getRow(postId)
        if (row != null) {
            responseDAO.delete(row)
        }
    }

    fun getRow(postRow: PostTable) {
        responseDAO.update(postRow)
    }

    fun insertAll(posts: ArrayList<PostTable>) {
        posts.forEach {
            responseDAO.insert(it)
        }
    }

    //Update  the row exist or insert new row.
    fun updateOrInsert(posts: PostTable) {
        val row = responseDAO.getRow(posts.id)
        if (row == null) {
            responseDAO.insert(posts)
        } else {
            responseDAO.update(row)
        }
    }

}
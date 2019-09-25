package com.thiqah.postsapp.database

import androidx.room.*
import com.thiqah.postsapp.data.PostModel


/**
 * Created by Mohamed Elshafey on 4/25/2018.
 */
@Dao
interface PostDAO {

    @Query("SELECT count(*) FROM postTable")
    fun rowsCount(): Int

    @Query("SELECT * FROM postTable")
    fun retrieveAll(): List<PostModel>

    @Query("SELECT * FROM postTable WHERE id LIKE :postId LIMIT 1")
    fun getRow(postId: Int): PostModel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg postTable: PostModel)

    @Update
    fun update(postTable: PostModel)

    @Delete
    fun delete(postTable: PostModel)
}
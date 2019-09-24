package com.thiqah.postsapp.database

import androidx.room.*


/**
 * Created by Mohamed Elshafey on 4/25/2018.
 */
@Dao
interface PostDAO {

    @Query("SELECT count(*) FROM postTable")
    fun rowsCount(): Int

    @Query("SELECT * FROM postTable")
    fun retrieveAll(): List<PostTable>

    @Query("SELECT * FROM postTable WHERE userId LIKE :userId LIMIT 1")
    fun getRow(userId: Int): PostTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg postTable: PostTable)

    @Update
    fun update(postTable: PostTable)

    @Delete
    fun delete(postTable: PostTable)
}
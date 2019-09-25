package com.thiqah.postsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thiqah.postsapp.data.PostModel

/**
 * Created by Mohamed Elshafey on 4/25/2018.
 */
@Database(entities = [(PostModel::class)], version = 2, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {

    abstract fun responseDAO(): PostDAO

    companion object {
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase? {
            if (INSTANCE == null) {
                synchronized(PostDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        PostDatabase::class.java, "data.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }

}
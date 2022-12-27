package com.jhon.posts.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhon.posts.database.dao.PostDao
import com.jhon.posts.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
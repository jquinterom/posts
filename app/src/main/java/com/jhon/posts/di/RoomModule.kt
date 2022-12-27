package com.jhon.posts.di

import android.content.Context
import androidx.room.Room
import com.jhon.posts.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "post-db"
        ).build()

    @Singleton
    @Provides
    fun providePostDao(db: AppDatabase) = db.postDao()
}
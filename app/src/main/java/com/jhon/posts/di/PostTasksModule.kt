package com.jhon.posts.di

import com.jhon.posts.api.repository.AlbumRepository
import com.jhon.posts.api.repository.PostRepository
import com.jhon.posts.interfaces.AlbumTask
import com.jhon.posts.interfaces.PostTasks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostTasksModule {
    @Binds
    abstract fun bindPostTasks(
        postRepository: PostRepository,
    ): PostTasks

    @Binds
    abstract fun bindAlbumTasks(
        albumRepository: AlbumRepository,
    ): AlbumTask
}
package com.example.todo_list.di

import com.example.todo_list.data.repository.TaskRepositoryImpl
import com.example.todo_list.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserCityRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository = impl
}
package com.example.todo_list.di

import com.example.todo_list.data.datasource.LocalDataSourceImpl
import com.example.todo_list.domain.datasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {
    @Provides
    @AppScope
    fun provideLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource = impl

}
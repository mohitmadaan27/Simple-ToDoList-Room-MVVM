package com.mohit.todolist.di

import android.content.Context
import com.mohit.todolist.data.repository.TodoRepository
import com.mohit.todolist.data.source.local.LocalDataSource
import com.mohit.todolist.data.source.local.room.TodoDb

object Injection {

    fun provideRepository(context: Context): TodoRepository {
        val database = TodoDb.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.todoDAO())

        return TodoRepository.getInstance(localDataSource)
    }
}
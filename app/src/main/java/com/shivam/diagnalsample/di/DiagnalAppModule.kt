package com.shivam.diagnalsample.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiagnalAppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
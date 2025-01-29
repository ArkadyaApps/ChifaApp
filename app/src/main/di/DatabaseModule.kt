package com.arkadya.chifa.di

import android.content.Context
import androidx.room.Room
import com.arkadya.chifa.data.local.AppDatabase
import com.arkadya.chifa.data.local.MyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "chifa_database"
        ).build()
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase): MyDao { // Replace `YourDao` with your DAO interface
        return appDatabase.myDao()
    }
}

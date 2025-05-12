package com.suresh.latestandroidstructure.core.di

import android.content.Context
import androidx.room.Room
import com.suresh.latestandroidstructure.core.data.local.room.RoomDB
import com.suresh.latestandroidstructure.core.data.local.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java,
            "Clean_MVVM_ANDROI"
        ).build()
    }

    @Provides
    fun provideUserDao(database: RoomDB): UserDao {
        return database.userDao()
    }
}
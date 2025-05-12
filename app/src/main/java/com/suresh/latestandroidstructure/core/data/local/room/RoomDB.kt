package com.suresh.latestandroidstructure.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}
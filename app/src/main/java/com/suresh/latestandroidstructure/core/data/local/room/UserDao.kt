package com.suresh.latestandroidstructure.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.suresh.latestandroidstructure.features.auth.data.dto.UserDetailDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDetails: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * FROM users where email = :email")
    suspend fun getUserDetails(email: String): UserDetailDto

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): UserDetailDto?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): UserDetailDto

}
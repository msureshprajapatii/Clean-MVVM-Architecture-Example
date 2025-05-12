package com.suresh.latestandroidstructure.core.data.local.preferences

import android.content.Context
import com.suresh.latestandroidstructure.core.constant.IS_LOGGED_IN
import com.suresh.latestandroidstructure.core.constant.USER_ID
import com.suresh.latestandroidstructure.core.constant.USER_PREFS

class UserPreferences(context: Context) {
    private val prefs = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

    fun setUserData(userData: UserData){
        prefs.edit().apply {
            putBoolean(IS_LOGGED_IN, userData.isLoggedIn)
            putLong(USER_ID, userData.userId)
        }.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun getLoggedInUserId(): Int {
        return prefs.getLong(USER_ID,0).toInt()
    }

    fun logout() {
        prefs.edit()
            .putBoolean(IS_LOGGED_IN, false)
            .remove(USER_ID)
            .apply()
    }
}

data class UserData(val userId:Long, val isLoggedIn:Boolean)
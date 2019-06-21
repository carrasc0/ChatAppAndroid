package com.example.chatapp.manager


import android.content.Context
import android.content.SharedPreferences

import com.example.chatapp.dagger.component.ApiController

class FlechPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        ApiController.getAppComponent().inject(this)
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        setIdUser(1)
    }

    fun setIdUser(idUser: Int) {
        sharedPreferences.edit()
                .putInt(KEY_ID_USER, idUser)
                .apply()
    }

    companion object {
        private val SHARED_PREFERENCES_NAME = "sharedFlechPreferences"
        private val KEY_ID_USER = "idUser"
        private val KEY_NAME_USER = "nameUser"
    }

    //public int getIdUser() {
    //   return sharedPreferences.getInt(KEY_ID_USER, 2);
    //}
}

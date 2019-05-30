package com.example.mvvmtest.manager;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvvmtest.dagger.component.ApiController;

public class FlechPreferences {

    private static final String SHARED_PREFERENCES_NAME = "sharedFlechPreferences";
    private static final String KEY_ID_USER = "idUser";
    private static final String KEY_NAME_USER = "nameUser";

    private SharedPreferences sharedPreferences;

    public FlechPreferences(Context context) {
        ApiController.getAppComponent().inject(this);
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        setIdUser(1);
    }

    public void setIdUser(int idUser) {
        sharedPreferences.edit()
                .putInt(KEY_ID_USER, idUser)
                .apply();
    }

    //public int getIdUser() {
     //   return sharedPreferences.getInt(KEY_ID_USER, 2);
    //}
}

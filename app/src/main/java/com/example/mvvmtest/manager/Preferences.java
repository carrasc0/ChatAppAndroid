package com.example.mvvmtest.manager;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvvmtest.dagger.component.ApiController;

public class Preferences {

    private static final String SHARED_PREFERENCES_NAME = "sharedPreferences";
    private static final String KEY_ID_USER = "idUser";

    private SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        ApiController.getAppComponent().inject(this);
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        setIdUser(1);
    }

    public void setIdUser(int idUser) {
        sharedPreferences.edit()
                .putInt(KEY_ID_USER, idUser)
                .apply();
    }

    public int getIdUser() {
        return sharedPreferences.getInt(KEY_ID_USER, 1);
    }
}

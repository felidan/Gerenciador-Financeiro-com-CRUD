package com.example.felipedantas.gerenciador.DataLayer.DL;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Felipe Dantas on 28/04/2017.
 */

public class SecurityPreferences {
    private final SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("Gerenciador", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoreString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }
}

package com.blackwhite.sinamn75.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.blackwhite.sinamn75.base.base.App;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

    public void put(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public float get(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clearAll() {
        sharedPreferences.edit().clear().apply();
    }
}
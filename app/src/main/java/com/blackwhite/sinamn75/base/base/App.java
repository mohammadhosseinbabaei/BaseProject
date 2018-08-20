package com.blackwhite.sinamn75.base.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.util.Locale;

public class App extends Application {

    public static Typeface typeface;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());
        context = getApplicationContext();
        try {
            Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, Typeface.createFromAsset(getApplicationContext().getAssets(), "FONT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
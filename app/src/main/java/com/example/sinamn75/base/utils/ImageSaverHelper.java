package com.example.sinamn75.base.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class ImageSaverHelper implements Target {
    private final String name;

    public ImageSaverHelper(String name) {
        this.name = name;
    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/celewall/images/");
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath() + "/celewall/images/" + name));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }
}
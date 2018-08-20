package com.example.sinamn75.base.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    public void transactionReplace(int animationIn, int animationOut, int layout, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(animationIn, animationOut).replace(layout, fragment).commit();
    }

    public void toastInfo(String text) {
        Toasty.info(Objects.requireNonNull(getApplicationContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastWarning(String text) {
        Toasty.warning(Objects.requireNonNull(getApplicationContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastError(String text) {
        Toasty.error(Objects.requireNonNull(getApplicationContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastSuccess(String text) {
        Toasty.success(Objects.requireNonNull(getApplicationContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastNormal(String text) {
        Toasty.normal(Objects.requireNonNull(getApplicationContext()), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());

        try {
            Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, Typeface.createFromAsset(getApplicationContext().getAssets(), "FONT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void showKeyboard() {
        Objects.requireNonNull((InputMethodManager) Objects.requireNonNull(getApplicationContext()).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void hideKeyboard(EditText editText) {
        Objects.requireNonNull((InputMethodManager) Objects.requireNonNull(getApplicationContext()).getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean isEmpty(String text) {
        return !text.isEmpty();
    }

    public void intentBackStack(Activity currentActivity, Class targetActivity) {
        startActivity(new Intent(currentActivity, targetActivity));
    }

    public void intent(Activity currentActivity, Class targetActivity) {
        startActivity(new Intent(currentActivity, targetActivity).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public String getRealPathFromURI(Uri contentURI) {
        String filePath;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            cursor.close();
        }
        return filePath;
    }
}
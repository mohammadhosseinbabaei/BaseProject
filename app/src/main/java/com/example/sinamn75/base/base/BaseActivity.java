package com.example.sinamn75.base.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
}
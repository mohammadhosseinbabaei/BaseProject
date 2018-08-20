package com.example.sinamn75.base.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sinamn75.base.dialog.LoadingDialog;
import com.example.sinamn75.base.utils.PicassoSaverHelper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Objects.requireNonNull(getContext()).getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        try {
            Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, Typeface.createFromAsset(App.getContext().getAssets(), "FONT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        loadingDialog.show(Objects.requireNonNull(getFragmentManager()), "loading");
    }

    public void dismissDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = LoadingDialog.newInstance();
    }

    public void toastInfo(String text) {
        Toasty.info(Objects.requireNonNull(getContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastWarning(String text) {
        Toasty.warning(Objects.requireNonNull(getContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastSuccess(String text) {
        Toasty.success(Objects.requireNonNull(getContext()), text, Toast.LENGTH_SHORT, true).show();
    }

    public void toastNormal(String text) {
        Toasty.normal(Objects.requireNonNull(getContext()), text, Toast.LENGTH_SHORT).show();
    }

    public void toastError(String text) {
        Toasty.error(Objects.requireNonNull(getContext()), text, Toast.LENGTH_SHORT).show();
    }

    public void setLinearLayoutForRecyclerView(RecyclerView recyclerView, boolean nested) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(nested);
        recyclerView.setHasFixedSize(true);
    }

    public void setLinearLayoutForRecyclerViewHorizontal(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void setGridLayoutForRecyclerView(RecyclerView recyclerView, int spanCount) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }


    public void transaction(int animationIn, int animationOut, int layout, Fragment fragment) {
        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().setCustomAnimations(animationIn, animationOut);
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }

    public void transactionAddToBackStack(int layout, int animationIn, int animationOut, Fragment fragment, String tag) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().setCustomAnimations(animationIn, animationOut).replace(layout, fragment).addToBackStack(tag).commit();
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public void picasso(String url, ImageView imageView) {
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        String existFileName = Environment.getExternalStorageDirectory().getPath() + "/celewall/images/" + fileName;
        if (ifImageExist(existFileName)) {
            Picasso.get().load(Uri.fromFile(new File(existFileName))).into(imageView);
        } else {
            Picasso.get().load(url).into(new PicassoSaverHelper(fileName));
            Picasso.get().load(url).into(imageView);
        }
    }

    public void showKeyboard() {
        Objects.requireNonNull((InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void hideKeyboard(EditText editText) {
        Objects.requireNonNull((InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public boolean ifImageExist(String text) {
        File file = new File(text);
        return file.exists();
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
        Cursor cursor = App.getContext().getContentResolver().query(contentURI, null, null, null, null);
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
package com.example.sinamn75.base.base;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sinamn75.base.dialog.LoadingDialog;
import com.example.sinamn75.base.utils.ImageSaverHelper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class BaseDialog extends DialogFragment {
    private LoadingDialog loadingDialog;

    public BaseDialog() {
    }

    public void showLoading() {
        loadingDialog.show(Objects.requireNonNull(getFragmentManager()), "loading");
    }

    public void dismissDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loadingDialog = LoadingDialog.newInstance();
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

    public void setLinearLayoutForRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void setGridLayoutForRecyclerView(RecyclerView recyclerView, int spanCount) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void picasso(String url, ImageView imageView) {
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        String existFileName = Environment.getExternalStorageDirectory().getPath() + "/celewall/images/" + fileName;
        if (ifImageExist(existFileName)) {
            Picasso.get().load(Uri.fromFile(new File(existFileName))).into(imageView);
        } else {
            Picasso.get().load(url).into(new ImageSaverHelper(fileName));
            Picasso.get().load(url).into(imageView);
        }
    }

    public boolean ifImageExist(String text) {
        File file = new File(text);
        return file.exists();
    }

    public boolean isEmpty(String text) {
        return !text.isEmpty();
    }
}
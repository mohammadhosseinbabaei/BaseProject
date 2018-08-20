package com.blackwhite.sinamn75.base.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.blackwhite.sinamn75.base.R;
import com.blackwhite.sinamn75.base.base.BaseDialog;

import java.util.Objects;

public class LoadingDialog extends BaseDialog {

    public LoadingDialog() {
    }

    public static LoadingDialog newInstance() {
        Bundle bundle = new Bundle();
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }
}
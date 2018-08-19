package com.example.sinamn75.base.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.sinamn75.base.R;
import com.example.sinamn75.base.base.BaseDialog;

import java.util.Objects;

public class OptionDialog extends BaseDialog {

    private Button buttonOption1OptionDialog;
    private Button buttonOption2OptionDialog;
    private Button buttonOption3OptionDialog;
    private TextView textViewTitleOptionDialog;
    private TextView textViewHeaderOptionDialog;

    public OptionDialog() {
    }

    public static OptionDialog newInstance() {
        Bundle bundle = new Bundle();
        OptionDialog optionDialog = new OptionDialog();
        optionDialog.setArguments(bundle);
        return optionDialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_option, container, false);
        initialView(view);

        return view;
    }

    private void initialView(View view) {
        buttonOption1OptionDialog = view.findViewById(R.id.buttonOption1OptionDialog);
        buttonOption2OptionDialog = view.findViewById(R.id.buttonOption2OptionDialog);
        buttonOption3OptionDialog = view.findViewById(R.id.buttonOption3OptionDialog);
        textViewTitleOptionDialog = view.findViewById(R.id.textViewTitleOptionDialog);
        textViewHeaderOptionDialog = view.findViewById(R.id.textViewHeaderOptionDialog);
    }
}
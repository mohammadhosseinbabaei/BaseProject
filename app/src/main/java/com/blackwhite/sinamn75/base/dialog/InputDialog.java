package com.blackwhite.sinamn75.base.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackwhite.sinamn75.base.R;
import com.blackwhite.sinamn75.base.base.BaseDialog;

import java.util.Objects;

public class InputDialog extends BaseDialog {

    private TextView textViewHeaderInputDialog;
    private TextView textViewDescriptionInputDialog;
    private EditText editTextInputDialog;
    private ImageView imageViewInputDialog;
    private Button buttonOkInputDialog;
    private Button buttonCancelDialog;

    public InputDialog() {
    }

    public static InputDialog newInstance() {
        Bundle bundle = new Bundle();
        InputDialog inputDialog = new InputDialog();
        inputDialog.setArguments(bundle);
        return inputDialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_input, container, false);
        initialView(view);

        return view;
    }

    private void initialView(View view) {
        textViewHeaderInputDialog = view.findViewById(R.id.textViewHeaderInputDialog);
        editTextInputDialog = view.findViewById(R.id.editTextInputDialog);
        imageViewInputDialog = view.findViewById(R.id.imageViewInputDialog);
        buttonOkInputDialog = view.findViewById(R.id.buttonOkInputDialog);
        buttonCancelDialog = view.findViewById(R.id.buttonCancelDialog);
        textViewDescriptionInputDialog = view.findViewById(R.id.textViewDescriptionInputDialog);
    }
}
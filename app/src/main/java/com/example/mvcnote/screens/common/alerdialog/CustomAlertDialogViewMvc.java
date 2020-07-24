package com.example.mvcnote.screens.common.alerdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;

public class CustomAlertDialogViewMvc
        extends BaseObservableViewMvc<CustomAlertDialogViewMvc.Listener> {
    public interface Listener {
        void onYesClick();
        void onNoClick();
    }

    private final TextView mTitleText;
    private final Button mYesButton;
    private final Button mNoButton;

    public CustomAlertDialogViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_custom_dialog, parent, false));
        mTitleText = findViewById(R.id.dialog_title_text);
        mYesButton = findViewById(R.id.dialog_yes_button);
        mNoButton = findViewById(R.id.dialog_no_button);

        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onYesClick();
                }
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onNoClick();
                }
            }
        });
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }
}

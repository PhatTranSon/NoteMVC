package com.example.mvcnote.screens.common.alerdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.mvcnote.screens.common.ViewMvcFactory;

public class DeleteAlertDialog extends Dialog implements CustomAlertDialogViewMvc.Listener {
    private CustomAlertDialogViewMvc mViewMvc;
    private final ViewMvcFactory mViewMvcFactory;
    private final Listener mListener;

    public interface Listener {
        void onYesClick();
        void onNoClick();
    }

    public DeleteAlertDialog(@NonNull Context context,
                             ViewMvcFactory viewMvcFactory,
                             Listener listener) {
        super(context);
        this.mViewMvcFactory = viewMvcFactory;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = mViewMvcFactory.getCustomDialogViewMvc();
        mViewMvc.setTitle("Are you sure you want to delete this ?");
        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    public void onYesClick() {
        mListener.onYesClick();
        dismiss();
    }

    @Override
    public void onNoClick() {
        mListener.onNoClick();
        dismiss();
    }
}

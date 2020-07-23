package com.example.mvcnote.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.screens.common.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {

    private final TextView mTitleText;

    public ToolbarViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTitleText = findViewById(R.id.toolbar_title);
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }
}

package com.example.mvcnote.screens.common;

import android.view.View;

public abstract class BaseViewMvc implements ViewMvc{
    private View mView;

    protected void setRootView(View view) {
        mView = view;
    }

    @Override
    public View getRootView() {
        return mView;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }
}

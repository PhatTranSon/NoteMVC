package com.example.mvcnote.screens.common;

import android.view.View;

public interface ViewMvc {
    View getRootView();
    <T extends View> T findViewById(int id);
}

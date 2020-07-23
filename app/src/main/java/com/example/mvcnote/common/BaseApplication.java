package com.example.mvcnote.common;

import android.app.Application;

import com.example.mvcnote.common.dependencyinjection.CompositionRoot;

public class BaseApplication extends Application {
    private CompositionRoot mCompositionRoot;

    public CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot();
        }
        return mCompositionRoot;
    }
}

package com.example.mvcnote.common.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import com.example.mvcnote.database.FetchFakeNoteUseCase;
import com.example.mvcnote.screens.common.ViewMvcFactory;

public class ControllerCompositionRoot {
    private final CompositionRoot mCompositionRoot;
    private final Activity mActivity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    public FetchFakeNoteUseCase getFetchFakeNoteUseCase() {
        return mCompositionRoot.getFetchFakeNoteUseCase();
    }

    private Context getContext() {
        return mActivity;
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getContext());
    }
}

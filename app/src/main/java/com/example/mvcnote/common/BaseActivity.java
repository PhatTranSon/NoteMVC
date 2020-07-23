package com.example.mvcnote.common;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvcnote.common.dependencyinjection.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {
    protected ControllerCompositionRoot getCompositionRoot() {
        return new ControllerCompositionRoot(
                ((BaseApplication) getApplication()).getCompositionRoot(),
                this
        );
    }
}

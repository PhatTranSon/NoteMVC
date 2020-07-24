package com.example.mvcnote.screens.noteadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvcnote.common.BaseActivity;

public class NoteAddActivity extends BaseActivity implements NoteAddViewMvc.Listener {
    private NoteAddViewMvc mViewMvc;

    public static void start(Context context) {
        Intent intent = new Intent(context, NoteAddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getNoteAddViewMvc(null);
        mViewMvc.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    public void onAdd(String title, String body) {
        //Perform add
    }
}
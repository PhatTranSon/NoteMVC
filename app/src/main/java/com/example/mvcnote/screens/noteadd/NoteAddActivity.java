package com.example.mvcnote.screens.noteadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvcnote.common.BaseActivity;
import com.example.mvcnote.database.AddRoomNoteUseCase;

public class NoteAddActivity extends BaseActivity implements NoteAddViewMvc.Listener, AddRoomNoteUseCase.Listener {
    private NoteAddViewMvc mViewMvc;
    private AddRoomNoteUseCase mAddUseCase;

    public static void start(Context context) {
        Intent intent = new Intent(context, NoteAddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getNoteAddViewMvc(null);
        mViewMvc.registerListener(this);

        mAddUseCase = getCompositionRoot().getAddNoteUseCase();
        mAddUseCase.registerListener(this);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    public void onAdd(String title, String body) {
        //Perform add
        mAddUseCase.addNoteAndNotify(title, body);
    }

    @Override
    public void onAddNoteSuccess() {
        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onAddNoteError(Throwable error) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
package com.example.mvcnote.screens.notelist;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvcnote.R;
import com.example.mvcnote.common.BaseActivity;
import com.example.mvcnote.database.FetchFakeNoteUseCase;
import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.toolbar.ToolbarViewMvc;

import java.util.List;

public class NoteListActivity extends BaseActivity implements FetchFakeNoteUseCase.Listener {
    private NoteListViewMvc mViewMvc;
    private FetchFakeNoteUseCase mFetchNotesUseCase;
    private Toolbar mToolbar;
    private ToolbarViewMvc mToolbarViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFetchNotesUseCase = getCompositionRoot().getFetchFakeNoteUseCase();
        mViewMvc = getCompositionRoot().getViewMvcFactory().getNoteListViewMvc(null);

        mToolbar = mViewMvc.findViewById(R.id.toolbar);
        mToolbarViewMvc = getCompositionRoot().getViewMvcFactory().getToolbarViewMvc(mToolbar);
        mToolbarViewMvc.setTitle("All notes");
        mToolbar.addView(mToolbarViewMvc.getRootView());

        setContentView(mViewMvc.getRootView());
    }

    private void setUpToolbar() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchNotesUseCase.registerListener(this);
        mFetchNotesUseCase.fetchNotesAndNotify();
    }

    @Override
    public void onFetchNotesSuccess(List<Note> notes) {
        mViewMvc.bindNotes(notes);
    }

    @Override
    public void onFetchNotesError(Throwable t) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
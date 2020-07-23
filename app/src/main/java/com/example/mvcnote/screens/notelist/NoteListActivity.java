package com.example.mvcnote.screens.notelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.mvcnote.database.FetchFakeNoteUseCase;
import com.example.mvcnote.notes.Note;

import java.util.List;

public class NoteListActivity extends AppCompatActivity implements FetchFakeNoteUseCase.Listener {
    private NoteListViewMvc mViewMvc;
    private FetchFakeNoteUseCase mFetchNotesUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFetchNotesUseCase = new FetchFakeNoteUseCase();
        mViewMvc = new NoteListViewMvcImpl(
                LayoutInflater.from(this),
                null
        );
        setContentView(mViewMvc.getRootView());
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
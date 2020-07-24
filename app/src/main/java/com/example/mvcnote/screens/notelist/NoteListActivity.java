package com.example.mvcnote.screens.notelist;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvcnote.R;
import com.example.mvcnote.common.BaseActivity;
import com.example.mvcnote.database.DeleteRoomNoteUseCase;
import com.example.mvcnote.database.FetchRoomNotesUseCase;
import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.alerdialog.DeleteAlertDialog;
import com.example.mvcnote.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvcnote.screens.noteadd.NoteAddActivity;

import java.util.List;

public class NoteListActivity extends BaseActivity implements FetchRoomNotesUseCase.Listener, ToolbarViewMvc.Listener, NoteListViewMvc.Listener, DeleteRoomNoteUseCase.Listener {
    private NoteListViewMvc mViewMvc;

    private FetchRoomNotesUseCase mFetchNotesUseCase;
    private DeleteRoomNoteUseCase mDeleteUseCase;

    private Toolbar mToolbar;
    private ToolbarViewMvc mToolbarViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFetchNotesUseCase = getCompositionRoot().getFetchFakeNoteUseCase();
        mDeleteUseCase = getCompositionRoot().getDeleteNoteUseCase();

        mViewMvc = getCompositionRoot().getViewMvcFactory().getNoteListViewMvc(null);
        mViewMvc.registerListener(this);

        mToolbar = mViewMvc.findViewById(R.id.toolbar);
        mToolbarViewMvc = getCompositionRoot().getViewMvcFactory().getToolbarViewMvc(mToolbar);
        mToolbarViewMvc.setTitle("All notes");
        mToolbar.addView(mToolbarViewMvc.getRootView());

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mToolbarViewMvc.registerListener(this);

        mDeleteUseCase.registerListener(this);

        mFetchNotesUseCase.registerListener(this);
        mFetchNotesUseCase.fetchNotesAndNotify();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewMvc.unregisterListener(this);

        mToolbarViewMvc.unregisterListener(this);

        mDeleteUseCase.unregisterListener(this);

        mFetchNotesUseCase.unregisterListener(this);
    }

    @Override
    public void onFetchNotesSuccess(List<Note> notes) {
        mViewMvc.bindNotes(notes);
    }

    @Override
    public void onFetchNotesError(Throwable t) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddButtonClick() {
        NoteAddActivity.start(this);
    }

    @Override
    public void onNoteClick(Note note) {
        //TODO: Start detail activity
    }

    @Override
    public void onDeleteClick(final Note note) {
        DeleteAlertDialog deleteAlertDialog = new DeleteAlertDialog(
                this,
                getCompositionRoot().getViewMvcFactory(),
                new DeleteAlertDialog.Listener() {
                    @Override
                    public void onYesClick() {
                        //User click yes
                        mDeleteUseCase.deleteNoteAndNotify(note);
                    }

                    @Override
                    public void onNoClick() {
                        //User click no
                    }
                }
        );
        deleteAlertDialog.show();
    }

    @Override
    public void onNoteDeleteSuccess() {
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        mFetchNotesUseCase.fetchNotesAndNotify();
    }

    @Override
    public void onNoteDeleteError(Throwable error) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
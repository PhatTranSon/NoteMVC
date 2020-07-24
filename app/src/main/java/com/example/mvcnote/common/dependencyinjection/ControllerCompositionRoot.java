package com.example.mvcnote.common.dependencyinjection;

import android.app.Activity;
import android.content.Context;

import com.example.mvcnote.database.AddRoomNoteUseCase;
import com.example.mvcnote.database.DeleteRoomNoteUseCase;
import com.example.mvcnote.database.FetchRoomNotesUseCase;
import com.example.mvcnote.database.NoteDao;
import com.example.mvcnote.database.NoteDatabase;
import com.example.mvcnote.screens.common.ViewMvcFactory;

public class ControllerCompositionRoot {
    private final CompositionRoot mCompositionRoot;
    private final Activity mActivity;
    private NoteDao mNoteDao;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    private Context getContext() {
        return mActivity;
    }

    public NoteDao getNoteDao() {
        if (mNoteDao == null) {
            mNoteDao = NoteDatabase.getInstance(getContext()).getDao();
        }
        return mNoteDao;
    }

    public FetchRoomNotesUseCase getFetchFakeNoteUseCase() {
        return mCompositionRoot.getFetchFakeNoteUseCase(getNoteDao());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getContext());
    }

    public AddRoomNoteUseCase getAddNoteUseCase() {
        return mCompositionRoot.getAddRoomNoteUseCase(getNoteDao());
    }

    public DeleteRoomNoteUseCase getDeleteNoteUseCase() {
        return mCompositionRoot.getDeleteRoomNoteUseCase(getNoteDao());
    }
}

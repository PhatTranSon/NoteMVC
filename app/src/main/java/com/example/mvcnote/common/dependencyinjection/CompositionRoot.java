package com.example.mvcnote.common.dependencyinjection;

import com.example.mvcnote.database.FetchFakeNoteUseCase;
import com.example.mvcnote.database.NoteDao;

public class CompositionRoot {
    public FetchFakeNoteUseCase getFetchFakeNoteUseCase(NoteDao noteDao) {
        return new FetchFakeNoteUseCase(noteDao);
    }
}

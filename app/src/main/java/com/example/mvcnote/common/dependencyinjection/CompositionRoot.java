package com.example.mvcnote.common.dependencyinjection;

import com.example.mvcnote.database.FetchFakeNoteUseCase;

public class CompositionRoot {
    public FetchFakeNoteUseCase getFetchFakeNoteUseCase() {
        return new FetchFakeNoteUseCase();
    }
}

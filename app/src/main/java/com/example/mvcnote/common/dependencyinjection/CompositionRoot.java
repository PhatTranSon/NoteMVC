package com.example.mvcnote.common.dependencyinjection;

import com.example.mvcnote.database.AddRoomNoteUseCase;
import com.example.mvcnote.database.FetchRoomNotesUseCase;
import com.example.mvcnote.database.NoteDao;

public class CompositionRoot {
    public FetchRoomNotesUseCase getFetchFakeNoteUseCase(NoteDao noteDao) {
        return new FetchRoomNotesUseCase(noteDao);
    }

    public AddRoomNoteUseCase getAddRoomNoteUseCase(NoteDao noteDao) {
        return new AddRoomNoteUseCase(noteDao);
    }
}

package com.example.mvcnote.screens.notelist;

import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.ObservableViewMvc;

import java.util.List;

public interface NoteListViewMvc extends ObservableViewMvc<NoteListViewMvc.Listener> {
    interface Listener {
        void onNoteClick(Note note);
    }
    void bindNotes(List<Note> notes);
}

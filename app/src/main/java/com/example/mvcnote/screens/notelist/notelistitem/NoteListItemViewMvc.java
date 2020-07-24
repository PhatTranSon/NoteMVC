package com.example.mvcnote.screens.notelist.notelistitem;

import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.ObservableViewMvc;

public interface NoteListItemViewMvc extends ObservableViewMvc<NoteListItemViewMvc.Listener> {
    interface Listener {
        void onNoteClick(Note note);
        void onDeleteClick(Note note);
    }
    void bindNote(Note note);
}

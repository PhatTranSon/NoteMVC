package com.example.mvcnote.screens.notelist.notelistitem;

import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.ObservableViewMvc;

public interface NoteListItemViewMvc extends ObservableViewMvc<NoteListItemViewMvc.Listener> {
    interface Listener {
        void onNoteClick(int noteId);
    }
    void bindNote(Note note);
}

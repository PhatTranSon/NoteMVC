package com.example.mvcnote.screens.noteadd;

import com.example.mvcnote.screens.common.ObservableViewMvc;

public interface NoteAddViewMvc extends ObservableViewMvc<NoteAddViewMvc.Listener> {
    interface Listener {
        void onAdd(String title, String body);
    }
}

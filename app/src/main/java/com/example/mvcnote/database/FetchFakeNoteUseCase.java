package com.example.mvcnote.database;

import com.example.mvcnote.common.BaseObservable;
import com.example.mvcnote.notes.Note;

import java.util.ArrayList;
import java.util.List;

public class FetchFakeNoteUseCase extends BaseObservable<FetchFakeNoteUseCase.Listener> {
    public interface Listener {
        void onFetchNotesSuccess(List<Note> notes);
        void onFetchNotesError(Throwable t);
    }

    public void fetchNotesAndNotify() {
        //Fake notes
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Hello Word", "Hello the world"));
        notes.add(new Note(2, "Welcome", "Welcome to note taking application"));
        notes.add(new Note(3, "What", "What is this"));

        onSuccess(notes);
    }

    private void onSuccess(List<Note> notes) {
        for (Listener listener : getListeners()) {
            listener.onFetchNotesSuccess(notes);
        }
    }

    private void onError(Throwable throwable) {
        for (Listener listener : getListeners()) {
            listener.onFetchNotesError(throwable);
        }
    }
}

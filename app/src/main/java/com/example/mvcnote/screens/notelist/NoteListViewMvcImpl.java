package com.example.mvcnote.screens.notelist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvcnote.R;
import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;
import com.example.mvcnote.screens.common.ViewMvcFactory;

import java.util.List;

public class NoteListViewMvcImpl
        extends BaseObservableViewMvc<NoteListViewMvc.Listener>
        implements NoteListViewMvc, NoteListAdapter.Listener {
    private RecyclerView mRecyclerView;
    private NoteListAdapter mAdapter;

    public NoteListViewMvcImpl(LayoutInflater inflater,
                               @Nullable ViewGroup parent,
                               ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_note_list, parent, false));

        mAdapter = new NoteListAdapter(viewMvcFactory, this);

        mRecyclerView = findViewById(R.id.notes_recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
    }

    @Override
    public void bindNotes(List<Note> notes) {
        mAdapter.update(notes);
    }

    @Override
    public void onNoteClick(Note note) {
        for (Listener listener : getListeners()) {
            listener.onNoteClick(note);
        }
    }

    @Override
    public void onDeleteClick(Note note) {
        for (Listener listener : getListeners()) {
            listener.onDeleteClick(note);
        }
    }
}

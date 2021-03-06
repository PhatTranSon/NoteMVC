package com.example.mvcnote.screens.notelist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.ViewMvcFactory;
import com.example.mvcnote.screens.notelist.notelistitem.NoteListItemViewMvc;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> implements NoteListItemViewMvc.Listener {
    private final List<Note> mNotes = new ArrayList<>();
    private final ViewMvcFactory mViewMvcFactory;
    private final Listener mListener;

    @Override
    public void onNoteClick(Note note) {
        mListener.onNoteClick(note);
    }

    @Override
    public void onDeleteClick(Note note) {
        mListener.onDeleteClick(note);
    }

    public interface Listener {
        void onNoteClick(Note note);
        void onDeleteClick(Note note);
    }

    public NoteListAdapter(ViewMvcFactory viewMvcFactory, Listener listener) {
        this.mViewMvcFactory = viewMvcFactory;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        NoteListItemViewMvc viewMvc = mViewMvcFactory.getNoteListItemView(parent);
        viewMvc.registerListener(this);
        NoteViewHolder viewHolder = new NoteViewHolder(viewMvc.getRootView());
        viewHolder.itemView.setTag(viewMvc);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = mNotes.get(position);
        NoteListItemViewMvc viewMvc = (NoteListItemViewMvc) holder.itemView.getTag();
        viewMvc.bindNote(note);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void update(List<Note> newNotes) {
        mNotes.clear();
        mNotes.addAll(newNotes);
        notifyDataSetChanged();
    }
}

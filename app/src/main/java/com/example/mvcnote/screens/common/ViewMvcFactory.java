package com.example.mvcnote.screens.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.mvcnote.screens.notelist.NoteListViewMvc;
import com.example.mvcnote.screens.notelist.NoteListViewMvcImpl;
import com.example.mvcnote.screens.notelist.notelistitem.NoteListItemViewMvc;
import com.example.mvcnote.screens.notelist.notelistitem.NoteListItemViewMvcImpl;

public class ViewMvcFactory {
    private final Context mContext;

    public ViewMvcFactory(Context context) {
        mContext = context;
    }

    public NoteListViewMvc getNoteListViewMvc(@Nullable ViewGroup parent) {
        return new NoteListViewMvcImpl(
                LayoutInflater.from(mContext),
                parent,
                this
        );
    }

    public NoteListItemViewMvc getNoteListItemView(@Nullable ViewGroup parent) {
        return new NoteListItemViewMvcImpl(
                LayoutInflater.from(mContext),
                parent
        );
    }
}
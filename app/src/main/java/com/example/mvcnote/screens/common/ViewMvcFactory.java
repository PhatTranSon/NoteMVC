package com.example.mvcnote.screens.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.mvcnote.screens.common.alerdialog.CustomAlertDialogViewMvc;
import com.example.mvcnote.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvcnote.screens.noteadd.NoteAddViewMvc;
import com.example.mvcnote.screens.noteadd.NoteAddViewMvcImpl;
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

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent) {
        return new ToolbarViewMvc(
                LayoutInflater.from(mContext),
                parent
        );
    }

    public NoteAddViewMvc getNoteAddViewMvc(@Nullable ViewGroup parent) {
        return new NoteAddViewMvcImpl(
                LayoutInflater.from(mContext),
                parent
        );
    }

    public CustomAlertDialogViewMvc getCustomDialogViewMvc() {
        return new CustomAlertDialogViewMvc(
                LayoutInflater.from(mContext),
                null
        );
    }
}

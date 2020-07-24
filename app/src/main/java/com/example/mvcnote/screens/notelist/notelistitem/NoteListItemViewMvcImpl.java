package com.example.mvcnote.screens.notelist.notelistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.notes.Note;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;

public class NoteListItemViewMvcImpl extends BaseObservableViewMvc<NoteListItemViewMvc.Listener> implements NoteListItemViewMvc {
    private final TextView mTitleText, mIdText;
    private final Button mDeleteButton;

    public NoteListItemViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_note_list_item, parent, false));

        mTitleText = findViewById(R.id.note_title_edit_text);
        mIdText = findViewById(R.id.note_id_text);
        mDeleteButton = findViewById(R.id.note_delete_button);
    }

    @Override
    public void bindNote(final Note note) {
        mTitleText.setText(note.getTitle());
        mIdText.setText("Id: " + note.getId());

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onNoteClick(note);
                }
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onDeleteClick(note);
                }
            }
        });
    }
}

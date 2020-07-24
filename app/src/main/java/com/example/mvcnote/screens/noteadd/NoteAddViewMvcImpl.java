package com.example.mvcnote.screens.noteadd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;

public class NoteAddViewMvcImpl extends BaseObservableViewMvc<NoteAddViewMvc.Listener> implements NoteAddViewMvc {
    private final EditText mTitleText;
    private final EditText mBodyText;
    private final Button mAddButton;

    public NoteAddViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_note_add, parent, false));
        mTitleText = findViewById(R.id.note_title_edit_text);
        mBodyText = findViewById(R.id.note_body_edit_text);
        mAddButton = findViewById(R.id.note_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitleText.getText().toString().trim();
                String body = mBodyText.getText().toString().trim();
                for (Listener listener : getListeners()) {
                    listener.onAdd(title, body);
                }
            }
        });
    }
}

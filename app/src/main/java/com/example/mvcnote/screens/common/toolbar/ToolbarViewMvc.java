package com.example.mvcnote.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvcnote.R;
import com.example.mvcnote.screens.common.BaseObservableViewMvc;
import com.example.mvcnote.screens.common.BaseViewMvc;

public class ToolbarViewMvc extends BaseObservableViewMvc<ToolbarViewMvc.Listener> {
    public interface Listener {
        void onAddButtonClick();
    }

    private final TextView mTitleText;
    private final ImageButton mAddButton;

    public ToolbarViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));

        mTitleText = findViewById(R.id.toolbar_title);

        mAddButton = findViewById(R.id.toolbar_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onAddButtonClick();
                }
            }
        });
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }
}

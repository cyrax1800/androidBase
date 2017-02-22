package com.project.michael.base.views.listeners;

import android.view.View;

/**
 * Created by mchen on 11/22/2016.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

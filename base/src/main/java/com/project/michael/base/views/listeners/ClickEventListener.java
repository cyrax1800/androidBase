package com.project.michael.base.views.listeners;

import android.view.View;

import java.util.EventListener;
import java.util.List;

/**
 * Created by michael on 2/7/17.
 */

public abstract class ClickEventListener<Model> implements EventListener {
    public abstract void onClick(View v, Model model, int position);
}

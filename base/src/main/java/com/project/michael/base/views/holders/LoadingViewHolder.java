package com.project.michael.base.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.project.michael.base.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michael on 17/12/16.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.progress_bar) public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
//        progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
    }
}

package com.project.michael.base.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.michael.base.R;
import com.project.michael.base.views.holders.LoadingViewHolder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by michael on 2/2/17.
 */

public class EndlessScrollAdapter<Model> extends BaseAdapter<Model> {

    protected final int VIEW_TYPE_ITEM = 0;
    protected final int VIEW_TYPE_LOADING = -1;

    List<Integer> mFooter = new ArrayList<>();

    public EndlessScrollAdapter(){

    }

    public void loadMore(){
        mFooter.add(0);
        notifyItemInserted(getItemCount());
    }

    public void doneLoadMore(){
        if(mFooter.size() > 0){
            mFooter.remove(0);
            notifyItemRemoved(getItemCount() + 1);
        }
    }

    @Override
    public void add(Model model){
        doneLoadMore();
        super.add(model);
    }

    @Override
    public void add(int position, Model model){
        doneLoadMore();
        super.add(position, model);
    }

    @SafeVarargs
    public final void addAllArray(Model... models) {
        doneLoadMore();
        super.addAll(models);
    }

    @Override
    public void addAll(List<Model> modelList){
        doneLoadMore();
        super.addAll(modelList);
    }

    @Override
    public void addAll(int position, List<Model> modelList){
        doneLoadMore();
        super.addAll(position, modelList);
    }

    @Override
    public int getItemViewType(int position) {
        return (position > mModelList .size() - 1) ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_LOADING){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_holder,parent,false);
            return new LoadingViewHolder(itemView);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mModelList .size() + mFooter.size();
    }
}

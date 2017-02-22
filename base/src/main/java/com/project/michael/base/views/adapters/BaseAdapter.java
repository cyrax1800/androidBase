package com.project.michael.base.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;

import com.project.michael.base.views.helpers.ClickListenerHelper;
import com.project.michael.base.views.listeners.ClickEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by michael on 2/2/17.
 */

public class BaseAdapter<Model> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "tmp-baseAdapter";

    protected List<Model> mModelList = new ArrayList<>();

    private ClickListenerHelper<Model> mClickListenerHelper;
    private OnClickListener<Model> mOnClickListener;
    private OnLongClickListener<Model> mOnLongClickListener;

    public BaseAdapter(){
        mClickListenerHelper = new ClickListenerHelper<>(this);
    }

    public void setNew(List<Model> modelList){
        mModelList  = modelList;
        notifyDataSetChanged();
    }

    public void set(int position, Model model){
        mModelList.set(position, model);
        notifyItemChanged(position);
    }

    public void add(Model model){
        mModelList.add(model);
        notifyItemInserted(mModelList.size() - 1);
    }

    public void add(int position, Model model){
        mModelList.add(position, model);
        notifyItemInserted(position);
    }

    @SafeVarargs
    public final void addAll(Model... models) {
        addAll(asList(models));
    }

    public void addAll(List<Model> modelList){
        int countBefore = mModelList.size();
        mModelList.addAll(modelList);
        notifyItemRangeInserted(countBefore,modelList.size());
    }

    public void addAll(int position, List<Model> modelList){
        mModelList.addAll(position, modelList);
        notifyItemRangeInserted(position,modelList.size());
    }

    public void remove(int position){
        mModelList.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int from, int to){
        int count = to - from;
        while(count > 0){
            mModelList.remove(from);
            count--;
        }
        notifyItemRangeRemoved(from, to);
    }

    public void clear(){
        int countBefore = mModelList.size();
        mModelList.clear();
        notifyItemRangeRemoved(0, countBefore);
    }

    public Model getItemAt(int index){
        return mModelList.get(index);
    }

    public int getViewHolderPosition(RecyclerView.ViewHolder viewHolder){
        return viewHolder.getAdapterPosition();
    }

    public BaseAdapter<Model> setOnClickListener(OnClickListener<Model> onClickListener){
        this.mOnClickListener = onClickListener;
        return this;
    }

    public BaseAdapter<Model> setOnLongClickListener(OnLongClickListener<Model> onLongClickListener){
        this.mOnLongClickListener = onLongClickListener;
        return this;
    }

    public BaseAdapter<Model> setItemListener(int viewId, EventListener listener){
        mClickListenerHelper.addEventListener(viewId, listener);
        return this;
    }

    public RecyclerView.ViewHolder onPostCreateViewHolder(final RecyclerView.ViewHolder viewHolder,
                                                          int viewType){
        if(viewType != -1){
            mClickListenerHelper.attachToView(viewHolder);
        }
        if(mOnClickListener != null){
            viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    mOnClickListener.onClick(v, mModelList, mModelList.get(position), position);
                }
            });
        }

        if(mOnLongClickListener!= null){
            viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnLongClickListener.onLongClick(v,mModelList,mModelList.get(position), position);
                }
            });
        }
        return viewHolder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return mModelList .size();
    }


    // Listeners
    public interface OnClickListener<Model> {
        boolean onClick(View v, List<Model> models,Model model, int position);
    }

    public interface OnLongClickListener<Model> {
        boolean onLongClick(View v, List<Model> models,Model model, int position);
    }
}

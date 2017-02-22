package com.project.michael.base.views.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.project.michael.base.views.adapters.EndlessScrollAdapter;

/**
 * Created by michael on 2/1/17.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "tmp-Scroll ";

    private boolean isLoading = false;

    private int mCurrentPage = 1;
    private int mTotalPage = Integer.MAX_VALUE;
    private int mPreviousTotal = -1;
    private int mTotalItem, mVisibleItemCount, mLastVisibleItem, mTreshold;

    private EndlessScrollAdapter endlessScrollAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    public EndlessScrollListener(){

    }

    public EndlessScrollListener(EndlessScrollAdapter endlessScrollAdapter){
        this.endlessScrollAdapter = endlessScrollAdapter;
    }

    public EndlessScrollListener(EndlessScrollAdapter endlessScrollAdapter, int mTreshold){
        this.endlessScrollAdapter = endlessScrollAdapter;
        this.mTreshold = mTreshold;
    }

    public EndlessScrollListener(EndlessScrollAdapter endlessScrollAdapter, int totalPage, int mTreshold){
        this.endlessScrollAdapter = endlessScrollAdapter;
        this.mTreshold = mTreshold;
        this.mTotalPage = totalPage;
    }

    public void LoadMore(int currentPage){
        Log.d(TAG, "LoadMore: " + currentPage);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(mLayoutManager == null)
            mLayoutManager = recyclerView.getLayoutManager();

        int footerItem = 0;
        if(isLoading){
            footerItem = 1;
        }

        mTotalItem = mLayoutManager.getItemCount() - footerItem;
        mVisibleItemCount = recyclerView.getChildCount() - footerItem;
        mLastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();

        if(mPreviousTotal == -1)
            mPreviousTotal = mTotalItem;

        if (isLoading) {
            if (mTotalItem > mPreviousTotal) {
                isLoading = false;
                mPreviousTotal = mTotalItem + 1;
            }
        }

        if(!isLoading && ((mLastVisibleItem + mTreshold ) >= (mTotalItem)) && (mCurrentPage < mTotalPage)){
            isLoading = true;
            mCurrentPage++;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    endlessScrollAdapter.loadMore();
                }
            });
            LoadMore(mCurrentPage);
        }
    }

    public void resetPageCount(int page) {
        mPreviousTotal = 0;
        isLoading = true;
        mCurrentPage = page;
        LoadMore(mCurrentPage);
    }

    public void resetPageCount() {
        resetPageCount(0);
    }
}

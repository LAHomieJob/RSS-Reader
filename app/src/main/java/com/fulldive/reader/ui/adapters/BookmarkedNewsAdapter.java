package com.fulldive.reader.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fulldive.reader.R;
import com.fulldive.reader.data.local.NewsEntity;
import com.fulldive.reader.ui.interfaces.ItemClickListener;
import com.fulldive.reader.ui.viewholders.NewsViewHolder;
import com.fulldive.reader.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class BookmarkedNewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private static final String EMPTY_LIST_TAG = "EMPTY_LIST_TAG";
    LayoutInflater mInflater;
    ItemClickListener onClick;
    private List<NewsEntity> newsItems = new ArrayList<>();

    public BookmarkedNewsAdapter(Context context, ItemClickListener onClick) {
        mInflater = LayoutInflater.from(context);
        this.onClick = onClick;
    }

    public List<NewsEntity> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsEntity> newsItems) {
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View itemView = mInflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView, onClick);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        if (newsItems != null && !newsItems.isEmpty()) {
            NewsEntity currentNews = newsItems.get(position);
            holder.getTitleTextView().setText(currentNews.getTitle());
            String imageUrl = currentNews.getDescription();
            Utilities.loadImage(imageUrl, holder.getImageNewsView());
            if (newsItems.get(position).getBookmark() == 1) {
                holder.getToggleButtonBookmark().setChecked(true);
                //else statement prevents auto toggling
            } else {
                holder.getToggleButtonBookmark().setChecked(false);
            }
            holder.getToggleButtonBookmark().setVisibility(View.GONE);
        } else {
            Log.d(EMPTY_LIST_TAG, "No RSS feeds available");
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }
}

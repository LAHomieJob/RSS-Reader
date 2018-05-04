package com.fulldive.reader.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.fulldive.reader.R;
import com.fulldive.reader.ui.interfaces.ItemClickListener;
import com.fulldive.reader.ui.interfaces.ToggleClickListener;
import com.fulldive.reader.ui.viewholders.NewsViewHolder;

/**
 * Adapter to show all news in a RecyclerView.
 */
public class NewsAdapter extends BookmarkedNewsAdapter {

    private ToggleClickListener onToggle;

    public NewsAdapter(final Context context, final ItemClickListener onClick, ToggleClickListener onToggle) {
        super(context, onClick);
        this.onToggle = onToggle;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View itemView = mInflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView, onClick, onToggle);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.findViewById(R.id.toggle_bookmark).setVisibility(View.VISIBLE);
    }
}

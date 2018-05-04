package com.fulldive.reader.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.fulldive.reader.R;
import com.fulldive.reader.ui.interfaces.ItemClickListener;
import com.fulldive.reader.ui.interfaces.ToggleClickListener;

/**
 * @link{ViewHolder} to display image and title of RSS Feeds Items in RecyclerView
 */
public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView title;
    private ImageView imageNews;

    private ToggleButton mToggleButtonBookmark;
    private ToggleClickListener onToggle;
    private ItemClickListener onClick;

    public NewsViewHolder(View itemView, ItemClickListener onClick, ToggleClickListener onToggle) {
        super(itemView);
        this.onClick = onClick;
        this.onToggle = onToggle;
        title = itemView.findViewById(R.id.text_title);
        imageNews = itemView.findViewById(R.id.image_news);
        mToggleButtonBookmark = itemView.findViewById(R.id.toggle_bookmark);
        itemView.setOnClickListener(this);
        mToggleButtonBookmark.setOnCheckedChangeListener(this);
    }

    public NewsViewHolder(View itemView, ItemClickListener onClick) {
        super(itemView);
        this.onClick = onClick;
        title = itemView.findViewById(R.id.text_title);
        imageNews = itemView.findViewById(R.id.image_news);
        mToggleButtonBookmark = itemView.findViewById(R.id.toggle_bookmark);
        itemView.setOnClickListener(this);
    }

    public ToggleButton getToggleButtonBookmark() {
        return mToggleButtonBookmark;
    }

    public TextView getTitleTextView() {
        return title;
    }

    public ImageView getImageNewsView() {
        return imageNews;
    }

    @Override
    public void onClick(View v) {
        onClick.onItemClick(v, getLayoutPosition());
    }

    @Override
    public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
        onToggle.onToggleBookmarkClick(buttonView, isChecked, getLayoutPosition());
    }
}

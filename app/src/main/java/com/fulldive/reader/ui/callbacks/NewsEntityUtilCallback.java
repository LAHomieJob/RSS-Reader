package com.fulldive.reader.ui.callbacks;

import android.support.v7.util.DiffUtil;

import com.fulldive.reader.data.local.NewsEntity;

import java.util.List;

public class NewsEntityUtilCallback extends DiffUtil.Callback {

    private final List<NewsEntity> oldList;
    private final List<NewsEntity> newList;

    public NewsEntityUtilCallback(List<NewsEntity> oldList, List<NewsEntity> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        NewsEntity oldNewsEntity = oldList.get(oldItemPosition);
        NewsEntity newNewsEntity = newList.get(newItemPosition);
        return oldNewsEntity.getTitle().equals(newNewsEntity.getTitle());
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        NewsEntity oldNewsEntity = oldList.get(oldItemPosition);
        NewsEntity newNewsEntity = newList.get(newItemPosition);
        return oldNewsEntity.getLink().equals(newNewsEntity.getLink())
                && oldNewsEntity.getPubDate().equals(newNewsEntity.getPubDate());
    }
}

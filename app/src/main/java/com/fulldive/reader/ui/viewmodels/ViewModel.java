package com.fulldive.reader.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fulldive.reader.data.local.NewsEntity;
import com.fulldive.reader.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository mRepository;

    public ViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
    }

    public LiveData<List<NewsEntity>> getAllNewsEntities() {
        return mRepository.fetchItems();
    }

    public LiveData<List<NewsEntity>> getAllBookmarkedEntities() {
        return mRepository.getAllBookmarkedNewsEntity();
    }

    public LiveData<NewsEntity> getNewsEntityByTitle(String title) {
        return mRepository.getNewsEntityByTitle(title);
    }

    public void bookmarkNewsEntity(String title) {
        Log.d("TRANSITION", "BookedItem");
        mRepository.bookmarkNewsEntity(title);
    }

    public void removeBookmarkNewsEntity(String title) {
        Log.d("TRANSITION", "UnBookedItem");
        mRepository.removeBookmarkNewsEntity(title);
    }

}
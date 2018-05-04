package com.fulldive.reader.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import com.fulldive.reader.data.local.AppDatabase;
import com.fulldive.reader.data.local.NewsEntity;
import com.fulldive.reader.data.local.NewsEntityDao;
import com.fulldive.reader.data.managers.ApiManager;
import com.fulldive.reader.data.remote.Item;
import com.fulldive.reader.data.services.RssService;
import com.fulldive.reader.utilities.SecurityProvider;
import com.fulldive.reader.utilities.Utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @link{Repository} module is responsible for handling data operations.
 */
public class Repository {
    private NewsEntityDao mNewsEntityDao;
    private ApiManager mApiManager;
    private SecurityProvider mSecurityProvider;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mNewsEntityDao = db.newsEntityDao();
        mApiManager = new ApiManager();
        mSecurityProvider = new SecurityProvider(application);
        mSecurityProvider.initSSLFactory();
    }

    private State checkNetworkState() {
        ConnectivityManager cm =
                (ConnectivityManager) mSecurityProvider.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            return cm.getActiveNetworkInfo().getState();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return State.DISCONNECTED;
        }
    }

    public LiveData<List<NewsEntity>> fetchItems() {
        if (checkNetworkState().equals(State.CONNECTED)) {
            mApiManager.init();
            fetchRemoteItems();
            return mNewsEntityDao.getAllNewsEntity();
        } else {
            return mNewsEntityDao.getAllNewsEntity();
        }
    }

    private void fetchRemoteItems() {

        //Emulates delay for 2 seconds of the first feed
        Observable<Item> feedOne = mApiManager.getNewsService().retrieveItems(RssService.FIRST_URL)
                .delay(2000, TimeUnit.MILLISECONDS)
                .map(rss -> rss.getChannel().getItems())
                .flatMapIterable(listItem -> listItem);

        //Emulates delay for 10 second for the second feed
        Observable<Item> feedTwo = mApiManager.getNewsService().retrieveItems(RssService.SECOND_URL)
                .delay(10000, TimeUnit.MILLISECONDS)
                .map(rss -> rss.getChannel().getItems())
                .flatMapIterable(listItem -> listItem);

        //Cache separate newsItems into database
        Observable.merge(feedOne, feedTwo)
                .subscribeOn(Schedulers.io())
                .subscribe(item -> mNewsEntityDao.insert(Utilities.convertItemToNewsEntity(item)));
    }

    public LiveData<List<NewsEntity>> getAllBookmarkedNewsEntity() {
        return mNewsEntityDao.getAllBookmarkedNewsEntity();
    }

    public LiveData<NewsEntity> getNewsEntityByTitle(String title) {
        return mNewsEntityDao.getNewsEntityByTitle(title);
    }

    public void bookmarkNewsEntity(String title) {
        Completable.fromAction(() -> mNewsEntityDao.bookmarkNewsEntity(title))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void removeBookmarkNewsEntity(String title) {
        Completable.fromAction(() -> mNewsEntityDao.removeBookmarkNewsEntity(title))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}

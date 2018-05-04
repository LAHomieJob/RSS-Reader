package com.fulldive.reader.data.managers;

import com.fulldive.reader.data.contracts.Manager;
import com.fulldive.reader.data.services.RssService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Api Manager which does all network-related work with REST API.
 */
public class ApiManager implements Manager.ApiManager {

    private Retrofit RSS_ADAPTER;
    private RssService RSS_SERVICE;

    @Override
    public RssService getNewsService() {
        return RSS_SERVICE;
    }

    @Override
    public void init() {
        initRetrofit();
        initService();
    }
    /**
     * Initialises Retrofit with a BASE_URL, XML converted and Rx adapter.
     */
    private void initRetrofit() {

        String BASE_URL = "http://feeds.feedburner.com/TechCrunch/";

        RSS_ADAPTER = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private void initService() {
        RSS_SERVICE = RSS_ADAPTER.create(RssService.class);
    }

    @Override
    public void clear() {
        RSS_ADAPTER = null;
        RSS_SERVICE = null;
    }
}

package com.fulldive.reader.data.services;

import com.fulldive.reader.data.remote.RSS;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Interface which describes a contract with the REST API of RSS providers.
 * Also it contains URLs with feeds sources
 */
public interface RssService {

    String FIRST_URL = "http://feeds.feedburner.com/TechCrunch/";
    String SECOND_URL = "https://lifehacker.com/rss/";

    @GET
    Observable<RSS> retrieveItems(@Url String url);
}

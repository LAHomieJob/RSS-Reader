package com.fulldive.reader.data.contracts;

import com.fulldive.reader.data.services.RssService;

/**
 * Contract for all managers in the app.
 */
public interface Manager {
    void init();
    void clear();

    interface ApiManager extends Manager {
        RssService getNewsService();
    }
}

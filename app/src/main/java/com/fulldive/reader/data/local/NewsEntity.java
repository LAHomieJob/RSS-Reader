package com.fulldive.reader.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.fulldive.reader.utilities.DateConverter;

/**
 * Room entity object class for News items from RSS.
 * @Index("pub_date") is used for quicker sorting of RSS feeds in
 * @link(AppDatabase) according to their publication dates
 */
@Entity(indices = {@Index("pub_date")})
public class NewsEntity {

    @ColumnInfo(name = "bookmark")
    private int bookmark;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "pub_date")
    private String pubDate;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "link")
    private String link;

    public NewsEntity(final String pubDate, final String title, final String description, final String link) {
        this.pubDate = pubDate;
        this.title = title;
        this.description = description;
        this.link = link;
        bookmark = 0;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(final String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(final int bookmark) {
        this.bookmark = bookmark;
    }
}

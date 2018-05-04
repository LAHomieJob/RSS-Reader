package com.fulldive.reader.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsEntityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NewsEntity newsEntity);

    @Query("SELECT * FROM newsentity WHERE title = :title")
    LiveData<NewsEntity> getNewsEntityByTitle(String title);

    @Query("SELECT * FROM newsentity ORDER BY pub_date DESC")
    LiveData<List<NewsEntity>> getAllNewsEntity();

    @Query("UPDATE newsentity SET bookmark = 1 WHERE title = :title")
    void bookmarkNewsEntity(String title);

    @Query("UPDATE newsentity SET bookmark = 0 WHERE title = :title")
    void removeBookmarkNewsEntity(String title);

    @Query("SELECT * FROM newsentity WHERE bookmark = 1 ORDER BY pub_date DESC")
    LiveData<List<NewsEntity>> getAllBookmarkedNewsEntity();
}

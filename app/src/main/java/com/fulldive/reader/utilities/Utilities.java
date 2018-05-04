package com.fulldive.reader.utilities;

import android.widget.ImageView;

import com.fulldive.reader.R;
import com.fulldive.reader.data.local.NewsEntity;
import com.fulldive.reader.data.remote.Item;
import com.squareup.picasso.Picasso;

/**
 * Utilities class with helper functions.
 */

public class Utilities {
    private static final String TAG = Utilities.class.getSimpleName();

    public static String parseDescriptionForImageUrl(String description){
        if(description.startsWith("<img src=\"")){
            String[] array = description.split("<img src=\"");
            String temp = array[1];
            int count = temp.indexOf("\"");
            String link = temp.substring(0, count);
            return link;
        } else{
            return description;
        }
    }
    /**
     * Set up downloaded image into the imageView
     * @param description from <description/> node of RSS feed
     */
    public static void loadImage(String description, ImageView imageView){
        Picasso.get()
                .load(Utilities.parseDescriptionForImageUrl(description))
                .fit()
                .centerCrop()
                .error(R.drawable.tech_crunch_logo)
                .into(imageView);
    }

    public static NewsEntity convertItemToNewsEntity(Item item){
        String description = item.getDescription();
        String link = item.getLink();
        String pubDate = item.getPubDate();
        String title = item.getTitle();
        return new NewsEntity(pubDate, title, description, link);
    }
}

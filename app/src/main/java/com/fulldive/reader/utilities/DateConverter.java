package com.fulldive.reader.utilities;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final String TAG = Utilities.class.getSimpleName();
    /**
     * Converts date format EEE, d MMM yyyy HH:mm:ss Z to long
     * @param unformattedDate in a String.
     * @return long representation of the date.
     */
    @TypeConverter
    public static long convertDate(String unformattedDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(unformattedDate);
        } catch (ParseException e) {
            Log.e(TAG, "e:" + e);
        }
        return date.getTime();
    }

    /**
     * Returns formatted date in a String
     * @param unformattedDate in millis
     * @return String formatted in "MMM d, yyyy", Locale aware
     */
    @TypeConverter
    public static String getFormattedDate(long unformattedDate) {
        Date date = new Date(unformattedDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a, MMM d, yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}

package com.fulldive.reader.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fulldive.reader.ui.fragments.BookmarksFragment;
import com.fulldive.reader.ui.fragments.FeedsFragment;

public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    public FixedTabsPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeedsFragment();
            case 1:
                return new BookmarksFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        switch (position) {
            case 0:
                return "Feeds";
            case 1:
                return "Bookmarks";
            default:
                return null;
        }
    }
}

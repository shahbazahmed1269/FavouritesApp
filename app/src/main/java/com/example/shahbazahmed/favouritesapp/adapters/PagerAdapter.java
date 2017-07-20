package com.example.shahbazahmed.favouritesapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shahbazahmed.favouritesapp.fragments.FavouritesFragment;
import com.example.shahbazahmed.favouritesapp.fragments.ItemsFragment;

/**
 * Created by shahbazahmed on 19/07/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ItemsFragment();
                break;
            case 1:
                fragment = new FavouritesFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "List";
                break;
            case 1:
                title = "Favourites";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

package com.example.project_2_crossword_magic.view;

import android.util.Log;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project_2_crossword_magic.model.dao.PuzzleDAO;

public class TabLayoutAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 2;
    private final String TAG = "TabLayoutAdapter Mine";

    // Constructor
    public TabLayoutAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        Fragment fragment = new Fragment();

        switch(position) {
            case 0:
                Log.d(TAG, "Case 0");
                fragment = new PuzzleFragment();
                break;
            case 1:
                Log.d(TAG, "Case 1");
                fragment = new ClueFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
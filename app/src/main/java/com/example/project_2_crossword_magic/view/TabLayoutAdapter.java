package com.example.project_2_crossword_magic.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 2;

    public TabLayoutAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();

        switch(position) {
            case 0:
                fragment = new PuzzleFragment();
                break;
            case 1:
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
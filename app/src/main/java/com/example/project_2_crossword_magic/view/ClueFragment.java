package com.example.project_2_crossword_magic.view;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project_2_crossword_magic.R;
import com.example.project_2_crossword_magic.databinding.FragmentClueBinding;

public class ClueFragment  extends Fragment {
    private final String TAG = "ClueFragment Mine";
    private FragmentClueBinding clueBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreate()");
        clueBinding = FragmentClueBinding.inflate(getLayoutInflater(),
                container, false);

        return (ViewGroup) inflater.inflate(R.layout.fragment_clue, null);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroy()");
        super.onDestroyView();
        clueBinding = null;
    }

    private void initClue(View view) {
        Log.d(TAG, "initClue()");
    }
}
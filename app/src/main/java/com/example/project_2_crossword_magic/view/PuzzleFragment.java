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
import com.example.project_2_crossword_magic.databinding.FragmentPuzzleBinding;

public class PuzzleFragment extends Fragment {
    private final String TAG = "PuzzleFragment Mine";
    private FragmentPuzzleBinding puzzleBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreate()");
        puzzleBinding = FragmentPuzzleBinding.inflate(getLayoutInflater(),
                container, false);

        return (ViewGroup) inflater.inflate(R.layout.fragment_puzzle, null);
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroy()");
        super.onDestroyView();
        puzzleBinding = null;
    }

    private void initPuzzle(View view) {
        Log.d(TAG, "initPuzzle()");
    }
}
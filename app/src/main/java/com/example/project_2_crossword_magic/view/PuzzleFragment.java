package com.example.project_2_crossword_magic.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.beans.PropertyChangeEvent;

import com.example.project_2_crossword_magic.databinding.FragmentPuzzleBinding;
import com.example.project_2_crossword_magic.controller.CrosswordMagicController;

public class PuzzleFragment extends Fragment implements AbstractView {
    private CrosswordMagicController controller;
    private FragmentPuzzleBinding puzzleBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        puzzleBinding = FragmentPuzzleBinding.inflate(getLayoutInflater(), container, false);
        return puzzleBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.controller = ((MainActivity)getContext()).getController();
        controller.addView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        puzzleBinding = null;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {}
}
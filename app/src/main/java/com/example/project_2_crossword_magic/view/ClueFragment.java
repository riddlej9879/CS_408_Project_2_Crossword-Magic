package com.example.project_2_crossword_magic.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.beans.PropertyChangeEvent;

import com.example.project_2_crossword_magic.controller.CrosswordMagicController;
import com.example.project_2_crossword_magic.databinding.FragmentClueBinding;

public class ClueFragment extends Fragment implements AbstractView {
    private CrosswordMagicController controller;
    private FragmentClueBinding clueBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                 ViewGroup container,  Bundle savedInstanceState) {
        clueBinding = FragmentClueBinding.inflate(getLayoutInflater(), container, false);
        return clueBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        this.controller = ((MainActivity)getContext()).getController();
        controller.addView(this);

        controller.getCluesDown();
        controller.getCluesAcross();
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        Object value = evt.getNewValue();

        if (name.equals(CrosswordMagicController.CLUES_ACROSS_PROPERTY)) {
            if (value instanceof String) {
                clueBinding.acrossContainer.setText(value.toString());
            }
        }
        if (name.equals(CrosswordMagicController.CLUES_DOWN_PROPERTY)) {
            if (value instanceof String) {
                clueBinding.downContainer.setText(value.toString());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clueBinding = null;
    }
}
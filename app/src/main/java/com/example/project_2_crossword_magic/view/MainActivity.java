package com.example.project_2_crossword_magic.view;

import android.util.Log;
import android.view.View;
import android.os.Bundle;

import java.beans.PropertyChangeEvent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_crossword_magic.model.*;
import com.example.project_2_crossword_magic.model.CrosswordMagicModel;
import com.example.project_2_crossword_magic.databinding.ActivityMainBinding;
import com.example.project_2_crossword_magic.controller.CrosswordMagicController;

public class MainActivity extends AppCompatActivity implements AbstractView {
    private ActivityMainBinding binding;
    private CrosswordMagicController controller;
    private final String TAG = "MainActivity Mine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle)");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */
        controller = new CrosswordMagicController();
        CrosswordMagicModel model = new CrosswordMagicModel(this);

        /* Register View(s) and Model(s) with Controller */
        controller.addModel(model);
        controller.addView(this);

        /* Get Test Property (tests MVC framework) */
        controller.getTestProperty(CrosswordMagicController.TEST_PROPERTY);
    }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {
        Log.d(TAG, "modelPropertyChange(evt)");
        String name = evt.getPropertyName();
        String value = evt.getNewValue().toString();

        Log.d(TAG, "Name: " + name + "; Value: " + value);

        if (name.equals(CrosswordMagicController.TEST_PROPERTY)) {
            binding.output.setText("Number of Words in Default Puzzle: " + value);
        }
    }
}
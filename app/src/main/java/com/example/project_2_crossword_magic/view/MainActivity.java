package com.example.project_2_crossword_magic.view;

import android.view.View;
import android.os.Bundle;

import java.beans.PropertyChangeEvent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_crossword_magic.model.CrosswordMagicModel;
import com.example.project_2_crossword_magic.databinding.ActivityMainBinding;
import com.example.project_2_crossword_magic.controller.CrosswordMagicController;

public class MainActivity extends AppCompatActivity implements AbstractView {
    private ActivityMainBinding binding;
    private CrosswordMagicController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */
        controller = new CrosswordMagicController();

        CrosswordMagicModel model = new CrosswordMagicModel(this);

        /* Register View(s) and Model(s) with Controller */
        controller.addModel(model);
        controller.addView(this);

        /* Init Model */
        controller.getTestProperty(CrosswordMagicController.TEST_PROPERTY);
    }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {}

    public CrosswordMagicController getController() {
        return controller;
    }
}
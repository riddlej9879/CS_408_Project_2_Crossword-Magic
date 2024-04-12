package com.example.project_2_crossword_magic.model;

import android.util.Log;
import android.content.Context;

import com.example.project_2_crossword_magic.model.dao.PuzzleDAO;
import com.example.project_2_crossword_magic.model.dao.DAOFactory;
import com.example.project_2_crossword_magic.controller.CrosswordMagicController;

public class CrosswordMagicModel extends AbstractModel {
    private final int DEFAULT_PUZZLE_ID = 1;
    private Puzzle puzzle;

    private final String TAG = "MagicModel Mine";

    public CrosswordMagicModel(Context context) {
        Log.d(TAG, "Constructor(context) [Def_Puz_ID: 1");
        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);
    }

    public void getTestProperty() {
        Log.d(TAG, "getTestProp()");
        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);
    }
}
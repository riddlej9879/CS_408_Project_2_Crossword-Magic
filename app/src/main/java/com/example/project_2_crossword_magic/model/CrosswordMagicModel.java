package com.example.project_2_crossword_magic.model;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import com.example.project_2_crossword_magic.R;
import com.example.project_2_crossword_magic.model.dao.GuessDAO;
import com.example.project_2_crossword_magic.model.dao.PuzzleDAO;
import com.example.project_2_crossword_magic.model.dao.DAOFactory;
import com.example.project_2_crossword_magic.controller.CrosswordMagicController;

public class CrosswordMagicModel extends AbstractModel {
    private final int DEFAULT_PUZZLE_ID = 1;
    private Puzzle puzzle;


    private String userInput;
    private int selectedBox;

    private DAOFactory daoFactory;

    public CrosswordMagicModel(Context context) {
        this.daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);
    }

    public void getCluesAcross() {
        firePropertyChange(CrosswordMagicController.CLUES_ACROSS_PROPERTY, null, puzzle.getCluesAcross());
    }

    public void getCluesDown() {
        firePropertyChange(CrosswordMagicController.CLUES_DOWN_PROPERTY, null, puzzle.getCluesDown());
    }

    public void getGridLetters() {
        firePropertyChange(CrosswordMagicController.GRID_LETTERS_PROPERTY, null, puzzle.getLetters());
    }

    public void getGridNumbers() {
        firePropertyChange(CrosswordMagicController.GRID_NUMBERS_PROPERTY, null, puzzle.getNumbers());
    }

    public void getGridDimensions() {


        Integer[] dimensions = {puzzle.getHeight(), puzzle.getWidth()};



        firePropertyChange(CrosswordMagicController.GRID_DIMENSIONS_PROPERTY, null, dimensions);
    }

    public void getTestProperty() {
        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);
    }

    public void setGuess(HashMap<String, String> params) {
        Log.d("Guess", params.get("guess").toUpperCase().trim());
        if (params != null) {
            Integer num = Integer.parseInt(params.get("num"));
            String guess = params.get("guess").toUpperCase().trim();

            WordDirection result = puzzle.checkGuess(num, guess);

            if (result == null) {
                firePropertyChange(CrosswordMagicController.GUESS_PROPERTY,
                        null, R.string.guess_incorrect);
            }
            else {
                String key = num.toString() + result.toString();
                Word word = puzzle.getWord(key);
                int wordid = word.getId();
                int puzzleid = word.getPuzzleId();

                GuessDAO puzzleDao = daoFactory.getGuessDAO();
                puzzleDao.create(puzzleid, wordid);

                firePropertyChange(CrosswordMagicController.GUESS_PROPERTY, null, R.string.guess_correct);
            }
        }
    }

    public void setUserInput(String userInput) {
        String oldValue = this.userInput;
        this.userInput = userInput;
        firePropertyChange(CrosswordMagicController.USER_INPUT_PROPERTY, oldValue, userInput);
    }

    public void setSelectedBox(int selectedBox) {
        int oldValue = this.selectedBox;
        this.selectedBox = selectedBox;
        firePropertyChange(CrosswordMagicController.SELECTED_PROPERTY, oldValue, selectedBox);
    }
}
package com.example.project_2_crossword_magic.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Objects;

public class Word {
    private Integer id, puzzleId, row, column, box;
    private WordDirection direction;
    private String word, clue;

    private final String TAG = "find Mine";

    public Word(HashMap<String, String> params) {
        Log.d(TAG, "Constructor");
        try {
            this.id = Integer.parseInt(Objects.requireNonNull(params.get("_id")));
            this.puzzleId = Integer.parseInt(Objects.requireNonNull(params.get("puzzleid")));
            this.row = Integer.parseInt(Objects.requireNonNull(params.get("row")));
            this.column = Integer.parseInt(Objects.requireNonNull(params.get("column")));
            this.box = Integer.parseInt(Objects.requireNonNull(params.get("box")));
            this.word = params.get("word");
            this.clue = params.get("clue");

            this.direction = WordDirection.values()[Integer.parseInt(Objects.requireNonNull(params.get("direction")))];
        }
        catch (Exception e) {
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public boolean isAcross() {
        Log.d(TAG, "isAcross()");
        return direction.equals(WordDirection.ACROSS);
    }

    public boolean isDown() {
        Log.d(TAG, "isDown()");
        return direction.equals(WordDirection.DOWN);
    }

    public Integer  getId() {
        Log.d(TAG, "getId()");
        return id;
    }

    public Integer getPuzzleId() {
        Log.d(TAG, "getPuzzleId()");
        return puzzleId;
    }

    public Integer getRow() {
        Log.d(TAG, "getRow()");
        return row;
    }

    public Integer getColumn() {
        Log.d(TAG, "getColumn()");
        return column;
    }

    public Integer getBox() {
        Log.d(TAG, "getBox()");
        return box;
    }

    public WordDirection getDirection() {
        Log.d(TAG, "getDirection()");
        return direction;
    }

    public String getWord() {
        Log.d(TAG, "getWord()");
        return word;
    }

    public String getClue() {
        Log.d(TAG, "getClue()");
        return clue;
    }
}
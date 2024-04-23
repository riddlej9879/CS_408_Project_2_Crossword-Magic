package com.example.project_2_crossword_magic.model;

import java.util.HashMap;
import java.util.Objects;

public class Word {
    private Integer id, puzzleId, row, column, box;
    private WordDirection direction;
    private String word, clue;

    public Word(HashMap<String, String> params) {
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
            e.printStackTrace();
        }
    }

    public boolean isAcross() {
        return direction.equals(WordDirection.ACROSS);
    }

    public boolean isDown() {
        return direction.equals(WordDirection.DOWN);
    }

    public Integer  getId() {
        return id;
    }

    public Integer getPuzzleId() {
        return puzzleId;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getBox() {
        return box;
    }

    public WordDirection getDirection() {
        return direction;
    }

    public String getWord() {
        return word;
    }

    public String getClue() {
        return clue;
    }
}
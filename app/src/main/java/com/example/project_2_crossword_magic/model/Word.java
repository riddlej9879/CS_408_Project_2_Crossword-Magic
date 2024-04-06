package com.example.project_2_crossword_magic.model;

import java.util.HashMap;

public class Word {
    private Integer id, puzzleId, row, column, box;
    private WordDirection direction;
    private String word, clue;

    public Word(HashMap<String, String> params) {
        try {
            this.id = Integer.parseInt(params.get("_id"));
            this.puzzleId = Integer.parseInt(params.get("puzzleId"));
            this.row = Integer.parseInt(params.get("row"));
            this.column = Integer.parseInt(params.get("column"));
            this.box = Integer.parseInt(params.get("box"));
            this.word = params.get("word");
            this.clue = params.get("clue");

            this.direction = WordDirection.values()[Integer.parseInt(params.get("direction"))];
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

    public Integer getId() {
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
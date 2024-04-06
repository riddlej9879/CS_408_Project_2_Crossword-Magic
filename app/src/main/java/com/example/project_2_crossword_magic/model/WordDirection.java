package com.example.project_2_crossword_magic.model;

public enum WordDirection {

    ACROSS("A"),
    DOWN("D");
    private String message;

    private WordDirection(String msg) { message = msg; }

    @Override
    public String toString() { return message; }

}
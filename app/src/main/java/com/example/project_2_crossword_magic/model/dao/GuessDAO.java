package com.example.project_2_crossword_magic.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class GuessDAO {
    private final DAOFactory daoFactory;

    GuessDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(int puzzleId, int wordId) {
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, puzzleId, wordId);
        db.close();

        return result;
    }

    public int create(SQLiteDatabase db, int puzzleId, int wordId) {
        /* use this method if there IS already a SQLiteDatabase open */
        String puzzleIdField = daoFactory.getProperty("sql_field_puzzleid");
        String wordIdField = daoFactory.getProperty("sql_field_wordid");

        ContentValues values = new ContentValues();
        values.put(puzzleIdField, puzzleId);
        values.put(wordIdField, wordId);

        int key = (int)db.insert(daoFactory.getProperty("sql_table_guesses"), null, values);

        return key;
    }
}
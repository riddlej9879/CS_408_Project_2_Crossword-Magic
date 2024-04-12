package com.example.project_2_crossword_magic.model.dao;

import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import java.util.HashMap;
import java.util.Objects;

import com.example.project_2_crossword_magic.model.Word;
import com.example.project_2_crossword_magic.model.Puzzle;
import com.example.project_2_crossword_magic.model.WordDirection;

public class PuzzleDAO {
    private final DAOFactory daoFactory;
    private final String TAG = "PuzzleDao Mine";

    PuzzleDAO(DAOFactory daoFactory) {
        Log.d(TAG, "Constructor(daoFactory)");
        this.daoFactory = daoFactory;
    }

    public int create(HashMap<String, String> params) {
        /* use this method if there is NOT already a SQLiteDatabase open */
        Log.d(TAG, "create(Hashmap<>)");
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, params);
        db.close();

        return result;
    }

    public int create(SQLiteDatabase db, HashMap<String, String> params) {
        /* use this method if there IS already a SQLiteDatabase open */
        Log.d(TAG, "create(SQLiteDb, Hashmap)");
        String name = daoFactory.getProperty("sql_field_name");
        String description = daoFactory.getProperty("sql_field_description");
        String height = daoFactory.getProperty("sql_field_height");
        String width = daoFactory.getProperty("sql_field_width");

        ContentValues values = new ContentValues();
        values.put(name, params.get(name));
        values.put(description, params.get(description));
        values.put(height, Integer.parseInt(Objects.requireNonNull(params.get(height))));
        values.put(width, Integer.parseInt(Objects.requireNonNull(params.get(width))));

        return (int)db.insert(daoFactory.getProperty("sql_table_puzzles"), null, values);
    }

    public Puzzle find(int id) {
        /* use this method if there is NOT already a SQLiteDatabase open */
        Log.d(TAG, "find(id), id: " + id);
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        Puzzle result = find(db, id);
        db.close();

        return result;
    }

    public Puzzle find(SQLiteDatabase db, int id) {
        /* use this method if there is already a SQLiteDatabase open */
        Log.d(TAG, "find(db,id): " + db.toString());

        Puzzle puzzle = null;
        String query = daoFactory.getProperty("sql_get_puzzle");
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            HashMap<String, String> params = new HashMap<>();
            //Log.d(TAG, params.toString());

            /* get data for new puzzle */
            /* INSERT YOUR CODE HERE */
            params.put(daoFactory.getProperty("sql_field_puzzleid"),
                    String.valueOf(cursor.getString(0)));
            params.put(daoFactory.getProperty("sql_field_name"),
                    String.valueOf(cursor.getString(1)));
            params.put(daoFactory.getProperty("sql_field_description"),
                    String.valueOf(cursor.getString(2)));
            params.put(daoFactory.getProperty("sql_field_height"),
                    String.valueOf(cursor.getString(3)));
            params.put(daoFactory.getProperty("sql_field_width"),
                    String.valueOf(cursor.getString(4)));

            if (!params.isEmpty())
                puzzle = new Puzzle(params);

            /* get list of words (if any) to add to puzzle */
            query = daoFactory.getProperty("sql_get_words");
            cursor = db.rawQuery(query, new String[]{ String.valueOf(id) });

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                do {
                    params = new HashMap<>();

                    /* get data for the next word in the puzzle */
                    /* INSERT YOUR CODE HERE */
                    params.put(daoFactory.getProperty("sql_field_id"),
                            String.valueOf(cursor.getString(0)));
                    params.put(daoFactory.getProperty("sql_field_puzzleid"),
                            String.valueOf(cursor.getString(1)));
                    params.put(daoFactory.getProperty("sql_field_row"),
                            String.valueOf(cursor.getString(2)));
                    params.put(daoFactory.getProperty("sql_field_column"),
                            String.valueOf(cursor.getString(3)));
                    params.put(daoFactory.getProperty("sql_field_box"),
                            String.valueOf(cursor.getString(4)));
                    params.put(daoFactory.getProperty("sql_field_direction"),
                            String.valueOf(cursor.getString(5)));
                    params.put(daoFactory.getProperty("sql_field_word"),
                            cursor.getString(6));
                    params.put(daoFactory.getProperty("sql_field_clue"),
                            cursor.getString(7));

                    if (!params.isEmpty()) {
                        puzzle.addWordToPuzzle(new Word(params));
                    }
                }
                while (cursor.moveToNext());

                cursor.close();
            }

            /* get list of already-guessed words (if any) from "guesses" table */
            query = daoFactory.getProperty("sql_get_guesses");
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                do {
                    Integer box = cursor.getInt(4);
                    WordDirection direction = WordDirection.values()[cursor.getInt(5)];

                    if (puzzle != null)
                        puzzle.addWordToGuessed(box + direction.toString());
                }
                while ( cursor.moveToNext() );

                cursor.close();
            }
        }

        return puzzle;
    }
}
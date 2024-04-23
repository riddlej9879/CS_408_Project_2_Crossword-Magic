package com.example.project_2_crossword_magic.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.util.List;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.project_2_crossword_magic.R;

public class DAOFactory extends SQLiteOpenHelper {
    private final Context context;
    private final DAOProperties properties;

    private static final String DATABASE_NAME = "cwmagic.db";

    private static final int DATABASE_VERSION = 1;
    private static final int CSV_HEADER_FIELDS = 4;
    private static final int CSV_DATA_FIELDS = 6;

    public DAOFactory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        properties = new DAOProperties(context, DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(properties.getProperty("sql_create_puzzles_table"));
        db.execSQL(properties.getProperty("sql_create_words_table"));
        db.execSQL(properties.getProperty("sql_create_guesses_table"));

        addInitialDataFromCSV(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(properties.getProperty("sql_drop_guesses_table"));
        db.execSQL(properties.getProperty("sql_drop_words_table"));
        db.execSQL(properties.getProperty("sql_drop_puzzles_table"));

        onCreate(db);
    }

    public PuzzleDAO getPuzzleDAO() {
        return new PuzzleDAO(this);
    }

    public WordDAO getWordDAO() {
        return new WordDAO(this);
    }

    public GuessDAO getGuessDAO() {
        return new GuessDAO(this);
    }

    public String getProperty(String key) {
        return (properties.getProperty(key));
    }

    public void addInitialDataFromCSV(SQLiteDatabase db) {
        int puzzleid = 0;

        /* populate initial database from CSV data file */
        try {
            WordDAO wordDAO = getWordDAO();
            PuzzleDAO puzzleDAO = getPuzzleDAO();

            BufferedReader br = new BufferedReader(new
                    InputStreamReader(context.getResources().openRawResource(R.raw.puzzle)));
            CSVParser parser = (new CSVParserBuilder()).withSeparator('\t').withIgnoreQuotations(true).build();
            CSVReader reader = (new CSVReaderBuilder(br)).withCSVParser(parser).build();

            List<String[]> csv = reader.readAll();
            String[] fields = csv.get(0);
            HashMap<String, String> params;

            if (fields.length == CSV_HEADER_FIELDS) {
                params = new HashMap<>();

                params.put(properties.getProperty("sql_field_name"), fields[0]);
                params.put(properties.getProperty("sql_field_description"), fields[1]);

                params.put(properties.getProperty("sql_field_height"), fields[2]);
                params.put(properties.getProperty("sql_field_width"), fields[3]);

                puzzleid = puzzleDAO.create(db, params);

                for (int i = 1; i < csv.size(); ++i) {
                    fields = csv.get(i);

                    if (fields.length == CSV_DATA_FIELDS) {
                        params = new HashMap<>();

                        /*  INSERT YOUR CODE HERE  */
                        params.put(properties.getProperty("sql_field_puzzleid"), String.valueOf(puzzleid));
                        params.put(properties.getProperty("sql_field_row"), fields[0]);
                        params.put(properties.getProperty("sql_field_column"), fields[1]);
                        params.put(properties.getProperty("sql_field_box"), fields[2]);
                        params.put(properties.getProperty("sql_field_direction"), fields[3]);
                        params.put(properties.getProperty("sql_field_word"), fields[4]);
                        params.put(properties.getProperty("sql_field_clue"), fields[5]);

                        wordDAO.create(db, params);
                    }
                }
            }

            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
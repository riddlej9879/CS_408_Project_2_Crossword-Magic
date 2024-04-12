package com.example.project_2_crossword_magic.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import android.util.Log;

public class Puzzle {
    public static final char BLOCK_CHAR = '*';
    public static final char BLANK_CHAR = ' ';

    private final HashMap<String, Word> words;
    private final HashSet<String> guessed;

    private final String name, description;
    private final Integer height, width;

    private final Character[][] letters;
    private final Integer[][] numbers;

    private boolean solved = false;

    private final StringBuilder cluesAcrossBuffer, cluesDownBuffer;
    private final String TAG = "Puzzle.java Mine";

    public Puzzle(HashMap<String, String> params) {
        Log.d(TAG, "Constructor");
        this.name = params.get("name");
        this.description = params.get("description");
        this.height = Integer.parseInt(Objects.requireNonNull(params.get("height")));
        this.width = Integer.parseInt(Objects.requireNonNull(params.get("width")));

        guessed = new HashSet<>();
        words = new HashMap<>();

        letters = new Character[height][width];
        numbers = new Integer[height][width];

        cluesAcrossBuffer = new StringBuilder();
        cluesDownBuffer = new StringBuilder();

        /* fill initial grids with solid zeroes and blocks */
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                letters[i][j] = BLOCK_CHAR;
                numbers[i][j] = 0;
            }
        }
    }

    public void addWordToPuzzle(Word word) {
        Log.d("find", "addWordToPuzzle(word)");
        String key = (word.getBox() + word.getDirection().toString());

        /* add to collection */
        words.put(key, word);

        /* get word properties */
        int row = word.getRow();
        int column = word.getColumn();
        int length = word.getWord().length();

        /* add box number to grid of numbers */
        numbers[row][column] = word.getBox();

        /* "hollow out" letters; replace with blanks */
        for (int i = 0; i < length; ++i) {
            letters[row][column] = BLANK_CHAR;

            if (word.isAcross())
                column++;
            else if (word.isDown())
                row++;
        }

        /* append clue (across or down) to corresponding StringBuilder */
        if (word.isAcross()) {
            Log.d(TAG, "if (word.isAcross())");
            cluesAcrossBuffer.append(word.getBox()).append(": ");
            cluesAcrossBuffer.append(word.getClue()).append(System.lineSeparator());
        }
        else if (word.isDown()) {
            Log.d(TAG, "else if (word.isDown())");
            cluesDownBuffer.append(word.getBox()).append(": ");
            cluesDownBuffer.append(word.getClue()).append(System.lineSeparator());
        }

        /* add word to guessed list (for development only!) */
        addWordToGuessed(key); // remove this later!
    }

    public WordDirection checkGuess(Integer num, String guess) {
        Log.d(TAG, "checkGuess(num, guess");
        WordDirection result = null;

        String acrossKey = num + WordDirection.ACROSS.toString();
        String downKey = num + WordDirection.DOWN.toString();

        /* get the words across and down in the selected box */
        Word across = words.get(acrossKey);
        Word down = words.get(downKey);

        /* compare guess to both words; if a match is found, add word to guessed list */
        if (across != null) {
            if (across.getWord().equals(guess) && !(guessed.contains(acrossKey))) {
                result = WordDirection.ACROSS;
                addWordToGuessed(downKey);
            }
        }

        if (down != null) {
            if (down.getWord().equals(guess) && !(guessed.contains(downKey))) {
                result = WordDirection.DOWN;
                addWordToGuessed(downKey);
            }
        }

        /* check if any blank squares remain after guess; if not, the puzzle is solved */
        this.solved = true;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (letters[i][j] == BLANK_CHAR) {
                    this.solved = false;
                }
            }
        }

        /* return direction of guessed word (across, down, or null for a wrong guess) */
        return result;
    }

    public void addWordToGuessed(String key) {
        Log.d(TAG, "addWordToGuessed(key");
        Word w = words.get(key);
        guessed.add(key);

        /* get word properties */
        int row = w.getRow();
        int column = w.getColumn();
        String word = w.getWord();
        int length = word.length();

        /* place letters in letter grid */
        for (int i = 0; i < length; ++i) {
            letters[row][column] = word.charAt(i);

            if (w.isAcross())
                column++;
            else if (w.isDown())
                row++;
        }
    }

    public Word getWord(String key) {
        Log.d(TAG, "getWord(key)");
        return words.get(key);
    }

    public String getName() {
        Log.d(TAG, "getName()");
        return name;
    }

    public String getDescription() {
        Log.d(TAG, "getDescription()");
        return description;
    }

    public Integer getWidth() {
        Log.d(TAG, "getWidth()");
        return width;
    }

    public Integer getHeight() {
        Log.d(TAG, "getHeight()");
        return height;
    }

    public String getCluesAcross() {
        Log.d(TAG, "getCluesAcross()");
        return cluesAcrossBuffer.toString();
    }

    public String getCluesDown() {
        Log.d(TAG, "getCluesDown()");
        return cluesDownBuffer.toString();
    }

    public int getSize() {
        Log.d(TAG, "getSize(): " + words.size());
        return words.size();
    }

    public Character[][] getLetters() {
        Log.d(TAG, "getLetters()");
        return letters;
    }

    public Integer[][] getNumbers() {
        Log.d(TAG, "getNumbers()");
        return numbers;
    }

    public boolean isSolved() {
        Log.d(TAG, "isSolved()");
        return solved;
    }
}
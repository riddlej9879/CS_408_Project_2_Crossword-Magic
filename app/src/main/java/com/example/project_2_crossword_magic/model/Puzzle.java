package com.example.project_2_crossword_magic.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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

    public Puzzle(HashMap<String, String> params) {
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

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                letters[i][j] = BLOCK_CHAR;
                numbers[i][j] = 0;
            }
        }
    }

    public void addWordToPuzzle(Word word) {
        String key = (word.getBox() + word.getDirection().toString());

        words.put(key, word);

        int row = word.getRow();
        int column = word.getColumn();
        int length = word.getWord().length();

        // Add number to grid
        numbers[row][column] = word.getBox();

        // Replace black squares with white squares
        for (int i = 0; i < length; ++i) {
            letters[row][column] = BLANK_CHAR;

            if (word.isAcross())
                column++;
            else if (word.isDown())
                row++;
        }

        if (word.isAcross()) {
            cluesAcrossBuffer.append(word.getBox()).append(": ");
            cluesAcrossBuffer.append(word.getClue()).append(System.lineSeparator());
        }
        else if (word.isDown()) {
            cluesDownBuffer.append(word.getBox()).append(": ");
            cluesDownBuffer.append(word.getClue()).append(System.lineSeparator());
        }
        // addWordToGuessed(key); // Fills in crossword puzzle
    }

    public WordDirection checkGuess(Integer num, String guess) {
        String acrossKey = num + WordDirection.ACROSS.toString();
        String downKey = num + WordDirection.DOWN.toString();
        WordDirection result = null;

        // Check if words match guess
        Word across = words.get(acrossKey);
        Word down = words.get(downKey);
        if (across != null) {
            if (across.getWord().equals(guess) && !(guessed.contains(acrossKey))) {
                result = WordDirection.ACROSS;
                addWordToGuessed(acrossKey);
            }
        }
        if (down != null) {
            if (down.getWord().equals(guess) && !(guessed.contains(downKey))) {
                result = WordDirection.DOWN;
                addWordToGuessed(downKey);
            }
        }

        this.solved = true;

        // Check if not solved
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (letters[i][j] == BLANK_CHAR) {
                    this.solved = false;
                }
            }
        }

        // Return direction of guessed word
        return result;
    }

    public void addWordToGuessed(String key) {
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
        return words.get(key);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getCluesAcross() {
        return cluesAcrossBuffer.toString();
    }

    public String getCluesDown() {
        return cluesDownBuffer.toString();
    }

    public int getSize() {
        return words.size();
    }

    public Character[][] getLetters() {
        return letters;
    }

    public Integer[][] getNumbers() {
        return numbers;
    }

    public boolean isSolved() {
        return solved;
    }
}
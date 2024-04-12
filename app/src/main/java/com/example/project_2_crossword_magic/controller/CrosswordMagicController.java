package com.example.project_2_crossword_magic.controller;

import android.util.Log;

public class CrosswordMagicController extends AbstractController {
    public static final String TEST_PROPERTY = "TestProperty";

    public static final String TAG = "MagicController Mine";

    public void getTestProperty(String value) {
        Log.d(TAG, "getTestProperty(value) :" +value);
        getModelProperty(TEST_PROPERTY);
    }
}
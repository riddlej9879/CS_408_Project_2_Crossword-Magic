package com.example.project_2_crossword_magic.model.dao;

import android.util.Log;
import android.content.Context;

import java.io.InputStreamReader;
import java.util.Properties;

import com.example.project_2_crossword_magic.R;

public class DAOProperties {
    private static final Properties PROPERTIES = new Properties();
    private final String prefix;

    private final String TAG = "DAOProperties Mine";

    DAOProperties(Context context, String prefix) {
        InputStreamReader file = new InputStreamReader(context.getResources()
                .openRawResource(R.raw.dao));
        Log.d(TAG, "Constructor(context, prefix)");

        try {
            PROPERTIES.load(file);
        }
        catch (Exception e) { e.printStackTrace(); }

        this.prefix = prefix;
    }

    String getProperty(String key) {
        String fullKey = prefix + "." + key;
        String property = PROPERTIES.getProperty(fullKey);
        Log.d(TAG, "getProperty(key), key: " + key);

        if (property == null || property.trim().length() == 0)
            property = null;
        return property;
    }
}
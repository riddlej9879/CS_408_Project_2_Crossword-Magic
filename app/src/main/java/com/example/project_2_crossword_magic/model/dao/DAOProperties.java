package com.example.project_2_crossword_magic.model.dao;

import android.content.Context;

import java.io.InputStreamReader;
import java.util.Properties;

import com.example.project_2_crossword_magic.R;

public class DAOProperties {
    private static final Properties PROPERTIES = new Properties();
    private final String prefix;

    DAOProperties(Context context, String prefix) {
        InputStreamReader file = new InputStreamReader(context.getResources().openRawResource(R.raw.dao));

        try {
            PROPERTIES.load(file);
        }
        catch (Exception e) { e.printStackTrace(); }

        this.prefix = prefix;
    }

    String getProperty(String key) {
        String fullKey = prefix + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0)
            property = null;
        return property;
    }
}
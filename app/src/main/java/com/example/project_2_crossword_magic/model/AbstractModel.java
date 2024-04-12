package com.example.project_2_crossword_magic.model;

import android.util.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel {
    protected PropertyChangeSupport propertyChangeSupport;

    private final String TAG = "AbstractModel Mine";

    public AbstractModel() {
        Log.d(TAG, "Constructor()");
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        Log.d(TAG, "addPropertyChangeListener(listener), list: " + listener.toString());
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        Log.d(TAG, "removePropertyChangeListener");
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        Log.d(TAG, "firePropertyChange(propName, oldVal, newVal), PN:" + propertyName
                + ", OV:" + oldValue + ", NV:" + newValue);
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
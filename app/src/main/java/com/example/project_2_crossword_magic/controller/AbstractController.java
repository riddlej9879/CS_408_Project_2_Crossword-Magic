package com.example.project_2_crossword_magic.controller;

import android.util.Log;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.example.project_2_crossword_magic.view.AbstractView;
import com.example.project_2_crossword_magic.model.AbstractModel;

public abstract class AbstractController implements PropertyChangeListener {
    private ArrayList<AbstractView> views;
    private ArrayList<AbstractModel> models;

    private final String TAG = "AbstractController Mine";

    public AbstractController() {
        Log.d(TAG, "Constructor()");
        views = new ArrayList<>();
        models = new ArrayList<>();
    }

    public void addModel(AbstractModel model) {
        Log.d(TAG, "addModel(model), model: " + model.toString());
        models.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        Log.d(TAG, "removeModel(model)");
        models.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        Log.d(TAG, "addView(view), view: " + view.toString());
        views.add(view);
    }

    public void removeView(AbstractView view) {
        Log.d(TAG, "removeView(view)");
        views.remove(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Log.d(TAG, "propertyChange(evt), evt: " + evt.toString());
        for (AbstractView view : views) {
            view.modelPropertyChange(evt);
        }
    }

    protected void setModelProperty(String propertyName, Object newValue) {
        Log.d(TAG, "setModelProperty(propName, newVal)");
        for (AbstractModel model : models) {
            try {
                Method method = model.getClass().getMethod("set" + propertyName, newValue.getClass());
                method.invoke(model, newValue);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void getModelProperty(String methodName) {
        Log.d(TAG, "getModelProperty(methodName), methodName: " + methodName);
        for (AbstractModel model : models) {
            try {
                Method method = model.getClass().getMethod("get" + methodName);
                method.invoke(model);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
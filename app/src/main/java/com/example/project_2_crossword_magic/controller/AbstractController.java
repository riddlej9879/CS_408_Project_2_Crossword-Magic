package com.example.project_2_crossword_magic.controller;

import java.util.ArrayList;
import java.lang.reflect.Method;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.example.project_2_crossword_magic.view.AbstractView;
import com.example.project_2_crossword_magic.model.AbstractModel;

public abstract class AbstractController implements PropertyChangeListener {
    private ArrayList<AbstractView> views;
    private ArrayList<AbstractModel> models;

    public AbstractController() {
        views = new ArrayList<>();
        models = new ArrayList<>();
    }

    public void addModel(AbstractModel model) {
        models.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        models.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        views.add(view);
    }

    public void removeView(AbstractView view) {
        views.remove(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        for (AbstractView view : views) {
            view.modelPropertyChange(evt);
        }
    }

    protected void setModelProperty(String propertyName, Object newValue) {
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
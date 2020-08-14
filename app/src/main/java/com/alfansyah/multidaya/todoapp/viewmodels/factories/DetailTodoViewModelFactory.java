package com.alfansyah.multidaya.todoapp.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alfansyah.multidaya.todoapp.databinding.ItemTodoBinding;
import com.alfansyah.multidaya.todoapp.models.DataItem;
import com.alfansyah.multidaya.todoapp.viewmodels.DetailTodoViewModel;

import java.lang.reflect.InvocationTargetException;

public class DetailTodoViewModelFactory implements ViewModelProvider.Factory {

    private DataItem dataItem;

    public DetailTodoViewModelFactory(DataItem dataItem) {
        this.dataItem = dataItem;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataItem.class).newInstance(dataItem);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (T) new DetailTodoViewModel(new DataItem());
    }
}

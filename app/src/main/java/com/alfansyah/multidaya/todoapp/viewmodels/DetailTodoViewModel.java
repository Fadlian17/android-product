package com.alfansyah.multidaya.todoapp.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.alfansyah.multidaya.todoapp.models.DataItem;

public class DetailTodoViewModel extends ViewModel {

    public ObservableField<DataItem> todos = new ObservableField<>();

    public DetailTodoViewModel(DataItem dataitem){
        todos.set(dataitem);
    }
}

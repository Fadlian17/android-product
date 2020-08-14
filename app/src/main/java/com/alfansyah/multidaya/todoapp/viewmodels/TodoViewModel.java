package com.alfansyah.multidaya.todoapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alfansyah.multidaya.todoapp.models.DataItem;
import com.alfansyah.multidaya.todoapp.repositories.TodoRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends ViewModel {

    private MutableLiveData<DataItem> todos = new MutableLiveData<DataItem>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private DataItem temp = new ArrayList<>();

    public TodoViewModel(){
        fetchTodos();
    }

    private void fetchTodos() {

        isLoading.setValue(true);

        TodoRepository.client().getTodos().enqueue(new Callback<DataItem>() {
            @Override
            public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                if (response.isSuccessful()) {
                    todos.setValue(response.body());

                    temp = todos.getValue();
                } else {
                    error.setValue(response.message());
                }

                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<DataItem> call, Throwable t) {
                t.printStackTrace();

                error.setValue(t.getMessage());

                isLoading.setValue(false);
            }
        });
    }

    //search by name
    public void searchTodos(CharSequence s) {
        DataItem todoModels = new ArrayList<>();

        if (todos.getValue() != null) {
            for (DataItem dataItem : temp) {
                if (dataItem.getTask().toLowerCase().contains(s) ||
                        dataItem.getCreatedAt().toLowerCase().contains(s)) {

                    todoModels.add(dataItem);
                }
            }

            todos.setValue(todoModels);
        }
    }

    public LiveData<DataItem> getTodos() {
        return todos;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}

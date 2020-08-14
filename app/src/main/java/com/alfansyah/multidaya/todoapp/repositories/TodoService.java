package com.alfansyah.multidaya.todoapp.repositories;

import com.alfansyah.multidaya.todoapp.models.DataItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {

    @GET("todos")
    Call<DataItem>getTodos();
}

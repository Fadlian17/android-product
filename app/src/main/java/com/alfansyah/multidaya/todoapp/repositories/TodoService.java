package com.alfansyah.multidaya.todoapp.repositories;

import com.alfansyah.multidaya.todoapp.models.DataTodo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {

    @GET("todos")
    Call<ArrayList<DataTodo>> getTodos();
}

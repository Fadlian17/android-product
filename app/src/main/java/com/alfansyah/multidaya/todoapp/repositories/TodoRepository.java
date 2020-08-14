package com.alfansyah.multidaya.todoapp.repositories;

import com.alfansyah.multidaya.todoapp.utils.ClientUtil;

public class TodoRepository {

    public static TodoService client(){
        return ClientUtil.client(TodoService.class,"https://online-course-todo.herokuapp.com/api/v1/");
    }
}

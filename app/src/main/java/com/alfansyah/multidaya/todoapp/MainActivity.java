package com.alfansyah.multidaya.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alfansyah.multidaya.todoapp.adapters.TodosAdapter;
import com.alfansyah.multidaya.todoapp.databinding.ActivityMainBinding;
import com.alfansyah.multidaya.todoapp.viewmodels.TodoViewModel;

public class MainActivity extends AppCompatActivity {

    public final static String DATA_TODO = "todo";

    ActivityMainBinding binding;
    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        TodosAdapter todosAdapter = new TodosAdapter();
        todosAdapter.Listener = todosModel -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    TodoActivity.class
            ).putExtra(DATA_TODO , (Parcelable) viewModel);
            startActivity(intent);
        };


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rvTodo.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        binding.rvTodo.setAdapter(todosAdapter);

        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        viewModel.getTodos().observe(this, todosAdapter::setTodos);

        viewModel.isLoading().observe(this, isLoading -> {
            binding.pbLoading.setVisibility(isLoading? View.VISIBLE:View.GONE);
            binding.rvTodo.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });

        viewModel.getError().observe(this, error -> {
            if (error.length() > 0) Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}
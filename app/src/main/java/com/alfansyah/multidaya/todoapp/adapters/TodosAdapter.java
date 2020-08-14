package com.alfansyah.multidaya.todoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alfansyah.multidaya.todoapp.R;
import com.alfansyah.multidaya.todoapp.databinding.ItemTodoBinding;
import com.alfansyah.multidaya.todoapp.models.DataItem;
import com.alfansyah.multidaya.todoapp.viewmodels.DetailTodoViewModel;
import com.alfansyah.multidaya.todoapp.viewmodels.factories.DetailTodoViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private ArrayList<DataItem> todos = new ArrayList<>();

    public interface TodoListener {
        void onClick(DataItem dataItem);
    }

    public void setTodos(ArrayList<DataItem> todos) {
        this.todos = todos;

        notifyDataSetChanged();
    }

    public TodoListener Listener;

    @NonNull
    @Override
    public TodosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTodoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_todo,
                parent,false

        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodosAdapter.ViewHolder holder, int position) {
        holder.bindData(todos.get(position), Listener);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTodoBinding binding;

        public ViewHolder(@NonNull ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(DataItem dataItem, TodoListener Listener) {
            DetailTodoViewModel viewModel = new DetailTodoViewModelFactory(dataItem)
                    .create(DetailTodoViewModel.class);

            binding.setViewModel(viewModel);
            binding.cvTodo.setOnClickListener(view -> Listener.onClick(dataItem));
        }
    }
}

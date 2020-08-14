package com.alfansyah.multidaya.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alfansyah.multidaya.todoapp.R;
import com.alfansyah.multidaya.todoapp.databinding.ItemTodoBinding;
import com.alfansyah.multidaya.todoapp.models.DataItem;

import java.util.ArrayList;
import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private List<DataItem> todos = new ArrayList<>();

    public interface DataListener {
        void onUpdate(DataItem dataItem);

        void onDelete(DataItem dataItem);
    }

    private DataListener listener;

    public void setListener(DataListener listener) {
        this.listener = listener;
    }

    public void setProducts(List<DataItem> products) {
        this.todos = products;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TodosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_todo,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TodosAdapter.ViewHolder holder, int position) {
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
    }
}

package io.akessler.budgie.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.akessler.budgie.core.model.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textName);
        }

    }

    private List<Category> categories;

    private Context context;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder viewHolder, int position) {
        Category category = categories.get(position);
        viewHolder.nameTextView.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

}
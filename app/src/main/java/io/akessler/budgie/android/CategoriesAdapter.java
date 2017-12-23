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

        public TextView extremumTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.textName);
            extremumTextView = (TextView) itemView.findViewById(R.id.textExtremum);
        }

    }

    private List<Category> mCategories;

    private Context mContext;

    public CategoriesAdapter(Context context, List<Category> categories) {
        mCategories = categories;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
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
        Category category = mCategories.get(position);
        viewHolder.nameTextView.setText(category.getName());
        viewHolder.extremumTextView.setText(String.valueOf(category.getExtremum()));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

}
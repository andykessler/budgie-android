package io.akessler.budgie.core.utils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import io.akessler.budgie.core.model.Budget;
import io.akessler.budgie.core.model.Category;
import io.akessler.budgie.core.model.Transaction;

public class FirestoreFacade {

    private FirebaseFirestore db;
    private CollectionReference categoriesRef;
//    private CollectionReference budgetsRef;
//    private CollectionReference transactionsRef;

    // change lists from public static
    public static List<Category> categoryList;

    public FirestoreFacade() {
        db = FirebaseFirestore.getInstance();
        categoriesRef = db.collection("categories");
    }

    public void addCategories(List<Category> categoryList) {
        for(Category c : categoryList) {
            categoriesRef.add(c);
        }
    }

    public void loadCategories() {
        OnSuccessListener<QuerySnapshot> onSuccessListener = new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                categoryList = documentSnapshots.toObjects(Category.class);
                for(Category c : categoryList) {
                    System.out.println(c.toString());
                }
//                RecyclerView rvCategories = findViewById(R.id.rvCategories);
//                CategoriesAdapter adapter = new CategoriesAdapter(context, categoryList);
//                rvCategories.setAdapter(adapter);
//                rvCategories.setLayoutManager(new LinearLayoutManager(context));
            }
        };
    }
}

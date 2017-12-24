package io.akessler.budgie.core.utils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import io.akessler.budgie.core.model.Budget;
import io.akessler.budgie.core.model.Category;
import io.akessler.budgie.core.model.Transaction;

public class FirestoreFacade {

    private FirebaseFirestore db;
    private CollectionReference categoriesRef;
    private CollectionReference budgetsRef;
    private CollectionReference transactionsRef;

    // change lists from public static
    public static List<Category> categoryList;
    public static List<Budget> budgetList;
    public static List<Transaction> transactionList;

    private static OnSuccessListener<DocumentReference> onSuccessListener = new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            System.out.println("DocumentSnapshot written with ID: " + documentReference.getId());
        }
    };

    private static OnFailureListener onFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            System.err.println(e.getMessage());
        }
    };

    public FirestoreFacade() {
        db = FirebaseFirestore.getInstance();
        categoriesRef = db.collection("categories");
        budgetsRef = db.collection("budgets");
        transactionsRef = db.collection("transactions");
    }

    public void addTransactions(List<Transaction> transactionList) {
        for(Transaction t : transactionList) {
            transactionsRef.add(t)
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(onFailureListener);
        }
    }

    public void addCategories(List<Category> categoryList) {
        for(Category c : categoryList) {
            categoriesRef.add(c)
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(onFailureListener);
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

        categoriesRef.get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public void loadBudgets() {
        OnSuccessListener<QuerySnapshot> onSuccessListener = new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                budgetList = documentSnapshots.toObjects(Budget.class);
                for(Budget b : budgetList) {
                    System.out.println(b.toString());
                }
//                RecyclerView rvBudgets = findViewById(R.id.rvBudgets);
//                BudgetsAdapter adapter = new BudgetsAdapter(context, budgetList);
//                rvBudgets.setAdapter(adapter);
//                rvBudgets.setLayoutManager(new LinearLayoutManager(context));
            }
        };

        budgetsRef.get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
}

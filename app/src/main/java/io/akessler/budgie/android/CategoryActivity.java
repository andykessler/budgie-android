package io.akessler.budgie.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.akessler.budgie.core.model.Budget;
import io.akessler.budgie.core.model.Category;
import io.akessler.budgie.core.model.Transaction;

public class CategoryActivity extends AppCompatActivity {

    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "TODO Implement create new category", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                loadCategoriesFromFirestore();

            }
        });

//        List<Transaction> list = TransactionReader.readFromCSV(AccountType.CREDIT, getResources().openRawResource(R.raw.credit_test));
//        for(Transaction t : list) {
//            System.out.println(t.toString());
//        }
//
//        // FIXME this actually contains data for both CHECKING and SAVINGS
//        List<Transaction> list2 = TransactionReader.readFromCSV(AccountType.CHECKING, getResources().openRawResource(R.raw.debit_test));
//        for(Transaction t : list2) {
//            System.out.println(t.toString());
//        }
//
//        putTransactionsInFirestore(list);
//        putTransactionsInFirestore(list2);

//        loadCategoriesFromFirestore();
//        loadBudgetsFromFirestore();

//        putCategoriesInFirestore(Arrays.asList(Category.MAIN_CATEGORIES));
    }


    public void putTransactionsInFirestore(List<Transaction> transactionList) {

        OnSuccessListener<DocumentReference> onSuccessListener = new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println("DocumentSnapshot written with ID: " + documentReference.getId());
            }
        };

        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.getMessage());
            }
        };

        db = FirebaseFirestore.getInstance();
        CollectionReference transactions = db.collection("transactions");
        for(Transaction t : transactionList) {
            transactions.add(t)
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(onFailureListener);
        }
    }

    public void putCategoriesInFirestore(List<Category> categoryList) {

        OnSuccessListener<DocumentReference> onSuccessListener = new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println("DocumentSnapshot written with ID: " + documentReference.getId());
            }
        };

        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.getMessage());
            }
        };

        db = FirebaseFirestore.getInstance();
        CollectionReference categories = db.collection("categories");
        for(Category c : categoryList) {
            categories.add(c)
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(onFailureListener);
        }
    }

    private static List<Category> categoryList;
    public void loadCategoriesFromFirestore() {
        final Context context = this; // FIXME probably not way this should be done in future :)
        OnSuccessListener<QuerySnapshot> onSuccessListener = new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {

                System.out.println("Hello");

                categoryList = documentSnapshots.toObjects(Category.class);

                for(Category c : categoryList) {
                    System.out.println(c.toString());
                }

                RecyclerView rvCategories = findViewById(R.id.rvCategories);
                CategoriesAdapter adapter = new CategoriesAdapter(context, categoryList);
                rvCategories.setAdapter(adapter);
                rvCategories.setLayoutManager(new LinearLayoutManager(context));

            }
        };
        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Goodbye");

                System.err.println(e.getMessage());
            }
        };

        db = FirebaseFirestore.getInstance();
        CollectionReference categories = db.collection("categories");
        categories.get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    private static List<Budget> budgetList;
    public void loadBudgetsFromFirestore() {
        final Context context = this; // FIXME probably not way this should be done in future :)
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
        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.getMessage());
            }
        };

        db = FirebaseFirestore.getInstance();
        CollectionReference budgets = db.collection("budgets");
        budgets.get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

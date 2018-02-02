package io.akessler.budgie.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.akessler.budgie.core.model.Category;

public class CategoryActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private CollectionReference categoriesRef;

    private FragmentManager fragmentManager;

    private CategoryCreateDialog createDialog;

    List<Category> categoryList;

    RecyclerView rvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        createDialog = new CategoryCreateDialog();

//      Arrays.asList(Category.MAIN_CATEGORIES);
        db = FirebaseFirestore.getInstance();
        loadCategories();

        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setAdapter(new CategoriesAdapter(this, categoryList));
        rvCategories.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog.show(fragmentManager,"category_create_dialog");
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

    }

    private void loadCategories() {
        categoriesRef = db.collection("categories");
        categoryList = new ArrayList<>();

        OnSuccessListener<QuerySnapshot> onSuccessListener = new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                categoryList = documentSnapshots.toObjects(Category.class);
                ((CategoriesAdapter) rvCategories.getAdapter()).updateCategories(categoryList); // hmm
//                for(Category c : categoryList) {
//                    System.out.println(c.toString());
//                }
            }
        };
        categoriesRef.get().addOnSuccessListener(onSuccessListener);
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

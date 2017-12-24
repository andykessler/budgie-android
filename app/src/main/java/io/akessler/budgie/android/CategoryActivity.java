package io.akessler.budgie.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import io.akessler.budgie.core.model.Budget;
import io.akessler.budgie.core.model.Category;
import io.akessler.budgie.core.model.Transaction;
import io.akessler.budgie.core.utils.FirestoreFacade;

public class CategoryActivity extends AppCompatActivity {

    private FirestoreFacade firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firestore = new FirestoreFacade();
        firestore.addCategories(Arrays.asList(Category.MAIN_CATEGORIES));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "TODO Implement create new category", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                firestore.loadCategories();
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

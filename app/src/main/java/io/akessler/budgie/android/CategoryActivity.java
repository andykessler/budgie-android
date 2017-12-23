package io.akessler.budgie.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.akessler.budgie.core.model.AccountType;
import io.akessler.budgie.core.model.Transaction;
import io.akessler.budgie.core.utils.TransactionReader;

public class CategoryActivity extends AppCompatActivity {

    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Transaction> list = TransactionReader.readFromCSV(AccountType.CREDIT, getResources().openRawResource(R.raw.credit_test));
        for(Transaction t : list) {
            System.out.println(t.toString());
        }

        // FIXME this actually contains data for both CHECKING and SAVINGS
        List<Transaction> list2 = TransactionReader.readFromCSV(AccountType.CHECKING, getResources().openRawResource(R.raw.debit_test));
        for(Transaction t : list2) {
            System.out.println(t.toString());
        }

        System.out.println("Attempting to put into Firestore...");
        putTransactionsInFirebase(list);
        putTransactionsInFirebase(list2);
        System.out.println("Done putting into Firestore.");
    }

    public void putTransactionsInFirebase(List<Transaction> transactionList) {

        OnSuccessListener<DocumentReference> onSuccessListener = new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println("DocumentSnapshot written with ID: " + documentReference.getId());
            }
        };

        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.err.println(e.toString());
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

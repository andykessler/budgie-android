package io.akessler.budgie.android;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

public class CategoryCreateDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_category_create, null))
                .setPositiveButton("Positive", positiveOnClickListener())
                .setNegativeButton("Negative", negativeOnClickListener());
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private DialogInterface.OnClickListener positiveOnClickListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("TODO Positive");
            }
        };
    }

    private DialogInterface.OnClickListener negativeOnClickListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("TODO Negative");
            }
        };
    }

}

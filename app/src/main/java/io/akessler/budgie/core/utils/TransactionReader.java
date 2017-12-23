package io.akessler.budgie.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.akessler.budgie.core.model.AccountType;
import io.akessler.budgie.core.model.Transaction;

public class TransactionReader {

    // know which type (credit or debit)

    public static List<Transaction> readFromCSV(AccountType accountType, InputStream inputStream) {
        List<Transaction> transactions = new ArrayList<>();

        String delimiter = ",";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));

            String headers = br.readLine();
            System.out.println(headers); // TODO Do something else with this?

            String line;
            while((line = br.readLine()) != null) {
                // FIXME Find solution that doesn't require hardcoded indices
                String[] cols = line.split(delimiter);
                // TODO Refactor these functions to builder pattern
                Transaction t = null;
                switch(accountType) {
                    case CREDIT:
                        t = buildCreditCardTransaction(cols);
                        break;
                    case CHECKING:
//                    case SAVINGS:
                        t = buildCheckingTransaction(cols);
                        break;
                }
                transactions.add(t);
            }

        } catch (Exception e) { // FIXME handle specific exceptions
            e.printStackTrace();
        }
        finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return transactions;
    }

    private static Transaction buildCreditCardTransaction(String[] cols) {
        Transaction t = new Transaction();
        t.setId("tempId"); // TODO how to link back to SOR with given fields?
        t.setName(cols[3]);
        t.setDescription("");
        t.setAccountId(cols[2]);
        t.setAuthDate(cols[0]);
        t.setPostDate(cols[1]);
        t.setCategoryId(cols[4]);
        // because of way null fields are being parsed currently
        // also, currently treating credit card debits as POSITIVE, and payments as NEGATIVE
        // TODO make sure this is how we will have it in the end state
        String amountString = cols[5].equals("") ? "-" + cols[6] : cols[5];
        t.setAmount(convertStringAmountToCents(amountString));

        return t;
    }

    private static Transaction buildCheckingTransaction(String[] cols) {
        Transaction t = new Transaction();
        t.setId(cols[9]); // to key in to SOR
        t.setName(cols[10]);
        t.setDescription("");
        t.setAccountId(cols[1].substring(Math.max(0,cols[1].length() - 4))); // get last 4 only
        t.setAuthDate(cols[7]);
        t.setPostDate(cols[7]);
        t.setCategoryId("");
        t.setAmount(convertStringAmountToCents(cols[8]));
        return t;
    }

    // Best to store currency as integers, so storing as cents
    private static int convertStringAmountToCents(String amountString) {
        String[] amounts = amountString.split("\\.");
        int dollars = 0;
        int cents = 0;
        try {
            dollars = Integer.parseInt(amounts[0]);
            cents = Integer.parseInt(amounts[1]);
        }
        catch (IndexOutOfBoundsException e) { // happens on values with no cents (e.g. 25 flat)
            System.out.println(e.getMessage());
        }
        int total = (100 * dollars) + cents;
        return total;
    }

}

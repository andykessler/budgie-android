package io.akessler.budgie.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.akessler.budgie.core.model.Transaction;

public class TransactionReader {

    // know which type (credit or debit)

    public static List<Transaction> readFromCSV(InputStream inputStream) {
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
                // TODO Refactor to builder pattern
                Transaction t = new Transaction();

                // t.setId("");
                t.setName(cols[3]);
                t.setDescription("This is a description!");
                t.setAccountId(cols[2]);
                t.setAuthDate(cols[0]);
                t.setPostDate(cols[1]);
                t.setCategoryId(cols[4]);

                // because of way null fields are being parsed currently
                int amountIndex = cols[5].equals("") ? 6 : 5;
                t.setAmount(new BigDecimal(cols[amountIndex]));//.add(new BigDecimal(cols[6])));

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


}

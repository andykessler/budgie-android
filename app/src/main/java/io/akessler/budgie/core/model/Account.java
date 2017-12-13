package io.akessler.budgie.core.model;

import java.math.BigDecimal;

public class Account {

    // eventually will be tied to a user.

    // don't see why to routing or full account number,
    // last four is sufficient

    String id;

    String name;

    String description;

    AccountType accountType; // or inherit?

    String lastFour;

    BigDecimal balance;

}

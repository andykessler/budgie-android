package io.akessler.budgie.core.model;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    // should we also track source id?
    // in case we want to check SOR, would have easy way to link

    String id;

    String name;

    String description;

    String accountId;

    int amount; // storing as cents (hence integer)

    String authDate;

    String postDate;

    String categoryId;

    // maybe create wrapper object which represents this? need to validate adds to 100%
//    Map<String, Float> categoryDistribution;

    // TODO Consider using annotations (lombok)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", accountId='" + accountId + '\'' +
                ", amount=" + amount +
                ", authDate='" + authDate + '\'' +
                ", postDate='" + postDate + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

    // might not need this if we have a serializer
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", description);
        map.put("accountId", accountId);
        map.put("amount", amount);
        map.put("authDate", authDate);
        map.put("postDate", postDate);
        map.put("categoryId", categoryId);
        return map;
    }
}

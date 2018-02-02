package io.akessler.budgie.core.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Budget {

    String categoryId;
//    String category;

    int amount; // signed? for income vs. expense

    Frequency frequency = Frequency.MONTHLY;

    boolean carryOver = false;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public boolean isCarryOver() {
        return carryOver;
    }

    public void setCarryOver(boolean carryOver) {
        this.carryOver = carryOver;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "categoryId=" + categoryId +
                ", amount=" + amount +
                ", frequency=" + frequency +
                ", carryOver=" + carryOver +
                '}';
    }
}

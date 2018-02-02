package io.akessler.budgie.core.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@IgnoreExtraProperties
public class Category {

    public static final Category[] MAIN_CATEGORIES = {
            new Category("Bills & Utilities",null, null),
            new Category("Business Services", null, null),
            new Category("Education", null, null),
            new Category("Entertainment", null, null),
            new Category("Fees & Charges", null, null),
            new Category("Financial", null, null),
            new Category("Food & Dining", null, null),
            new Category("Gifts & Donations", null, null),
            new Category("Health & Fitness", null, null),
            new Category("Home", null, null),
            new Category("Income", null, null),
            new Category("Kids", null, null),
            new Category("Loans", null, null),
            new Category("Misc Expenses", null, null),
            new Category("Personal Care", null, null),
            new Category("Pets", null, null),
            new Category("Shopping", null, null),
            new Category("Taxes", null, null),
            new Category("Transfer", null, null),
            new Category("Travel", null, null),
            new Category("Uncategorized", null, null)
    };

    public Category() {
        this.name = "";
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Category(String name, String parent, String[] children) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
        if(children != null) {
            this.children.addAll(Arrays.asList(children));
        }
    }

    private String name;

    private String parent;

    private List<String> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) { // Assumption that we don't make our own copy.
        this.children = children;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", children=" + children +
                '}';
    }
}

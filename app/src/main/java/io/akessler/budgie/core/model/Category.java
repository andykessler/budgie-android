package io.akessler.budgie.core.model;

import java.util.List;

public class Category {

//    String id;

    String name;

    String description;

    int amount;

    int extremum;

    String parent;

    List<String> children;

    // TODO lombok

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExtremum() {
        return extremum;
    }

    public void setExtremum(int extremum) {
        this.extremum = extremum;
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

    public void setChildren(List<String> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", extremum=" + extremum +
                ", parent='" + parent + '\'' +
                ", children=" + children +
                '}';
    }
}

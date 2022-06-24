package com.example.tamz2_sqlite;

import androidx.annotation.NonNull;

public class DataItem {
    public static final String[] types = new String[] {"", "Natural 91", "Natural 95", "Natural 98"};

    private final int id;
    private final String name;
    private final int type;
    private final int cost;

    public DataItem(int id, String name, int type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + ": " + cost + (types[type] == "" ? "" : " (" + types[type] + ")");
    }
}

package com.example.expenseviewerapp.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses")
public class ExpenseEntity {

    @PrimaryKey
    @NonNull
    private final String id;
    private final String title;
    private final double amount;
    private final long dateMillis;

    public ExpenseEntity(@NonNull String id, String title, double amount, long dateMillis) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.dateMillis = dateMillis;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public long getDateMillis() {
        return dateMillis;
    }
}

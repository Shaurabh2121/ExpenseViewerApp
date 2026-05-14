package com.example.expenseviewerapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ExpenseDto {

    @SerializedName("id")
    private final String id;

    @SerializedName("title")
    private final String title;

    @SerializedName("amount")
    private final double amount;

    @SerializedName("date")
    private final String date;

    public ExpenseDto(String id, String title, double amount, String date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

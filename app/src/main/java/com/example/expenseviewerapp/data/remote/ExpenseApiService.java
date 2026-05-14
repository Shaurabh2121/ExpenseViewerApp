package com.example.expenseviewerapp.data.remote;

import com.example.expenseviewerapp.data.remote.dto.ExpenseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExpenseApiService {

    @GET("b/DYZJF")
    Call<List<ExpenseDto>> getExpenses();
}

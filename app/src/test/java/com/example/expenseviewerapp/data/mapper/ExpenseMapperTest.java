package com.example.expenseviewerapp.data.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.expenseviewerapp.data.local.entity.ExpenseEntity;
import com.example.expenseviewerapp.data.remote.dto.ExpenseDto;
import com.example.expenseviewerapp.domain.model.Expense;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpenseMapperTest {

    @Test
    public void dtosToEntities_validDtos_returnsCorrectEntities() {
        List<ExpenseDto> dtos = Arrays.asList(
                new ExpenseDto("1", "Flight to SF", 230.50, "2021-07-03T01:50:00+01:00"),
                new ExpenseDto("2", "Hotel", 550.00, "2021-08-03T01:50:00+01:00")
        );

        List<ExpenseEntity> result = ExpenseMapper.dtosToEntities(dtos);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Flight to SF", result.get(0).getTitle());
        assertEquals(230.50, result.get(0).getAmount(), 0.01);
        assertTrue(result.get(0).getDateMillis() > 0);
    }

    @Test
    public void dtosToEntities_malformedDate_skipsEntry() {
        List<ExpenseDto> dtos = Arrays.asList(
                new ExpenseDto("1", "Valid", 100.0, "2021-07-03T01:50:00+01:00"),
                new ExpenseDto("2", "Invalid", 200.0, "not-a-date"),
                new ExpenseDto("3", "Also valid", 300.0, "2021-08-03T01:50:00+01:00")
        );

        List<ExpenseEntity> result = ExpenseMapper.dtosToEntities(dtos);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("3", result.get(1).getId());
    }

    @Test
    public void dtosToEntities_emptyList_returnsEmpty() {
        List<ExpenseEntity> result = ExpenseMapper.dtosToEntities(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void entitiesToDomain_validEntities_returnsCorrectDomainList() {
        List<ExpenseEntity> entities = Arrays.asList(
                new ExpenseEntity("1", "Flight", 230.50, 1625270000000L),
                new ExpenseEntity("2", "Hotel", 550.00, 1628000000000L)
        );

        List<Expense> result = ExpenseMapper.entitiesToDomain(entities);

        assertEquals(2, result.size());
        assertEquals("Flight", result.get(0).getTitle());
        assertEquals(230.50, result.get(0).getAmount(), 0.01);
        assertEquals(1625270000000L, result.get(0).getDate().getTime());
    }
}

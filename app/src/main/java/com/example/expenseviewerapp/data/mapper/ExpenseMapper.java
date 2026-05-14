package com.example.expenseviewerapp.data.mapper;

import com.example.expenseviewerapp.data.local.entity.ExpenseEntity;
import com.example.expenseviewerapp.data.remote.dto.ExpenseDto;
import com.example.expenseviewerapp.data.util.DateParser;
import com.example.expenseviewerapp.domain.model.Expense;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExpenseMapper {

    private static final Logger LOGGER = Logger.getLogger(ExpenseMapper.class.getName());

    private ExpenseMapper() {
    }

    public static List<ExpenseEntity> dtosToEntities(List<ExpenseDto> dtos) {
        List<ExpenseEntity> entities = new ArrayList<>();
        for (ExpenseDto dto : dtos) {
            try {
                Date date = DateParser.parseIso8601(dto.getDate());
                entities.add(new ExpenseEntity(
                        dto.getId(),
                        dto.getTitle(),
                        dto.getAmount(),
                        date.getTime()
                ));
            } catch (ParseException e) {
                LOGGER.log(Level.WARNING, "Skipping expense with unparseable date: " + dto.getDate(), e);
            }
        }
        return entities;
    }

    public static List<Expense> entitiesToDomain(List<ExpenseEntity> entities) {
        List<Expense> expenses = new ArrayList<>();
        for (ExpenseEntity entity : entities) {
            expenses.add(new Expense(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getAmount(),
                    new Date(entity.getDateMillis())
            ));
        }
        return expenses;
    }
}

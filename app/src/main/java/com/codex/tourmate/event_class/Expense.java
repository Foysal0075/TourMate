package com.codex.tourmate.event_class;

public class Expense {
    private String expenseDetails;
    private String expenseDateTime;
    private Double expenseCost;
    private String expenseKey;
    private String eventKey;


    public Expense() {
    }

    public Expense(String expenseDetails, String expenseDateTime, Double expenseCost, String expenseKey, String eventKey) {
        this.expenseDetails = expenseDetails;
        this.expenseDateTime = expenseDateTime;
        this.expenseCost = expenseCost;
        this.expenseKey = expenseKey;
        this.eventKey = eventKey;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public String getExpenseDate() {
        return expenseDateTime;
    }

    public String getExpenseCost() {
        return String.valueOf(expenseCost);
    }

    public String getExpenseKey() {
        return expenseKey;
    }

    public String getExpenseDateTime() {
        return expenseDateTime;
    }

    public String getEventKey() {
        return eventKey;
    }
}

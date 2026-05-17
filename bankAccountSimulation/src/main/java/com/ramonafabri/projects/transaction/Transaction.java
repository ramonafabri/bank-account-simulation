package com.ramonafabri.projects.transaction;

import java.time.LocalDateTime;

public class Transaction {

    private Long id;

    private TransactionType transactionType;

    private Double amount;

    private LocalDateTime dateTime;

    private String description;

    private Integer sourceAccountNumber;

    private Integer targetAccountNumber;

    private Double balanceAfterTransaction;

    public Transaction(Long id, TransactionType transactionType, Double amount, Integer sourceAccountNumber, Integer targetAccountNumber, LocalDateTime dateTime, String description, Double balanceAfterTransaction) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.dateTime = dateTime;
        this.description = description;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(Integer sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public Integer getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(Integer targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}

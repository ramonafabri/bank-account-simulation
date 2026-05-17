package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private Customer owner;

    private Integer accountNumber;

    private Double balance;

    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount(Customer owner, Integer accountNumber, List<Transaction> transactions, Double balance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
        this.balance = balance;
    }

    public BankAccount(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(){}

    public void withdraw(){}

    public Customer getOwner() {
        return owner;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

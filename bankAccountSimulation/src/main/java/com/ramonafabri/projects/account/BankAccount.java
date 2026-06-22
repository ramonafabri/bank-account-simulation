package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.exceptions.InsufficientFundsException;
import com.ramonafabri.projects.transaction.Transaction;
import com.ramonafabri.projects.transaction.TransactionType;

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

    public BankAccount(Customer owner, Integer accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
        this.balance = 0.0;
    }


    public void deposit(Double depositAmount) {
        if (depositAmount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        this.balance += depositAmount;
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, depositAmount, accountNumber, null, "Deposit", this.balance);
        transactions.add(transaction);
    }

    public void withdraw(Double withdrawAmount) {
        if (withdrawAmount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (!canWithdraw(withdrawAmount)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        this.balance -= withdrawAmount;
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, withdrawAmount, accountNumber, null, "Withdraw", this.balance);
        transactions.add(transaction);
    }

    public boolean canWithdraw(double amount) {

        return amount <= balance;
    }

    public void transferOut(double amount, int targetAccountNumber){
        this.balance -= amount;
        Transaction transaction = new Transaction(TransactionType.TRANSFER_OUT, amount,this.accountNumber, targetAccountNumber, "Transfer sent", this.balance);
        transactions.add(transaction);
    }

    public void transferIn(double amount, int sourceAccountNumber){
        this.balance += amount;
        Transaction transaction = new Transaction(TransactionType.TRANSFER_IN, amount,sourceAccountNumber, this.accountNumber, "Transfer received", this.balance);
        transactions.add(transaction);
    }

    public void chargeFee(double feeAmount){
        this.balance -=feeAmount;
        Transaction transaction = new Transaction(TransactionType.FEE, feeAmount, this.accountNumber, null, "Transfer fee", this.balance);
        transactions.add(transaction);
    }


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

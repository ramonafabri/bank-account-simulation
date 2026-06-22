package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.List;

public class CheckingAccount extends BankAccount {

    private Double overdraftLimit;

    public CheckingAccount(Customer owner, Integer accountNumber, List<Transaction> transactions, Double balance, Double overdraftLimit) {
        super(owner, accountNumber, transactions, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public Double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(Double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(Customer owner,Integer accountNumber, Double overdraftLimit) {
        super(owner,accountNumber);
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public boolean canWithdraw(double amount) {
        if(amount <= getBalance() + getOverdraftLimit()){
            return true;
        }
        return false;
    }
}

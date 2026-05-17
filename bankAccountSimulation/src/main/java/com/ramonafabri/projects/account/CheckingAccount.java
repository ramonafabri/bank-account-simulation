package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.List;

public class CheckingAccount extends BankAccount {

    private Integer overdraftLimit;

    public CheckingAccount(Customer owner, Integer accountNumber, List<Transaction> transactions, Double balance, Integer overdraftLimit) {
        super(owner, accountNumber, transactions, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public Integer getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(Integer overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(Integer accountNumber, Integer overdraftLimit) {
        super(accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

}

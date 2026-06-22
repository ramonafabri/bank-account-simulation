package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.List;

public class SavingsAccount extends BankAccount {

    private Double interest;


    public SavingsAccount(Customer owner, Integer accountNumber, List<Transaction> transactions, Double balance, Double interest) {
        super(owner, accountNumber, transactions, balance);
        this.interest = interest;
    }

    public SavingsAccount(Customer owner,Integer accountNumber, Double interest) {
        super(owner,accountNumber);
        this.interest = interest;
    }

    private void addInterest(){}

    private void checkLimit(){}

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }


}

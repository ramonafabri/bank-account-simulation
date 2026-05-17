package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.List;

public class SavingsAccount extends BankAccount {

    private Integer interest;


    public SavingsAccount(Customer owner, Integer accountNumber, List<Transaction> transactions, Double balance, Integer interest) {
        super(owner, accountNumber, transactions, balance);
        this.interest = interest;
    }

    public SavingsAccount(Integer accountNumber, Integer interest) {
        super(accountNumber);
        this.interest = interest;
    }

    private void addInterest(){}

    private void checkLimit(){}

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }


}

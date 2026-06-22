package com.ramonafabri.projects;

import com.ramonafabri.projects.account.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;

    private List<BankAccount> bankAccounts = new ArrayList<>();


    public Customer(String name){
        this.name = name;
        this.bankAccounts = new ArrayList<>();
    }

    public void addAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    private void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


}

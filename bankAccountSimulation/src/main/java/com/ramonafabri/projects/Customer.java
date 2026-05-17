package com.ramonafabri.projects;

import com.ramonafabri.projects.account.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;

    private List<BankAccount> bankAccounts = new ArrayList<>();

    public Customer(String name, List<BankAccount> bankAccounts) {
        this.name = name;
        this.bankAccounts = bankAccounts;
    }

    public Customer(String name){
        this.name = name;
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

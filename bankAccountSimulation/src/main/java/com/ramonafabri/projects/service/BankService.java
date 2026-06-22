package com.ramonafabri.projects.service;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.account.BankAccount;
import com.ramonafabri.projects.account.CheckingAccount;
import com.ramonafabri.projects.account.SavingsAccount;
import com.ramonafabri.projects.exceptions.AccountNotFoundException;
import com.ramonafabri.projects.exceptions.InsufficientFundsException;
import com.ramonafabri.projects.exceptions.SameAccountTransferException;
import com.ramonafabri.projects.exceptions.TransferAmountLessThenZeroException;
import com.ramonafabri.projects.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    static  final double TRANSFER_FEE = 2.0;

    private List<Customer> customers = new ArrayList<>();
    private List<BankAccount> accounts = new ArrayList<>();


    public Customer findCustomerByName(String owner) {
        for (Customer customer : customers) {
            if (customer.getName().equals(owner)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("Customer is not registered!");
    }

    public BankAccount findAccountByNumber(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account is not registered!");
    }


    public void registerCustomer(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        Customer customer = new Customer(name);
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }


    public void openAccount(String owner, int accountNumber, String accountType, Double extraValue) {

        Customer customer = findCustomerByName(owner);

        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                throw new IllegalArgumentException("Account already exist with this number");
            }
        }

        BankAccount account;

        switch (accountType.toLowerCase()) {
            case "standard" -> account = new BankAccount(customer, accountNumber);
            case "checking" -> {
                if (extraValue == null || extraValue < 0) {
                    throw new IllegalArgumentException("Checking account requires a non-negative overdraft limit.");
                }
                account = new CheckingAccount(customer, accountNumber, extraValue);
            }
            case "savings" -> {
                if (extraValue == null || extraValue < 0) {
                    throw new IllegalArgumentException("Savings account requires a non-negative interest value.");
                }
                account = new SavingsAccount(customer, accountNumber, extraValue);
            }
            default -> throw new IllegalArgumentException("Unknown account type: " + accountType);
        }

        accounts.add(account);
        customer.addAccount(account);

    }

    public void depositMoney(int accountNumber, double amount) {

        BankAccount account = findAccountByNumber(accountNumber);
        account.deposit(amount);

    }

    public void withdrawMoney(int accountNumber, double amount){

        BankAccount account = findAccountByNumber(accountNumber);
        account.withdraw(amount);
    }

    public void transferMoney(int sourceAccountNumber, int targetAccountNumber, double amount){


        BankAccount sourceAccount = findAccountByNumber(sourceAccountNumber);
        BankAccount targetAccount = findAccountByNumber(targetAccountNumber);


        if (amount <= 0){
            throw new TransferAmountLessThenZeroException("Transfer amount must be greater than zero.");
        }

        if(sourceAccount == targetAccount){
            throw  new SameAccountTransferException("You cannot transfer money to the same account number.");
        }

        double fee = calculateTransferFee(amount);
        double totalAmount = amount + fee;

        if (!sourceAccount.canWithdraw(totalAmount)){
            throw  new InsufficientFundsException("Insufficient funds for transfer.");
        }

        sourceAccount.transferOut(amount,targetAccountNumber);
        targetAccount.transferIn(amount,sourceAccountNumber);
        sourceAccount.chargeFee(fee);

    }

    public double calculateTransferFee(double amount){
        return  TRANSFER_FEE;
    }

    public List<Transaction> getTransactionHistory(int accountNumber){
        BankAccount account = findAccountByNumber(accountNumber);
        return account.getTransactions();
    }

}

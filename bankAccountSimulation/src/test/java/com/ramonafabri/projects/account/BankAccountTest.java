package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.exceptions.InsufficientFundsException;
import com.ramonafabri.projects.transaction.Transaction;
import com.ramonafabri.projects.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BankAccountTest {


    @Test
    void deposit() {
        Customer customer = new Customer("Test Customer");
        BankAccount account = new BankAccount(customer, 1000);

        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), 0.001);
        assertEquals(1, account.getTransactions().size());

        List<Transaction> transactions = account.getTransactions();
        Transaction transaction = transactions.get(0);

        assertEquals(TransactionType.DEPOSIT, transaction.getTransactionType());
        assertEquals(100.0, transaction.getAmount(), 0.001);
        assertEquals(100.0, transaction.getBalanceAfterTransaction(), 0.001);
    }

    @Test
    void withdraw() {
        Customer customer = new Customer("Test Customer");
        BankAccount account = new BankAccount(customer, 100);
        account.deposit(100.0);

        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance(), 0.001);
        assertEquals(2, account.getTransactions().size());

        List<Transaction> transactions = account.getTransactions();
        Transaction transaction = transactions.get(1);

        assertEquals(TransactionType.WITHDRAWAL, transaction.getTransactionType());
        assertEquals(50.0, transaction.getAmount(), 0.001);
        assertEquals(50.0, transaction.getBalanceAfterTransaction(), 0.001);

        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(100.0);
        });
    }

    @Test
    void canWithdraw() {
        Customer customer = new Customer("Test Customer");
        BankAccount account = new BankAccount(customer, 100);
        account.deposit(100.0);

        assertTrue(account.canWithdraw(50.0));
        assertTrue(account.canWithdraw(100.0));
        assertFalse(account.canWithdraw(150.0));
    }

    @Test
    void depositNegativeAmountThrowsException() {
        Customer customer = new Customer("Test Customer");
        BankAccount account = new BankAccount(customer, 1000);

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.0);
        });
    }

    @Test
    void withdrawNegativeAmountThrowsException() {
        Customer customer = new Customer("Test Customer");
        BankAccount account = new BankAccount(customer, 1000);

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10.0);
        });
    }
}

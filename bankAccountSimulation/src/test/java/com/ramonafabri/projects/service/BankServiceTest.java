package com.ramonafabri.projects.service;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.account.BankAccount;
import com.ramonafabri.projects.exceptions.AccountNotFoundException;
import com.ramonafabri.projects.transaction.Transaction;
import com.ramonafabri.projects.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankServiceTest {

    @Test
    void registerCustomer() {
        BankService bankService = new BankService();

        bankService.registerCustomer("Ramona");

        assertEquals(1, bankService.getCustomers().size());

        Customer customer = bankService.getCustomers().get(0);
        assertEquals("Ramona", customer.getName());
    }

    @Test
    void openAccountAddsAccountToCustomer() {
        BankService bankService = new BankService();

        bankService.registerCustomer("Ramona");
        bankService.openAccount("Ramona", 1000, "standard", null);

        Customer customer = bankService.findCustomerByName("Ramona");

        assertEquals(1, customer.getBankAccounts().size());

        BankAccount account = customer.getBankAccounts().get(0);
        assertEquals(1000, account.getAccountNumber());
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    void depositMoney() {
        BankService bankService = new BankService();

        bankService.registerCustomer("Ramona");
        bankService.openAccount("Ramona", 1000, "standard", null);
        bankService.depositMoney(1000, 500.0);

        BankAccount account = bankService.findAccountByNumber(1000);

        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    void transferMoneyMovesMoneyAndAddsFee() {
        BankService bankService = new BankService();

        bankService.registerCustomer("Ramona");
        bankService.openAccount("Ramona", 1000, "standard", null);
        bankService.openAccount("Ramona", 2000, "standard", null);
        bankService.depositMoney(1000, 500.0);

        bankService.transferMoney(1000, 2000, 100.0);

        BankAccount sourceAccount = bankService.findAccountByNumber(1000);
        BankAccount targetAccount = bankService.findAccountByNumber(2000);

        assertEquals(398.0, sourceAccount.getBalance(), 0.001);
        assertEquals(100.0, targetAccount.getBalance(), 0.001);
    }

    @Test
    void getTransactionHistory() {
        BankService bankService = new BankService();

        bankService.registerCustomer("Ramona");
        bankService.openAccount("Ramona", 1000, "standard", null);
        bankService.depositMoney(1000, 500.0);
        bankService.withdrawMoney(1000, 100.0);

        List<Transaction> transactions = bankService.getTransactionHistory(1000);

        assertEquals(2, transactions.size());
        assertEquals(TransactionType.DEPOSIT, transactions.get(0).getTransactionType());
        assertEquals(TransactionType.WITHDRAWAL, transactions.get(1).getTransactionType());
    }

    @Test
    void findAccountByNumberThrowsExceptionWhenAccountDoesNotExist() {
        BankService bankService = new BankService();

        assertThrows(AccountNotFoundException.class, () -> {
            bankService.findAccountByNumber(9999);
        });
    }
}

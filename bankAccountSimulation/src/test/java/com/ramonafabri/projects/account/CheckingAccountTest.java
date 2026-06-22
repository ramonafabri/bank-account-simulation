package com.ramonafabri.projects.account;

import com.ramonafabri.projects.Customer;
import com.ramonafabri.projects.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckingAccountTest {

    @Test
    void canWithdrawWithOverdraftLimit() {
        Customer customer = new Customer("Test Customer");
        CheckingAccount account = new CheckingAccount(customer, 1000, 200.0);

        account.deposit(100.0);

        assertTrue(account.canWithdraw(100.0));
        assertTrue(account.canWithdraw(250.0));
        assertFalse(account.canWithdraw(350.0));
    }

    @Test
    void withdrawCanUseOverdraftLimit() {
        Customer customer = new Customer("Test Customer");
        CheckingAccount account = new CheckingAccount(customer, 1000, 200.0);

        account.deposit(100.0);
        account.withdraw(250.0);

        assertEquals(-150.0, account.getBalance(), 0.001);
    }

    @Test
    void withdrawMoreThanOverdraftLimitThrowsException() {
        Customer customer = new Customer("Test Customer");
        CheckingAccount account = new CheckingAccount(customer, 1000, 200.0);

        account.deposit(100.0);

        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(350.0);
        });
    }
}

package com.ramonafabri.projects;

import com.ramonafabri.projects.account.BankAccount;
import com.ramonafabri.projects.account.CheckingAccount;
import com.ramonafabri.projects.account.SavingsAccount;
import com.ramonafabri.projects.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class BankAccountSimulationEngine {

    private final UI ui;
    private List<Customer> customers = new ArrayList<>();
    private List<BankAccount> accounts = new ArrayList<>();


    public BankAccountSimulationEngine(UI ui) {
        this.ui = ui;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.getCommand();
            String[] commands = input.split(" \\| ");

            List<String> params = new ArrayList<>();
            if (commands.length > 1) {
                params = List.of(commands[1].split(" "));
            }

            switch (commands[0]) {

                case "registerCustomer", "rc" -> registerCustomer(params.get(0));
                case "registerBankAccount", "ra" -> {
                    int accountNumber = Integer.parseInt(params.get(0));
                    String type = params.get(1).toLowerCase();

                    BankAccount account;

                    switch (type) {
                        case "standard" -> account = new BankAccount(accountNumber);
                        case "checking" -> {
                            int overdraftLimit = Integer.parseInt(params.get(2));
                            account = new CheckingAccount(accountNumber, overdraftLimit);
                        }
                        case "savings" -> {
                            int interest = Integer.parseInt(params.get(2));
                            account = new SavingsAccount(accountNumber, interest);
                        }
                        default -> throw new IllegalArgumentException("Unknown account type: " + type);
                    }

                    accounts.add(account);
                }

                case "quit", "q" -> isRunning = false;

            }


        }
        ui.close();
    }


    private void registerCustomer(String name){
        Customer customer = new Customer(name);
        customers.add(customer);
    }




}

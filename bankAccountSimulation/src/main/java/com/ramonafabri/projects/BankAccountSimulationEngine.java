package com.ramonafabri.projects;

import com.ramonafabri.projects.service.BankService;
import com.ramonafabri.projects.transaction.Transaction;
import com.ramonafabri.projects.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class BankAccountSimulationEngine {

    private final UI ui;
    private final BankService bankService;


    public BankAccountSimulationEngine(UI ui) {
        this.ui = ui;
        this.bankService = new BankService();
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.getCommand();
            String[] commands = input.split("\\|");
            String command = commands[0].trim();

            List<String> params = new ArrayList<>();
            if (commands.length == 2) {
                params = List.of(commands[1].trim().split("\\s+"));
            } else if (commands.length > 2) {
                for (int i = 1; i < commands.length; i++) {
                    params.add(commands[i].trim());
                }
            }

            switch (command) {
                case "registerCustomer", "rc" -> {
                    bankService.registerCustomer(params.get(0));
                    ui.printMessage("Customer registered successfully.");
                }

                case "openAccount", "oa" -> {

                    String owner = String.valueOf(params.get(0));
                    int accountNumber = Integer.parseInt(params.get(1));

                    String accountType = String.valueOf(params.get(2));

                    Double extraValue = null;
                    if (params.size() > 3) {
                        extraValue = Double.parseDouble(params.get(3));
                    }

                    bankService.openAccount(owner,accountNumber,accountType, extraValue);
                    ui.printMessage("Account registered successfully.");
                }

                case "deposit", "d" ->{

                    int accountNumber = Integer.parseInt(params.get(0));
                    double amount = Double.parseDouble(params.get(1));

                    bankService.depositMoney(accountNumber,amount);
                    ui.printMessage("Deposit added successfully: " + amount + " on account number: " + accountNumber);
                }

                case "withdraw", "w" -> {
                    int accountNumber = Integer.parseInt(params.get(0));
                    double amount = Double.parseDouble(params.get(1));

                    bankService.withdrawMoney(accountNumber,amount);
                    ui.printMessage("Withdraw was successful: " + amount + " from account number: " + accountNumber);
                }

                case "transfer", "t" -> {
                    int sourceAccountNumber = Integer.parseInt(params.get(0));
                    int targetAccountNumber = Integer.parseInt(params.get(1));
                    double amount = Double.parseDouble(params.get(2));

                    bankService.transferMoney(sourceAccountNumber,targetAccountNumber,amount);
                    ui.printMessage("Transfer was successful. You have transferred " + amount + " from account number: " + sourceAccountNumber + " to account number: " + targetAccountNumber);
                }

                case "history", "h" ->{
                    int accountNumber = Integer.parseInt(params.get(0));
                    List<Transaction> transactions = bankService.getTransactionHistory(accountNumber);
                    for (Transaction transaction : transactions) {
                        ui.printMessage(transaction.toString());
                    }

                }
                case "quit", "q" -> isRunning = false;
            }

        }

        ui.close();
    }
}


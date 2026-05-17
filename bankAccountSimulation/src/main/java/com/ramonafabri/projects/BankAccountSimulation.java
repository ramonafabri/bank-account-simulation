package com.ramonafabri.projects;

import com.ramonafabri.projects.ui.ConsoleUI;

public class BankAccountSimulation {

    public static void main(String[] args) {
        BankAccountSimulationEngine bankAccountSimulationEngine = new BankAccountSimulationEngine(new ConsoleUI());
        bankAccountSimulationEngine.run();
    }

}

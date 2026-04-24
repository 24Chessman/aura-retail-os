// Aura Retail OS | IT620 | Code Crafters
// Entry point — shows a startup menu and delegates to the chosen mode
package com.aura.retailos;

import com.aura.retailos.ui.AutomatedSimulation;
import com.aura.retailos.ui.InteractiveMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(" AURA RETAIL OS");
        System.out.println(" IT620 | Code Crafters | Path B");
        System.out.println("==========================================");
        System.out.println("Select Mode:");
        System.out.println("[1] Automated Simulation");
        System.out.println("    Runs all 3 Path B scenarios automatically.");
        System.out.println("    Demonstrates all 10 design patterns.");
        System.out.println("[2] Interactive Mode");
        System.out.println("    You control the kiosk yourself.");
        System.out.println("    Test all features manually.");
        System.out.println("==========================================");
        System.out.print("Enter choice (1 or 2): ");

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        int choice;
        try {
            choice = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            choice = -1;
        }

        if (choice == 1) {
            new AutomatedSimulation().run();
        } else if (choice == 2) {
            new InteractiveMode(scanner).start();
        } else {
            System.out.println("Invalid choice. Running automated simulation.");
            new AutomatedSimulation().run();
        }
    }

}

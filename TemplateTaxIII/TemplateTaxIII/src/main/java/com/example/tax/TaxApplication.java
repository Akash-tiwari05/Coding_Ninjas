package com.example.tax;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TaxApplication {

    public static void main(String[] args) {
        // Initialize the Spring context and load the beans
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Tax Payment Application");

        while (true) {
            // Present the menu to the user
            System.out.println("Please select which tax you want to pay: \n1. Income \n2. Property\n3. Exit");
            int userChoice = scanner.nextInt();
            String taxChoice = "";

            switch (userChoice) {
                case 1:
                    taxChoice = "incomeTax";  // Use the bean ID for IncomeTax
                    break;
                case 2:
                    taxChoice = "propertyTax";  // Use the bean ID for PropertyTax
                    break;
                case 3:
                    System.out.println("Exiting...");
                    context.close();  // Close the context before exiting
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            // Get the tax bean from the Spring context based on the user selection
            Tax tax = context.getBean(taxChoice, Tax.class);
            System.out.println("You have selected: " + tax.getTaxType());

            // Check if the tax has already been paid
            if (tax.isTaxPayed()) {
                System.out.println("The " + tax.getTaxType() + " has already been paid.");
                continue;  // Return to the main menu if tax is already paid
            }

            // Prompt the user to enter the taxable amount
            System.out.println("Enter the taxable amount for the " + tax.getTaxType() + ":");
            int amount = scanner.nextInt();
            tax.setTaxableAmount(amount);

            // Calculate the tax amount based on the taxable amount
            tax.calculateTaxAmount();
            System.out.println("The calculated " + tax.getTaxType() + " is: " + tax.getTaxAmount());

            // Ask the user if they want to pay the tax
            System.out.println("Do you want to pay this tax? (yes/no)");
            String userInput = scanner.next();

            if ("yes".equalsIgnoreCase(userInput)) {
                // If the user agrees, confirm the payment and mark the tax as paid
                tax.payTax();
                System.out.println("Tax payment confirmed. Thank you!");
            } else {
                System.out.println("Tax payment cancelled.");
            }
        }
    }
}

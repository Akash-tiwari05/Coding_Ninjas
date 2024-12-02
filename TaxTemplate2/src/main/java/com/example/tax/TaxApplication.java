package com.example.tax;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TaxApplication {

    public static void main(String[] args) {
        // Initialize the Spring context to load the beans from applicationContext.xml
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Tax Payment Application");
        
        while (true) {
            System.out.println("Please select which tax you want to pay: \n1. Income \n2. Property\n3. Exit");
            int userChoice = scanner.nextInt();
            String taxChoice = "";

            // Based on the user choice, set the corresponding taxChoice string
            switch (userChoice) {
                case 1:
                    taxChoice = "incomeTax"; // Corresponding to the bean id for Income tax
                    break;
                case 2:
                    taxChoice = "propertyTax"; // Corresponding to the bean id for Property tax
                    break;
                case 3:
                    System.out.println("Exiting...");
                    context.close(); // Properly close the context before exiting
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Skip the current loop iteration
            }

            // Fetch the selected tax bean using the bean id
            Tax tax = context.getBean(taxChoice, Tax.class); // Get the bean by its id and type
            System.out.println("You have selected: " + tax.getTaxType());
        }
    }
}

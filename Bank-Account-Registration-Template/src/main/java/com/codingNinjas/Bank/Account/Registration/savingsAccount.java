package com.codingNinjas.Bank.Account.Registration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
  This class is an implementation of a "Account" Interface based on the selection 
  done in the console for account type. You need to complete this class
  based on the following tasks.

     Tasks:
   a. Create attribute amount(double) 
   b. Override the methods of Account Interface.
   c. Build the logic for all the methods based on the description mentioned in the Account Interface.

**/


@Component("savingsAccount")
@Scope("prototype")
public class savingsAccount implements Account{
    @PostConstruct
    public void init() {
        System.out.println("User bean has been instantiated and I'm the init() method");
    }


private double amount;
    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public void addBalance(double balance) {
    amount += balance;
    }

    @Override
    public double getBalance() {
        return this.amount;
    }
}

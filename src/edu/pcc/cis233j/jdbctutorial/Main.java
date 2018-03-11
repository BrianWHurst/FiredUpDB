package edu.pcc.cis233j.jdbctutorial;

import java.util.List;

/**
 * Print info on customers in the FiredUp database
 * @author Cara Tang and Brian Hurst
 * @version 2016.03.08
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("FiredUp Customers:");
        FiredUpDB firedUp = new FiredUpDB();
        List<Customer> customers = firedUp.readCustomers();
        for (Customer cust : customers) {
            System.out.println("ID: " + cust.getId() +
                    ", Name: " + cust.getName() +
                    ", City: " + cust.getCity() +
                    ", State: " + cust.getState() +
                    ", Email Address(es): " + cust.getEmailAddresses());
        }
    }
}

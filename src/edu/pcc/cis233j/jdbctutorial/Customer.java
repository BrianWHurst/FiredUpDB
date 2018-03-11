package edu.pcc.cis233j.jdbctutorial;

import java.util.ArrayList;

/**
 * A customer in the FiredUp database (not all properties are included)
 * @author Cara Tang
 * @version 2016.01.05
 */
public class Customer {
    private int id;
    private String name;
    private String city;
    private String state;
    private ArrayList<String> emailAddresses;

    public Customer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        emailAddresses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    /**
     * Add an email address to this customer's email addresses
     * @param email an email address belonging to this customer
     */
    public void addEmailAddress(String email) {
        emailAddresses.add(email);
    }

    /**
     * @return the list of email addresses belonging to this customer
     */
    public ArrayList<String> getEmailAddresses() {
        return emailAddresses;
    }
}

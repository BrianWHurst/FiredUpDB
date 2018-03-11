package edu.pcc.cis233j.jdbctutorial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access class for the FiredUp database.
 * Currently reads only customer information.
 *
 * @author Cara Tang & Brian Hurst
 * @version 2016.03.10
 * <p>
 * - Changed field in constructor to point to the FiredUp.db file on localhost
 * - Removed USERNAME and PASSWORD fields
 * - Created constructor and an object called "input" object that calls the FiredUpUI class for user input
 * - Created readCustomersFromState method that uses input from the user to select customers by state
 * - Removed readCustomerBasics method
 */
public class FiredUpDB {
    private static final String FIREDUP_DB = "jdbc:sqlite:FiredUp.db";
    private static final String CUSTOMER_SQL = "SELECT CustomerID, Name, City, StateProvince FROM CUSTOMER";
    private static final String EMAIL_SQL = "SELECT EmailAddress FROM EMAIL WHERE FK_CustomerID = ?";
    private static String input;

    /**
     * Constructor for FiredUpDB
     */
    public FiredUpDB() {
        input = new FiredUpUI().userInput();
    }

    /**
     * Read all customers from the FiredUp database and return them as a list of Customer objects
     *
     * @return a list of customers from the FiredUp database
     */
    public List<Customer> readCustomers() {
        ArrayList<Customer> customers = readCustomersFromState(input);
        readEmailAddresses(customers);
        return customers;
    }

    /**
     * Read from the FiredUp database customers from the given state
     *
     * @param state the state of interest (US state or Canadian province)
     * @return a list of customers from the given state
     */
    private ArrayList<Customer> readCustomersFromState(String state) {
        ArrayList<Customer> customersFromState = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(CUSTOMER_SQL);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                // Matches the user input with column value
                if (rs.getString("StateProvince").equalsIgnoreCase(state)) {
                    customersFromState.add(new Customer(rs.getInt("CustomerID"),
                            rs.getString("Name"),
                            rs.getString("City"),
                            rs.getString("StateProvince")));
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersFromState;
    }

    /**
     * @return a connection to the FiredUp database
     * @throws SQLException if unable to connect
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(FIREDUP_DB);
    }


    /**
     * Read email addresses from the database for each customer in the given list,
     * adding the email addresses found to the corresponding Customer object
     *
     * @param customers list of customers whose email addresses should be read
     */
    private void readEmailAddresses(ArrayList<Customer> customers) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(EMAIL_SQL)
        ) {
            for (Customer cust : customers) {
                stmt.setInt(1, cust.getId());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    cust.addEmailAddress(rs.getString("EmailAddress"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

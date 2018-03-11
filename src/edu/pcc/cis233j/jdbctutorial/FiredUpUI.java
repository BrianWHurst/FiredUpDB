package edu.pcc.cis233j.jdbctutorial;
import java.util.Scanner;

/**
 * Text-based interface that allows the user to query the FiredUp.db SQLite database
 * - Imported Scanner class
 * - Created new Scanner object in the constructor
 * - Added userInput method
 *
 * @author Brian Hurst
 * @version 2018.03.08
 */
public class FiredUpUI {
    private Scanner input;

    /**
     * Constructor for objects of class FiredUpUI
     */
    public FiredUpUI() {
        input = new Scanner(System.in);
    }

    /**
     * Get input from the usr and build a query based on the input
     */
    public String userInput() {
        String CUSTOMER_QUERY;
        System.out.print("What state would you like to see customers from? Choices: [OR, WA, CA, ID, NV, BC] ");
        CUSTOMER_QUERY = input.nextLine();
        return CUSTOMER_QUERY;
    }
}

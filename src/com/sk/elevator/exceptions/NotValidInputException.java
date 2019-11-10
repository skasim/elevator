package com.sk.elevator.exceptions;

/**
 * Exception class to handle invalid inputs provided via text files for this simulation project. These are input
 * rows that are comments and exit and entry floors greater than 5 or less than 1.
 *
 * @author Samra Kasim
 */

public class NotValidInputException extends Exception {
    /**
     * Constructor method to instantiate when needing to throw an exception for invalid input.
     * @param message
     */
    public NotValidInputException(String message) {
        super(message);
    }
}

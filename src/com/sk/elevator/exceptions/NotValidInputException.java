package com.sk.elevator.exceptions;

/**
 * Exception class to handle invalid inputs provided via text files for this simulation project.
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

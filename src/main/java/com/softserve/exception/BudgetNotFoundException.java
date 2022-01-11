package com.softserve.exception;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
public class BudgetNotFoundException extends Exception {

    public BudgetNotFoundException(String message) {
        super(message);
    }

}

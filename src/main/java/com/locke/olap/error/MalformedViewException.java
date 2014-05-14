package com.locke.olap.error;

/**
 * @author David Gilmore
 * @date 5/14/14
 */
public class MalformedViewException extends Exception {
    public MalformedViewException() {}

    public MalformedViewException(String message) {
        super(message);
    }
}
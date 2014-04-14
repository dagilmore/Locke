package com.rollup.core.error;

/**
 * If a view is queried that does not exist, this exception should be thrown.
 *
 * @author David Gilmore
 * @date 4/14/14
 */
public class QueryDoesNotExistException extends Exception {
    public QueryDoesNotExistException() {}

    public QueryDoesNotExistException(String message) {
        super(message);
    }
}

package org.locke.query.error;

/**
 * If a view is queried that does not exist, this exception should be thrown.
 *
 * @author David Gilmore
 * @date 4/14/14
 */
public class DoesNotExistException extends Exception {
    public DoesNotExistException() {}

    public DoesNotExistException(String message) {
        super(message);
    }
}

package org.locke.query.error;

/**
 * @author David Gilmore
 * @date 5/4/14
 */
public class ExistsException extends Exception {
    public ExistsException() {}

    public ExistsException(String message) {
        super(message);
    }
}

package com.locke.olap;

import com.locke.olap.error.MalformedViewException;
import com.locke.olap.models.View;

import java.io.IOException;

/**
 * @author David Gilmore
 * @date 5/15/14
 */
public interface ViewSerde<T> {
    public View deserialize(String message) throws MalformedViewException, IOException;
    public String serialize(T object);
}

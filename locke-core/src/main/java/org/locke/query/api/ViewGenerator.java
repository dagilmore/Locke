package org.locke.query.api;

import org.locke.query.error.MalformedViewException;
import org.locke.query.models.View;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface ViewGenerator {
    String createQuery(View view) throws MalformedViewException;
}

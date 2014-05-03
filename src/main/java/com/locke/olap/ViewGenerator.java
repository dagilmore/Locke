package com.locke.olap;

import com.locke.olap.models.View;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface ViewGenerator {
    String createQuery(View view);
}

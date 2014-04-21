package com.rollup.olap;

/**
 * @author David Gilmore
 * @date 4/10/14
 */
public interface ViewGenerator {
    String createQuery(View view);
}

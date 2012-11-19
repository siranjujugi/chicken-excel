/**
 * 
 */
package com.chicken.excel.core;

/**
 * Determines a true or false value for a given input.
 * 
 * @author tamnguyen
 * 
 */
public interface Predicate<T>
{
	/**
	 * Returns the result of applying this predicate to {@code input}.
	 * 
	 * @param input
	 * @return
	 */
	boolean apply(T input);
}

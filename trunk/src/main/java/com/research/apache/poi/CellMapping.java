/**
 * 
 */
package com.research.apache.poi;

/**
 * Maintain a mapping between an excel cell and java bean property.
 * 
 * @author tamnguyen
 * 
 */
public interface CellMapping
{
	/**
	 * @return the position of cell in excel file.
	 */
	int getCellPosition();

	/**
	 * @return the property name of java bean.
	 */
	String getPropertyName();

	/**
	 * @return the type of cell's data
	 */
	Class<?> getCellType();
}

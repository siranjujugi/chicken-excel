/**
 * 
 */
package com.chicken.excel;

import java.util.Enumeration;

/**
 * Read an excel file and convert each row into a object {@link T}
 * 
 * @author tamnguyen
 * 
 */
public interface ExcelReader<T>
{
	/**
	 * Read sheet with given name of excel file.
	 * 
	 * @param sheetName
	 *            the sheet name
	 * @return the enumerator or NULL if sheet does not exist
	 */
	Enumeration<T> read(String sheetName);
}

/**
 * 
 */
package com.chicken.excel.impl;

import java.util.Enumeration;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.chicken.excel.ExcelReader;

/**
 * @author tamnguyen
 * 
 */
public abstract class AbstractExcelReader<T, W extends Workbook> implements ExcelReader<T>
{
	private final Class<T> _beanClass;
	private final W _workbook;

	public AbstractExcelReader(Class<T> beanClass, W workbook)
	{
		_beanClass = beanClass;
		_workbook = workbook;
	}

	public final Class<T> getBeanClass()
	{
		return _beanClass;
	}

	public final W getWorkbook()
	{
		return _workbook;
	}

	@Override
	public final Enumeration<T> read(String sheetName)
	{
		Sheet sheet = getWorkbook().getSheet(sheetName);
		if (sheet != null)
		{
			return new SheetEnumerator<>(sheet, getBeanClass());
		}
		return null;
	}
}

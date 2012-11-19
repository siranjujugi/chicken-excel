/**
 * 
 */
package com.chicken.excel.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.chicken.excel.ExcelReader;

/**
 * Default implementation of {@link ExcelReader} using Apache POI
 * 
 * @author tamnguyen
 * 
 */
public class ExcelReaderImpl<T> implements ExcelReader<T>
{
	private final Class<T> _beanClass;

	private HSSFWorkbook _workbook;

	public ExcelReaderImpl(Class<T> beanClass, InputStream stream) throws IOException
	{
		_beanClass = beanClass;
		_workbook = new HSSFWorkbook(stream);
	}

	@Override
	public Enumeration<T> read(String sheetName)
	{
		HSSFSheet sheet = _workbook.getSheet(sheetName);
		if (sheet != null)
		{
			return new HSSFSheetEnumerator<>(sheet, _beanClass);
		}
		return null;
	}
}

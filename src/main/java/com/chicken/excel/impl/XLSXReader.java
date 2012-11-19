/**
 * 
 */
package com.chicken.excel.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.chicken.excel.ExcelReader;

/**
 * Implementation of {@link ExcelReader} that support <strong>xlsx</strong> excel file format (excel
 * 2010)
 * 
 * @author tamnguyen
 * 
 */
public class XLSXReader<T> extends AbstractExcelReader<T, XSSFWorkbook>
{

	public XLSXReader(Class<T> beanClass, InputStream stream) throws IOException
	{
		super(beanClass, new XSSFWorkbook(stream));
	}
}

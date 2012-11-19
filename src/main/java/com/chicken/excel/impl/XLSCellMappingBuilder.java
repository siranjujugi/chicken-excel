/**
 * 
 */
package com.chicken.excel.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.chicken.excel.CellMapping;
import com.chicken.excel.CellMappingBuilder;

/**
 * A {@link CellMappingBuilder} for xls file format.
 * 
 * @author tamnguyen
 * 
 */
public class XLSCellMappingBuilder implements CellMappingBuilder
{
	private final Class<?> _beanClass;
	private final HSSFSheet _hssfSheet;

	public XLSCellMappingBuilder(Class<?> beanClass, HSSFSheet hssfSheet)
	{
		_beanClass = beanClass;
		_hssfSheet = hssfSheet;
	}

	/**
	 * Loop through each cell of first row. On each cell, try to find an appropriate setter method
	 * which is based on method's name. If method is found, create a {@link CellMapping}
	 */
	@Override
	public List<CellMapping> buildCellMappings()
	{
		List<CellMapping> cellMappings = new ArrayList<>();
		Row row = _hssfSheet.getRow(0);
		if (row != null)
		{
			List<Method> setters = BeanUtils.getSetterMethods(_beanClass);
			if (!setters.isEmpty())
			{
				Iterator<Cell> iCells = row.cellIterator();
				while (iCells.hasNext())
				{
					Cell cell = iCells.next();
					String val = cell.getStringCellValue();
					Method method = filter(setters, val);
					if (method != null)
					{
						Class<?> params[] = method.getParameterTypes();
						cellMappings.add(CellMappingLocal.newCellMapping(cell.getColumnIndex(),
								val, params[0]));
					}
				}
			}
		}
		return cellMappings;
	}

	private Method filter(List<Method> methods, String methodName)
	{
		for (Method method : methods)
		{
			String name = BeanUtils.name(method);
			if (name.equals(methodName))
			{
				return method;
			}
		}
		return null;
	}

}

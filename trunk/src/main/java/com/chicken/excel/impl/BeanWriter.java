/**
 * 
 */
package com.chicken.excel.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import com.chicken.excel.CellMapping;

/**
 * @author tamnguyen
 * 
 */
public class BeanWriter<B>
{
	private Map<String, Method> _setterMethods;

	public BeanWriter(final Class<B> beanClass)
	{
		initSetterMethods(beanClass);
	}

	private void initSetterMethods(Class<B> beanClass)
	{
		if (_setterMethods != null)
		{
			return;
		}
		List<Method> setters = BeanUtils.getSetterMethods(beanClass);

		_setterMethods = new HashMap<>(setters.size());

		for (Method method : setters)
		{
			_setterMethods.put(BeanUtils.name(method), method);
		}
	}

	public void writeTo(B bean, Cell cell, CellMapping mapping)
	{
		String propName = mapping.getPropertyName();

		Method method = _setterMethods.get(propName);
		if (method == null)
		{
			return;
		}

		Class<?> cellType = mapping.getCellType();

		Object val = getData(cell, cellType);

		try
		{
			method.invoke(bean, new Object[] { val });
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			// do nothing.
			System.out.println(e);
		}
	}

	/**
	 * Get the data of cell. It only supports {@link Number}, {@link String}, {@link Date} and
	 * {@link Boolean} types
	 * 
	 * @param cell
	 *            the excel cell.
	 * @param type
	 *            the data type
	 * @return the value of cell or NULL if the cell does not support data type.
	 */
	private Object getData(Cell cell, Class<?> type)
	{
		if (Number.class.isAssignableFrom(type))
		{
			Double d = cell.getNumericCellValue();
			if (Long.class.isAssignableFrom(type))
			{
				return d.longValue();
			}
			else if (Integer.class.isAssignableFrom(type))
			{
				return d.intValue();
			}
			else if (Float.class.isAssignableFrom(type))
			{
				return d.floatValue();
			}
			else
			{
				return d;
			}
		}
		else if (String.class.isAssignableFrom(type))
		{
			return cell.getStringCellValue();
		}
		else if (Date.class.isAssignableFrom(type))
		{
			return cell.getDateCellValue();
		}
		else if (Boolean.class.isAssignableFrom(type))
		{
			return cell.getBooleanCellValue();
		}
		return null;
	}
}

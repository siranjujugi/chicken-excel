/**
 * 
 */
package com.chicken.excel.impl;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.chicken.excel.core.CellMapping;

/**
 * @author tamnguyen
 */
public class SheetEnumerator<T> implements Enumeration<T>
{
	private final Iterator<Row> _iRows;
	private final Class<T> _beanClass;

	private List<CellMapping> _cellMappings;
	private BeanWriter<T> _beanWriter;

	public SheetEnumerator(final Sheet sheet, Class<T> clazz)
	{
		_iRows = sheet.iterator();
		_beanClass = clazz;
		_beanWriter = new BeanWriter<>(_beanClass);
		_cellMappings = new CellMappingBuilderLocal(_beanClass, sheet).buildCellMappings();

		ignoreFirstRow();
	}

	/**
	 * Always ignore the first row. The first row is header row.
	 */
	private void ignoreFirstRow()
	{
		if (_iRows.hasNext())
		{
			_iRows.next();
		}
	}

	@Override
	public boolean hasMoreElements()
	{
		return _iRows.hasNext();
	}

	@Override
	public T nextElement()
	{
		try
		{
			Row row = _iRows.next();

			T bean = _beanClass.newInstance();

			populate(bean, row);

			return bean;
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}

	/**
	 * Populate a bean with data from {@link Row}.
	 * 
	 * @param bean
	 *            the bean
	 * @param row
	 *            the row in sheet.
	 */
	private void populate(final T bean, Row row)
	{
		Iterator<Cell> i = row.cellIterator();
		while (i.hasNext())
		{
			Cell cell = i.next();

			CellMapping mapping = cellMapping(cell);

			if (mapping != null)
			{
				_beanWriter.writeTo(bean, cell, mapping);
			}
		}
	}

	private CellMapping cellMapping(Cell cell)
	{
		int index = cell.getColumnIndex();
		for (CellMapping mapping : _cellMappings)
		{
			if (mapping.getCellPosition() == index)
			{
				return mapping;
			}
		}
		return null;
	}
}

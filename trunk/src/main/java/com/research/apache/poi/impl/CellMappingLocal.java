/**
 * 
 */
package com.research.apache.poi.impl;

import com.research.apache.poi.CellMapping;

/**
 * Default implementation of {@link CellMapping}
 * 
 * @author tamnguyen
 * 
 */
public class CellMappingLocal implements CellMapping
{

	private int _pos;
	private String _prop;
	private Class<?> _type;

	private CellMappingLocal(int pos, String prop, Class<?> type)
	{
		_pos = pos;
		_prop = prop;
		_type = type;
	}

	@Override
	public int getCellPosition()
	{
		return _pos;
	}

	@Override
	public String getPropertyName()
	{
		return _prop;
	}

	@Override
	public Class<?> getCellType()
	{
		return _type;
	}

	public static CellMapping newCellMapping(int pos, String prop, Class<?> type)
	{
		return new CellMappingLocal(pos, prop, type);
	}
}

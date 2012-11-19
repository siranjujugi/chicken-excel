/**
 * 
 */
package com.chicken.excel.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tamnguyen
 * 
 */
public abstract class BeanUtils
{
	private static final String SETTER_METHOD_PREFIX = "set";

	public static String name(Method setterMethod)
	{
		String name = setterMethod.getName();
		if (name.startsWith(SETTER_METHOD_PREFIX))
		{
			name = name.substring(SETTER_METHOD_PREFIX.length());
		}
		return name.toUpperCase();
	}

	public static List<Method> getSetterMethods(Class<?> clazz)
	{
		List<Method> list = new ArrayList<>();
		Method[] methods = clazz.getMethods();
		if (methods != null)
		{
			for (Method method : methods)
			{
				if (isSetter(method))
				{
					list.add(method);
				}
			}
		}
		return list;
	}

	private static boolean isSetter(Method method)
	{
		return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
	}

}

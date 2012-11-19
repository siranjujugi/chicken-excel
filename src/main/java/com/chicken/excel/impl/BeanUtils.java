/**
 * 
 */
package com.chicken.excel.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chicken.excel.core.Predicate;

/**
 * @author tamnguyen
 * 
 */
public abstract class BeanUtils
{
	private static final Predicate<Method> SETTER_METHOD_PREDICATE = new SetterMethodPredicate();

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
		return find(clazz, SETTER_METHOD_PREDICATE);
	}

	public static List<Method> find(Class<?> clazz, Predicate<Method> predicate)
	{
		List<Method> list = new ArrayList<>();
		Method[] methods = clazz.getMethods();
		if (methods != null)
		{
			for (Method method : methods)
			{
				if (predicate.apply(method))
				{
					list.add(method);
				}
			}
		}
		return list;
	}

	private static class SetterMethodPredicate implements Predicate<Method>
	{
		@Override
		public boolean apply(Method input)
		{
			// method name start with "set"
			if (!input.getName().startsWith("set"))
			{
				return false;
			}

			// method only has one parameter.
			Class<?> params[] = input.getParameterTypes();
			if (params == null || params.length != 1)
			{
				return false;
			}
			// type of parameter must be in String, Number, Date or Boolean type.
			return isSupportedType(params[0]);
		}

		private boolean isSupportedType(Class<?> param)
		{
			if (Number.class.isAssignableFrom(param))
			{
				return true;
			}
			else if (Date.class.isAssignableFrom(param))
			{
				return true;
			}
			else if (String.class.isAssignableFrom(param))
			{
				return true;
			}
			else if (Boolean.class.isAssignableFrom(param))
			{
				return true;
			}
			return false;
		}
	}
}

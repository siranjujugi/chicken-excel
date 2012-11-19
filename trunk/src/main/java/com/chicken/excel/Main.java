/**
 * 
 */
package com.chicken.excel;

import java.io.FileInputStream;
import java.util.Enumeration;

import com.chicken.excel.impl.XLSExcelReader;

/**
 * @author tamnguyen
 * 
 */
public class Main
{
	private static long fSLEEP_INTERVAL = 100;

	public static void main(String[] args) throws Exception
	{

		long startMemoryUse = getMemoryUse();

		FileInputStream in = new FileInputStream("test.xls");
		long t1 = System.currentTimeMillis();
		ExcelReader<User> reader = new XLSExcelReader<>(User.class, in);
		Enumeration<User> enumeration = reader.read("user");
		int count = 0;
		while (enumeration.hasMoreElements())
		{
			User user = enumeration.nextElement();
			System.out.println(user.toString());
			count++;
		}
		long t2 = System.currentTimeMillis();
		long endMemoryUse = getMemoryUse();

		System.out.println("Time to read " + count + " rows is " + (t2 - t1)
				+ "ms. Usaged memory: " + (endMemoryUse - startMemoryUse) + " bytes");
	}

	private static long getMemoryUse()
	{
		putOutTheGarbage();
		long totalMemory = Runtime.getRuntime().totalMemory();

		putOutTheGarbage();
		long freeMemory = Runtime.getRuntime().freeMemory();

		return (totalMemory - freeMemory);
	}

	private static void putOutTheGarbage()
	{
		collectGarbage();
		collectGarbage();
	}

	private static void collectGarbage()
	{
		try
		{
			System.gc();
			Thread.sleep(fSLEEP_INTERVAL);
			System.runFinalization();
			Thread.sleep(fSLEEP_INTERVAL);
		}
		catch (InterruptedException ex)
		{
		}
	}
}

package com.chicken.excel;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.Enumeration;

import org.junit.Test;

import com.chicken.excel.impl.XLSExcelReader;

public class XLSExcelReaderTests
{

	@Test
	public void testRead() throws Exception
	{
		FileInputStream in = new FileInputStream("test.xls");
		ExcelReader<User> reader = new XLSExcelReader<>(User.class, in);

		Enumeration<User> enumeration = reader.read("user");
		assertNotNull(enumeration);
		while (enumeration.hasMoreElements())
		{
			User user = enumeration.nextElement();
			assertNotNull(user);
			assertNotNull(user.getId());
			assertNotNull(user.getBirthDay());
			System.out.println(user.toString());
		}
	}

}

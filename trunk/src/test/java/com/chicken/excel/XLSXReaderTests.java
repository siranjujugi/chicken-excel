package com.chicken.excel;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.Enumeration;

import org.junit.Test;

import com.chicken.excel.impl.XLSXReader;

public class XLSXReaderTests
{

	@Test
	public void testRead() throws Exception
	{
		FileInputStream in = new FileInputStream("test.xlsx");
		ExcelReader<User> reader = new XLSXReader<>(User.class, in);

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

# Introduction #
chicken-excel is a library that provide features
  * read excel file and convert into java beans
  * write java bean to excel file (not support yet)

# Usage #
```
FileInputStream in = new FileInputStream("test.xls");
ExcelReader<User> reader = new XLSReader<>(User.class, in);
Enumeration<User> enumeration = reader.read("user"); // read sheet name "user"
while (enumeration.hasMoreElements())
{
	User user = enumeration.nextElement();
	System.out.println(user.toString());
}
```

Java bean is a POJO
```
public class User
{

	private Long _id;
	private String _firstName;
	private String _lastName;
	private Date _birthDay;
	private Boolean _activated;

        .............
}
```

The first row of excel file define header. Each column in header correspond with a property of java bean. The value of columns in header row must be String type and in uppercase.

[You can refer this file for the detail](http://code.google.com/p/chicken-excel/source/browse/trunk/test.xls)

# Requirement #
  * Java 7
  * [Apache POI](http://poi.apache.org)

# Limitations #
  * Support maximum 65535 rows in a sheet.
  * Support java data types: String, Date, Number(Long, Double, Integer, Float) and Boolean
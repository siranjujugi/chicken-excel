/**
 * 
 */
package com.research.apache.poi;

import java.util.Date;

/**
 * @author tamnguyen
 * 
 */
public class User extends Account
{

	private Long _id;
	private String _firstName;
	private String _lastName;
	private Date _birthDay;
	private Boolean _activated;

	public Long getId()
	{
		return _id;
	}

	public void setId(Long id)
	{
		_id = id;
	}

	public String getFirstName()
	{
		return _firstName;
	}

	public void setFirstName(String firstName)
	{
		_firstName = firstName;
	}

	public String getLastName()
	{
		return _lastName;
	}

	public void setLastName(String lastName)
	{
		_lastName = lastName;
	}

	public Date getBirthDay()
	{
		return _birthDay;
	}

	public void setBirthDay(Date birthDay)
	{
		_birthDay = birthDay;
	}

	public Boolean getActivated()
	{
		return _activated;
	}

	public void setActivated(Boolean activated)
	{
		_activated = activated;
	}

	@Override
	public String toString()
	{
		return "User [_id=" + _id + ", _firstName=" + _firstName + ", _lastName=" + _lastName
				+ ", _birthDay=" + _birthDay + ", _activated=" + _activated + ", getLogin()="
				+ getLogin() + ", getPassword()=" + getPassword() + "]";
	}
}

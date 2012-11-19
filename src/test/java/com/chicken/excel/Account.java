/**
 * 
 */
package com.chicken.excel;

/**
 * @author tamnguyen
 * 
 */
public class Account
{

	private String _login;
	private String _password;

	public String getLogin()
	{
		return _login;
	}

	public void setLogin(String login)
	{
		_login = login;
	}

	public String getPassword()
	{
		return _password;
	}

	public void setPassword(String password)
	{
		_password = password;
	}

	@Override
	public String toString()
	{
		return "Account [_login=" + _login + ", _password=" + _password + "]";
	}
}

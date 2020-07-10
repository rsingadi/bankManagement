package com.dxc.service;

import java.util.List;

import com.dxc.dao.CustomerDaoImpl;
import com.dxc.dao.ICustomerDao;
import com.dxc.pojo.Customer;
import com.dxc.pojo.Statements;

public class CustomerServiceImpl implements ICustomerService {

	private ICustomerDao dao=new CustomerDaoImpl();
	public boolean authenticate(int accno,String pass)
	{
		return dao.authenticate(accno,pass);
	}
	public int depositMoney(int a,double d)
	{
		return dao.depositMoney(a,d);
	}
	public  int withdrawMoney(int a,double d)
	{
		return dao.withdrawMoney(a,d);
	}
	public  int transferMoney(int a,double d,int t)
	{
		return dao.transferMoney(a,d,t);
	}
	public List<Statements> printStatement(int a)
	{
		return dao.printStatement(a);
	}
	public int changePassword(int a,String pass,String pass1)
	{
		return dao.changePassword(a,pass,pass1);
	}
	}


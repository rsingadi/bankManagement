package com.dxc.dao;

import java.util.List;

import com.dxc.pojo.Customer;
import com.dxc.pojo.Statements;

public interface ICustomerDao {
	public boolean authenticate(int accno,String pass);
	public  int depositMoney(int a,double d);
	public  int withdrawMoney(int a,double d);
	public  int transferMoney(int a,double d,int t);
	public List<Statements> printStatement(int a);
	public int changePassword(int a,String pass,String pass1);
}


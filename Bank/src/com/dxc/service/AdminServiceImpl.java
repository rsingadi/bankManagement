package com.dxc.service;

import com.dxc.dao.IAdminDao;
import com.dxc.pojo.Customer;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;

public class AdminServiceImpl implements IAdminService{

	private IAdminDao dao=new AdminDaoImpl();
	
	public boolean authenticate(String aname,String pass)
	{
		return dao.authenticate(aname,pass);
	}
	public boolean addCustomer(int number, String name,double bal,String pass)
	{
		return dao.addCustomer(number,name,bal,pass);
	}
	public List<Customer> getAllCustomer()
	{
		return dao.getAllCustomer();
	}
	public List<Customer> findCustomer(int anumber)
	{
		return dao.findCustomer(anumber);
	}
	public void modifyCustomer(int number,String name)
	{
		dao.modifyCustomer(number, name);
	}
	public List<Double> getBalance(int number)
	{
		return dao.getBalance(number);
	}
	public boolean deleteCustomer(int number)
	{
		return dao.deleteCustomer(number);
	}
}

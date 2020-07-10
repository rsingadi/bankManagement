package com.dxc.service;

import java.util.List;

import com.dxc.pojo.Customer;

public interface IAdminService {
	
	public boolean authenticate(String aname,String pass);

	public boolean addCustomer(int number, String name,double bal,String pass);

	public List<Customer> getAllCustomer();
	
	public List<Customer> findCustomer(int anumber);
	
	public void modifyCustomer(int number,String name);

	public List<Double> getBalance(int number);
	
	public boolean deleteCustomer(int number);
}

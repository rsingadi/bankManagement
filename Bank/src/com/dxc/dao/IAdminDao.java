package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.dxc.pojo.Customer;

import java.sql.*;
public interface IAdminDao {

public boolean authenticate(String aname,String pass);
public boolean addCustomer(int number, String name,double bal,String pass);
public List<Customer> getAllCustomer();
public List<Customer> findCustomer(int anumber);
public void modifyCustomer(int number,String name);
public List<Double> getBalance(int number);
public boolean deleteCustomer(int number);
}

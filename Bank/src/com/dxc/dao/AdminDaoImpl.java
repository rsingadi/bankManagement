package com.dxc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dxc.pojo.Customer;



public class AdminDaoImpl implements IAdminDao {
	private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded...");
			
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?autoReconnect=true&useSSL=false", "root", "rash");
			System.out.println("connected to database....");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean authenticate(String aname,String pass)
	{
		try
		{
			 Statement stmt = conn.createStatement();
			 ResultSet rset = stmt.executeQuery("select name,password from administrator");
			
			 while(rset.next())
			 {
				 if(rset.getString(1).equals(aname) && rset.getString(2).equals(pass))
				 {
					 return true;
				 }
			 }
		}
		catch (Exception e) 
		{  
			//System.out.println("authentication failed");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addCustomer(int number, String name,double bal,String pass)
	{     
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into customer values(?,?,?,?)");
			pstmt.setInt(1,number);
			pstmt.setString(2,name);
			pstmt.setDouble(3,bal);
			pstmt.setString(4, pass);
			pstmt.execute();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Customer> getAllCustomer()
	{  
		List<Customer> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customer");
			while(rs.next())
			{
				list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public List<Customer> findCustomer(int anumber)
	{  int a=0;
		List<Customer> list=new ArrayList<>();
		try {
			System.out.println("hi");
			PreparedStatement pstmt=conn.prepareStatement("select * from customer where anumber=?");
			pstmt.setInt(1,anumber);
			pstmt.execute();
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{   
			a=rs.getInt(1);
			if(a==anumber) {
				list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
				return list;
			}
			else {
				return null;
			}
			
		}
			
		}
			catch(Exception e) {
				System.out.println("hello");
				e.printStackTrace();
				return null;
			}
		
	return null;
	}
	public void modifyCustomer(int number,String name) {
	try {
		PreparedStatement pstmt=conn.prepareStatement("update customer set cname=? where anumber=?");
		pstmt.setString(1,name);
		pstmt.setInt(2,number);
		pstmt.executeUpdate();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	}
	public List<Double> getBalance(int number)
	{
		List<Double> list=new ArrayList<>();
	    double balance=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement("select balance from customer where anumber=?");
			pstmt.setInt(1,number);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{ 
				balance=rs.getDouble(1);
				System.out.println("balance ="+balance);
				list.add(balance);
				System.out.println("balance");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}	
	public boolean deleteCustomer(int number)
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from customer where anumber=?");
			pstmt.setInt(1,number);
			pstmt.execute();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	}
	

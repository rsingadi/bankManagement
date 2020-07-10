package com.dxc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojo.Customer;
import com.dxc.pojo.Statements;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javax.servlet.http.HttpSession;

public class CustomerDaoImpl implements ICustomerDao{
private static Connection conn;
	
HttpSession session;
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

	public boolean authenticate(int accno, String pass)
	{
		try
		{

			 Statement stmt = conn.createStatement();
			 ResultSet rset = stmt.executeQuery("select anumber,password from customer");
			
			 while(rset.next())
			 {
				if(rset.getInt(1)==(accno) && rset.getString(2).equals(pass))
				 {
					 return true;
				 }
			 }
		}
		catch (Exception e) 
		{
			System.out.println("Authentication Error Occured!!!");
			e.printStackTrace();
		}
		return false;
	}
	public int depositMoney(int a,double d)
	{  
		double balance=0;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from customer where anumber=?");
			pstmt.setInt(1, a);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				balance=rs.getDouble(3);
				balance=balance+d;
			}
			pstmt.close(); 
			PreparedStatement pstmt1=conn.prepareStatement("update customer set balance=? where anumber=?");
		    pstmt1.setDouble(1, balance);
		    pstmt1.setInt(2, a);
		    pstmt1.executeUpdate();	
		    PreparedStatement pstmt2=conn.prepareStatement("insert into transaction values(?,?,?)");
		    pstmt2.setInt(1,a);
		    pstmt2.setDouble(2,d);
		    pstmt2.setString(3,"deposit");
		    pstmt2.executeUpdate();
		    return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	public int withdrawMoney(int a,double d)
	{   
		double balance=0;
		int deduct=0;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from customer where anumber=?");
			pstmt.setInt(1, a);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				balance=rs.getDouble(3);
				if(balance-d>=0)
				{  
					System.out.println("hello");
					balance=balance-d;
					deduct=1;
				}
				else
				{
					return 1;
				}
				if(deduct==1)
				{
					System.out.println("hi");
			PreparedStatement pstmt1=conn.prepareStatement("update customer set balance=? where anumber=?");
		    pstmt1.setDouble(1, balance);
		    pstmt1.setInt(2, a);
		    pstmt1.executeUpdate();	
		    PreparedStatement pstmt2=conn.prepareStatement("insert into transaction values(?,?,?)");
		    pstmt2.setInt(1,a);
		    pstmt2.setDouble(2,d);
		    pstmt2.setString(3,"withdraw");
		    pstmt2.executeUpdate();
		    return 0;
		}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	public  int transferMoney(int a,double d,int t)
	{
		double bal=0;
		  double bal1=0;

			try {
				PreparedStatement pstmt1 = conn.prepareStatement("select * from customer where anumber=?");
				pstmt1.setInt(1,a);
				ResultSet rs1 = pstmt1.executeQuery(); 
				rs1.next();
				bal = rs1.getDouble(3);

				double bala = bal -d;

					PreparedStatement pstmt2 = conn.prepareStatement("update customer set balance = ? where anumber=? ");
					pstmt2.setDouble(1, bala);
					pstmt2.setInt(2,a);
					pstmt2.execute();

					PreparedStatement pstmt3 = conn.prepareStatement("select * from customer where anumber=?");
					pstmt3.setInt(1,t);
					ResultSet rs2 = pstmt3.executeQuery();
					rs2.next();
					bal1 = rs2.getDouble(3);
					bal1 = bal1 + d;

					PreparedStatement pstmt4 = conn.prepareStatement("update customer set balance = ? where anumber = ?");
					pstmt4.setDouble(1, bal1);
					pstmt4.setInt(2, t);
					pstmt4.executeUpdate();

		    
            PreparedStatement pstmt6=conn.prepareStatement("insert into transaction values(?,?,?)");
            System.out.println("inserted");
		    pstmt6.setInt(1,a);
		    pstmt6.setDouble(2,d);
		    pstmt6.setString(3,"withdraw");
		    pstmt6.executeUpdate();
		    PreparedStatement pstmt5=conn.prepareStatement("insert into transaction values(?,?,?)");
		    pstmt5.setInt(1,t);
		    pstmt5.setDouble(2,d);
		    pstmt5.setString(3,"deposit");
		    pstmt5.executeUpdate();
		    
		    return 0;
		    
			}
	catch(Exception e)
		{
			e.printStackTrace();
		}
		return 1;

		}
	public List<Statements> printStatement(int a)
	{
		int i=0;
		List<Statements> list=new ArrayList<>();
		List<Statements> list2=new ArrayList<>();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from transaction where anumber=?");
			pstmt.setInt(1, a);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				list.add(new Statements(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}   	
		if(list.size()>5)
		{  System.out.println("hi");
	   		int l=4;
			while(i<5)
	   		{   
	    			list2.add(list.get(l));
	   			l--;
	   			i++;
	   		}
	   		return list2;
		}
		else 
		{  System.out.println("hello");
			return list;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;	
		
	}
	public int changePassword(int a,String pass,String pass1) {
		if(pass.equals(pass1))
		{
		try {
			
			PreparedStatement pstmt=conn.prepareStatement("update customer set password=? where anumber=?");
			pstmt.setString(1, pass);
			pstmt.setInt(2, a);
			pstmt.executeUpdate();
			return 0;
		}
	
	
	catch(Exception e)
		{
		e.printStackTrace();
		}
		return 2;
		}
		else
		{
			return 1;
		}
	}
	
	}


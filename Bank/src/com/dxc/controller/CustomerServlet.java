package com.dxc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojo.Customer;
import com.dxc.pojo.Statements;
import com.dxc.service.CustomerServiceImpl;
import com.dxc.service.ICustomerService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

@WebServlet("/cust")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ICustomerService customerservice=new CustomerServiceImpl();
     
    int accno=0;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		String message1="";
		String action="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		
		
		if(action.equals("log in"))
		{
			
	    accno=Integer.parseInt(request.getParameter("accno"));
		String pass=request.getParameter("password");
		boolean b=customerservice.authenticate(accno,pass);
		if(b==true){
	    response.sendRedirect("customerpage.jsp");
		}
		else if(b==false)
		{
			message1="authentication failed";
			session.setAttribute("message", message1);
			response.sendRedirect("view.jsp");
				
		}	
	}
		else if(action.equals("deposit"))
		{
			System.out.println(accno);
			int a=accno;
			Double d=Double.parseDouble(request.getParameter("deposit"));
			int b=customerservice.depositMoney(a,d);
			if(b==0)
			{
			message1="money is deposited!!!";
		    session.setAttribute("message", message1);
			response.sendRedirect("view.jsp");
			}
			else if(b==1)
			{
				message1="failed to deposit money!!!";
			    session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
			}
		}
		else if(action.equals("withdraw"))
		{
			int a=accno;
			Double d=Double.parseDouble(request.getParameter("withdraw"));
			int b=customerservice.withdrawMoney(a,d);
			if(b==0)
			{
			message1="money is withdrawn!!!";
		    session.setAttribute("message", message1);
			response.sendRedirect("view.jsp");
			}
			else if(b==1)
			{
				message1="insufficient balance!!!";
			    session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
			}
		}
		else if(action.equals("transfer"))
		{
			int a=accno;
			Double d=Double.parseDouble(request.getParameter("transfer"));
			int t=Integer.parseInt(request.getParameter("t"));
			int b=customerservice.transferMoney(a,d,t);
	   		 if(b==0)
	   		 {
	   			message1="transaction success!!!";
	   			session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
	   		 }
	   		 else if(b==1)
	   		 {
	   			message1="faile to transfer!!!";
			    session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
	   		 }
	   	   }
		
		else if(action.equals("change password"))
		{
			System.out.println("pass");
			int a=accno;
			String pass=request.getParameter("pass");
			String pass1=request.getParameter("pass1");
		    int z=customerservice.changePassword(a,pass,pass1);
			if(z==0){
				message1="password is changed!!!";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
					
			}
			else if(z==1)
			{
				message1="password is not matching!!!";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");			
			}
			else if(z==2)
			{
				message1="failed to change password!!!";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");	
			}
		}
		else 
		{   int a=accno;
			List<Statements> list=customerservice.printStatement(a);
	     	session.setAttribute("list", list);
			
	   		response.sendRedirect("displaystatement.jsp");
		}
	   		 
	}
}
	
	
	
	
	
	

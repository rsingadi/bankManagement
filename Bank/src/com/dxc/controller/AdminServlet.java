package com.dxc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dxc.pojo.Customer;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.IAdminService;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IAdminService adminservice=new AdminServiceImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message1="";
		//String message2="";
		String action="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		
		if(action.equals("log in"))
		{
		String aname=request.getParameter("name");
		String pass=request.getParameter("password");
		boolean b=adminservice.authenticate(aname,pass);
		if(b==true){
	    response.sendRedirect("adminpage.jsp");
		}
		else if(b==false)
		{
			message1="authentication failed";
			session.setAttribute("message", message1);
			response.sendRedirect("view.jsp");
				
		}	
	}
		else if(action.equals("add customer"))
		{
			int number=Integer.parseInt(request.getParameter("accountnumber"));
			String name=request.getParameter("customername");
			double bal=Double.parseDouble(request.getParameter("balance"));
			String pass=request.getParameter("password");
			boolean b=adminservice.addCustomer(number,name,bal,pass);
			if(b==true)
			{
				response.sendRedirect("adminpage.jsp");
			}
			else if(b==false)
			{
				message1="failed to add customer";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
			}
		}
		else if(action.equals("find customer"))
		{
			int anumber=Integer.parseInt(request.getParameter("acnumber"));
			List<Customer> list=adminservice.findCustomer(anumber);
			session.setAttribute("list", list);
			if(list==null)
			{
				System.out.println("no");
				message1="no such customer found";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
			
			} else {
				
				response.sendRedirect("displayallcustomer.jsp");
			}
		}
		else if(action.equals("modify"))
		{
			int number=Integer.parseInt(request.getParameter("acnumber"));
			String name=request.getParameter("cname");
			adminservice.modifyCustomer(number,name);
			message1="customer data is modified";
			session.setAttribute("message", message1);
			response.sendRedirect("view.jsp");
			
		}
		else if(action.equals("get balance"))
		{
			int number=Integer.parseInt(request.getParameter("acnumber"));
			List<Double> list=adminservice.getBalance(number);
			session.setAttribute("listbal", list);
			response.sendRedirect("displaybalance.jsp");
		}
		else if(action.equals("delete"))
		{
			int number=Integer.parseInt(request.getParameter("acnumber"));
			boolean b=adminservice.deleteCustomer(number);
			if(b==true)
			{
				response.sendRedirect("adminpage.jsp");
			}
			else if(b==false)
			{
				message1="failed to delete customer";
				session.setAttribute("message", message1);
				response.sendRedirect("view.jsp");
			}
		}
		else
		{
			List<Customer> list=adminservice.getAllCustomer();
			session.setAttribute("list", list);
			response.sendRedirect("displayallcustomer.jsp");
		}
}
}

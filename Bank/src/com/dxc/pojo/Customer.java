package com.dxc.pojo;

public class Customer {
private int accountnumber;
private String customername;
private double balance;
public Customer(int accountnumber, String customername, double balance) {
	super();
	this.accountnumber = accountnumber;
	this.customername = customername;
	this.balance = balance;
}
public int getAccountnumber() {
	return accountnumber;
}
public void setAccountnumber(int accountnumber) {
	this.accountnumber = accountnumber;
}
public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

public void display()
{
	System.out.println(accountnumber+" "+customername+" "+balance);
}

}

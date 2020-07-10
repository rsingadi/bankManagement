package com.dxc.pojo;

public class Statements {
private int anumber;
private String cname;
private String type;
public Statements(int anumber, String cname, String type) {
	super();
	this.anumber = anumber;
	this.cname = cname;
	this.type = type;
}
public int getAnumber() {
	return anumber;
}
public void setAnumber(int anumber) {
	this.anumber = anumber;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Override
public String toString() {
	return "Statements [anumber=" + anumber + ", cname=" + cname + ", type=" + type + "]";
}


}

package com.spring.model;

public class Employee {
	private int id;
	private String name;
	private String role;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getrole() {
		return role;
	}
	public void setrole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		return "{ID="+id+",Name="+name+",Role="+role+"}";
	}
}

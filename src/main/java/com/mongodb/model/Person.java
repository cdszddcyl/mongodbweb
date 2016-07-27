package com.mongodb.model;

import java.io.Serializable;

public class Person extends IdEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3736372815337625596L;
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}

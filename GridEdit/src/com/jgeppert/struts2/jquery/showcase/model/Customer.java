package com.jgeppert.struts2.jquery.showcase.model;

public class Customer implements Comparable<Customer> {
    private int id;
    private String name;

    public Customer() {
	super();
    }


    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

   

   
    public int compareTo(Customer o) {
	return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }

   

	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

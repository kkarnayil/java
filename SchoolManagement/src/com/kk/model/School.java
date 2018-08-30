/**
 * 
 */
package com.kk.model;

import java.util.List;

/**
 * @author kartikkarnayil
 *
 */
public class School {
	
	private int id = 1;
	private String name;
	private Address address;
	private List<Person> people;
	
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param people
	 */
	public School(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the people
	 */
	public List<Person> getPeople() {
		return people;
	}
	/**
	 * @param people the people to set
	 */
	public void setPeople(List<Person> people) {
		this.people = people;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", address=" + address + ", people=" + people + "]";
	}
	
	

}

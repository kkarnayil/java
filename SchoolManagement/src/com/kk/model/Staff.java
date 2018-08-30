/**
 * 
 */
package com.kk.model;

/**
 * @author kartikkarnayil
 *
 */
public class Staff extends Person{

	private String type;
	
	public Staff(String id, String name, int age) {
		super(id, name, age);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
		
	}

}

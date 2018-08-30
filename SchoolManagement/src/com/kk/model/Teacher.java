/**
 * 
 */
package com.kk.model;

import com.kk.imodel.Teaching;

/**
 * @author kartikkarnayil
 *
 */
public class Teacher extends Staff implements Teaching{
	
	private String subject;

	public Teacher(String id, String name, int age) {
		super(id, name, age);
	}
	
	public Teacher(String id, String name, int age, String subject) {
		super(id, name, age);
		this.subject = subject;
	}

	@Override
	public String teaches() {
		return this.subject;
	}
	

	

}

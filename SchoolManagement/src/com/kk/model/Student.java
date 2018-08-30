/**
 * 
 */
package com.kk.model;

/**
 * @author kartikkarnayil
 *
 */
public class Student extends Person {
	
	private String classNo;

	public Student(String id, String name, int age, String classNo) {
		super(id, name, age);
		this.classNo = classNo;
	}

	/**
	 * @return the classNo
	 */
	public String getClassNo() {
		return classNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [classNo=" + classNo + "]";
	}
	
	

}

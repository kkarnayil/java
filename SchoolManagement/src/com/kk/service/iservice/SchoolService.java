/**
 * 
 */
package com.kk.service.iservice;

import com.kk.model.School;

/**
 * @author kartikkarnayil
 *
 */
public interface SchoolService {
	
	public void addSchool(School school);
	
	public void printSchools();
	
	public void deleteSchool(int id);
	
	public void addStudent(String name, String age);
	
	public void getStudentOfSchool(String schoolId);
	
	public void getAllStudents();
	
	public void getAllTeachers();

}

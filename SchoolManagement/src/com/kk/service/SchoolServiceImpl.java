/**
 * 
 */
package com.kk.service;

import java.util.ArrayList;
import java.util.List;

import com.kk.model.School;
import com.kk.service.iservice.SchoolService;

/**
 * @author kartikkarnayil
 *
 */
public class SchoolServiceImpl implements SchoolService {
	
	private static int schoolId = 0;
	private List<School> schools;
	
	public SchoolServiceImpl() {
		schools = new ArrayList<>();
	}

	@Override
	public void addSchool(School school) {
		school.setId(schoolId++);
		schools.add(school);		
	}

	@Override
	public void printSchools() {	
		if(schools.isEmpty())
			System.out.println("No Schools Added.");
		
		for(School school:schools) {
			System.out.println(school);
		}	
	}

	@Override
	public void deleteSchool(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addStudent(String name, String age) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStudentOfSchool(String schoolId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllStudents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllTeachers() {
		// TODO Auto-generated method stub
		
	}

}

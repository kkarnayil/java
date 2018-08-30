/**
 * 
 */
package com.main;

import java.util.Scanner;

import com.kk.model.Address;
import com.kk.model.School;
import com.kk.service.SchoolServiceImpl;
import com.kk.service.iservice.SchoolService;

/**
 * @author kartikkarnayil
 *
 */
public class App {
	
	static Scanner sc = new Scanner(System.in);
	static SchoolService schoolService = new SchoolServiceImpl();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			while(true) {
				System.out.print("--------------"+
			    "\n1. Add School"+
			    "\n2. Print Schools"+
			    "\n2. Add Person"+
			    "\n4. Exit"+
			    "\n--------------"+
			    "\nEnter option: "
			    );
				
				String option = sc.next();

				switch (option) {
				case "1":
					addSchool();
					break;
				case "2":
					printSchools();
					break;
				case "3":
					addPerson();
					break;
				case "4":
					System.exit(0);
				default:
					System.out.println("Invalid Input!!!.");
					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sc) {
				sc.close();
			}
		}
	}

	private static void addSchool() {
		System.out.println("Add School");
		System.out.print("Enter name: ");
		String name = sc.next();
		System.out.print("Enter Street Address: ");
		String streetAddress = sc.next();
		System.out.print("Enter City: ");
		String city = sc.next();
		System.out.print("Enter Zip: ");
		String zip = sc.next();
		System.out.print("Enter State: ");
		String state = sc.next();
		System.out.print("Enter Country: ");
		String country = sc.next();

		Address address = new Address(streetAddress, city, zip, state, country);
		School school = new School(name, address);
		schoolService.addSchool(school);
	}

	private static void printSchools() {
		schoolService.printSchools();
	}

	private static void addPerson() {
		System.out.println("Add Person");

	}

}

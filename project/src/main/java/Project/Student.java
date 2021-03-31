package Project;

import java.util.ArrayList;

/* 
 * KLasse som oppretter en student og sier hvilke attributter studenten skal ha
 */

public class Student {
	
	private String firstName;
	private String lastName;
	private String studentID;
	
	//Arraylist med alle emner hver enkelt student har
	//private ArrayList<Course> studentCourses;
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param studentID
	 * @param studentCourses 
	 */
	public Student(String firstName, String lastName, String studentID) { //ArrayList<Course> studentCourses
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		//this.studentCourses = studentCourses;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + studentID;
	}
	
	
}

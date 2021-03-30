package Project;

import java.util.ArrayList;

public class StudentRegister {
	
	/*
	 * Lage liste med studenter
	 * Liste med emner
	 * Registrer student
	 * Registrere et emne
	 */
	
	public ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<Course> courses = new ArrayList<Course>();
	
	
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	/*
	 * Opprett ny student
	 */
	
	//hvis sann, returner true (ny student), hvis ikke returnerer komplett eller finnes fra før
	public Student registerNewStudent(String firstName, String lastName, String studentID) {
		Student newStudent = new Student(firstName, lastName, studentID);
		this.students.add(newStudent);
		return newStudent;
	}
	
	/*
	 * Opprett nytt emne
	 */
	
	public Course registerNewCourse(String courseID, String semester, String year, String grade) {
		Course newCourse = new Course(courseID, semester, year, grade);
		this.courses.add(newCourse);
		return newCourse;		
	}
	
	


}

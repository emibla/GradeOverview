package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Course> getCoursesByID(String courseID){
		(match = courses.stream()
				.filter(course -> course.getCourseID() == courseID)
				.orElse(null);
		if (match !=null) {
			return match;
		} else {
			return null;
		}
	}
	*/
	
	
	
	
	/**
	 * 
	 * @param studentID
	 * @param course
	
	public void addCourseToStudent(String studentID, Course course) {
		 ArrayList<Course> studentCourses;
		for(int i = 0; i < students.size(); i++) {
			if(getStudents().get(i).getStudentID().equals(studentID)) {
				//this.students.registerNewCourse(course);
	
				
			}
		}
	}
	
	 */
	
	//Opprett ny student
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
	
	
	public boolean findCourseByID(String studentID, String courseID) {
		boolean ifSuccess = false;
		for(int i = 0; i < students.size(); i++ ) {
			if(students.get(i).getStudentID().equals(studentID)) {
				for(int b = 0; b < courses.size(); b++) {
					if(getCourses().get(b).getCourseID().equals(courseID)) {
						ifSuccess = true;
					}
			}
		}
			
		}
		return ifSuccess;
		
	}
	
	/**
	 * Method to find student by studentID
	 * @param studentID 
	 * @return returns a student  if found, else returns null
	 */
	public Student findStudentByID(String studentID) {
		//ArrayList<Student> studentIDsearch = new ArrayList();
		//Student stud;
		for(int i = 0; i < students.size(); i++) {
			if(getStudents().get(i).getStudentID().equals(studentID)) {
				return getStudents().get(i);
			}
		}
		return null;
	}
	
	
	/*
	 * Didn't work
	public void fetchStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Tast inn studentID");
		}
		
		Student s = students.get(student.getStudentID());
		
//Student s = students.get(student.getStudentID());
		
	}
	
	
*/

}

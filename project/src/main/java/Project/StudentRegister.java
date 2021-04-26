package Project;

import java.util.ArrayList;
import java.util.List;

public class StudentRegister{
	
	
	/*
	 * List of students
	 */
	
	private List<Student> students = new ArrayList<Student>();
	
	
	
	/*
	 * Returns a copy of student list
	 */
	
	public List<Student> getStudents() {
		List<Student> studentsCopy = new ArrayList<Student>();
		studentsCopy.addAll(students);
		return studentsCopy;
	}
	
	
	/*
	 * Method to register a new student, add to student List
	 * Throw new Illegal Argument Exception if student is already registered
	 */
	
	public Student registerNewStudent(String firstName, String lastName, String studentID) throws IllegalArgumentException {
		if(students.stream().anyMatch(c -> c.getStudentID().equals(studentID))) {
			throw new IllegalArgumentException("StudentID er allerede registrert,\nvennligst logg inn");
		} else {
		Student newStudent = new Student(firstName, lastName, studentID);
		this.students.add(newStudent);
		return newStudent;
	}
}
	
	/*
	 * Method to find a student by studentID 
	 */

	public Student findStudentByID(String studentID) {
		Student stud = null;
		for(int i = 0; i < students.size(); i++) {
			if(getStudents().get(i).getStudentID().equals(studentID)) {
				stud = getStudents().get(i);	
			}
		}
		return stud;
	}
}
package Project;

import java.util.ArrayList;
import java.util.List;

public class StudentRegister{
	
	/*
	 * Lage liste med studenter
	 * Liste med emner
	 * Registrer student
	 * Registrere et emne
	 */
	
	
	public List<Student> students = new ArrayList<Student>();
	
	public List<Student> getStudents() {
		return this.students;
	}
	
	//Opprett ny student
	//hvis sann, returner true (ny student), hvis ikke returnerer komplett eller finnes fra før
	public Student registerNewStudent(String firstName, String lastName, String studentID) {
		if(!isValidFirstName(firstName)) {
			throw new IllegalArgumentException("Ugyldig fornavn");
		} else if (!isValidLastName(lastName)) {
			throw new IllegalArgumentException("Ugyldig etternavn");
		} else if(!isValidStudentID(studentID)) {
			throw new IllegalArgumentException("Ugyldig studentID");
//		} else if(firstName == null || firstName.isBlank() || lastName== null || lastName.isBlank() || studentID == null || studentID.isBlank()) {
//			throw new NullPointerException("Ingen felter kan være tomme");		
		} else if(students.stream().anyMatch(c -> c.getStudentID().equals(studentID))) {
			throw new IllegalArgumentException("StudentID er allerede registrert,\nvennligst logg inn");
		} else {
		Student newStudent = new Student(firstName, lastName, studentID);
		this.students.add(newStudent);
		return newStudent;
	}}
	
	
	/**
	 * Method to find student by studentID
	 * @param studentID s
	 * @return returns a student  if found, else returns null
	 */
	
	//må ha en nullsjekk hver gang brukes pga return null, se 
	public Student findStudentByID(String studentID) {
		Student stud = null;
		for(int i = 0; i < students.size(); i++) {
			if(getStudents().get(i).getStudentID().equals(studentID)) {
				stud = getStudents().get(i);	
			}
		}
		return stud;
	}
	
	private boolean isValidFirstName(String firstName) {
		return (firstName.matches("^[a-zA-Z\s]*$") && !firstName.isBlank());
	}
	private boolean isValidLastName(String lastName) {
		return (lastName.matches("^[a-zA-Z\s]*$") && !lastName.isBlank());
	}
	
	private boolean isValidStudentID(String studentID) {
		if ((!studentID.matches("^[0-9]*$") || studentID.length() != 4)) {
			return false; 
			}
		return true;
	}
}
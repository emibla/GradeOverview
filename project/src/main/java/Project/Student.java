package Project;

/* 
 * KLasse som oppretter en student og sier hvilke attributter studenten skal ha
 */

public class Student {
	
	private String firstName;
	private String lastName;
	private String studentID;
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param studentID
	 */
	public Student(String firstName, String lastName, String studentID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
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
		return "\n" + firstName + " " + lastName + " " + studentID;
	}
	
	
}

package Project;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Course {
	
	private String courseID; 
	private String semester; 
	private int year; 
	private String grade; 
	
	private static List<String> validGrade = Arrays.asList("A", "B", "C", "D", "E");
	
	
	/*
	 * Checks if values are valid, if so set values
	 * Throws IllegalArgumentException if not
	 */

	public Course(String courseID, String semester, int year, String grade) {
		if(!isValidCourseID(courseID)){
			throw new IllegalArgumentException("Emnenavn starter med bokstaver\nog kan inneholde tall");
		} else if(!isValidYear(year)) {
			throw new IllegalArgumentException("Tast inn et år mellom 2000\nog inneværende år");
		} else if(!isValidSemester(semester)) {
			throw new IllegalArgumentException("Velg semesteret du tok emnet");
		} else if(!validGrade.contains(grade)){
			throw new IllegalArgumentException("Kun beståtte karakterer A-E er gyldige");
		} else {
		this.courseID = courseID;
		this.semester = semester;
		this.year = year; 
		this.grade = grade;
		}
	}
	
	public String getCourseID() {
		return courseID;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getGrade() {
		return grade;
	}
	
	@Override
	public String toString() {
		return "CourseID: " + courseID + " Semester: " + semester + " Year: " + year + " Grade: " + grade;
	
	}
	
	
	/*
	 * Returns true if courseID starts with a letter and contains letters and digits
	 */
	
	private boolean isValidCourseID (String courseID){
		if ((!courseID.matches("^[a-zA-ZæøåÆØÅ][0-9a-zA-ZæøåÆØÅ]*$") || courseID.isBlank() )){
			return false;
		}
		return true;
	}
	
	
	/*
	 * Returns true if year is between 2000 and year to date
	 */
	
	private boolean isValidYear(int year) {
		LocalDateTime ldt = LocalDateTime.now();
		int thisyear = ldt.getYear();
		if (year < 2000 || year > thisyear) {
			return false;
		}
		return true;
	}
	
	
	/*
	 * returns true if semester is either "Høst" or "Vår", ignoring upper or lower case
	 */
	
	private boolean isValidSemester(String semester) {
		if (semester.equalsIgnoreCase("Høst") || semester.equalsIgnoreCase("Vår")) {
			return true;
		} else {
		return false;
	}
	}
	
}

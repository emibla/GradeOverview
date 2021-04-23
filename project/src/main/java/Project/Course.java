package Project;


/*
 * Hvert Course skal ha en emnekode, et semester, årstall og en karakter
 */

public class Course {
	
	private String courseID; //Emnekode 
	private String semester; // Høst eller Vår
	private int year; //årstall
	private String grade; // A, B, C, D, E 
	

	public Course(String courseID, String semester, int year, String grade) {
		this.courseID = courseID;
		this.semester = semester;
		this.year = year; 
		this.grade = grade;
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
	
	
}

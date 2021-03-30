package Project;


/*
 * Hvert Course skal ha en emnekode, et semester, årstall og en karakter
 * Som student
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
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	public String toString() {
		return "CourseID: " + courseID + " semester: " + semester + " Year: " + year + " Grade: " + grade;
	
	}
	
	
}

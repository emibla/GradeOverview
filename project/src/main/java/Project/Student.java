package Project;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Student {
	
	private String firstName;
	private String lastName;
	private final String STUDENT_ID;
	private List<Course> studentCourses;
	
	
	/*
	 * Checks if valid input is inserted, if so set values
	 * Each student has a list of studentCourses, which is checked for valid input in own class Course
	 */
	
	public Student(String firstName, String lastName, String studentID) { 
		if(!isValidFirstName(firstName)) {
			throw new IllegalArgumentException("Skriv inn et gyldig fornavn, kun bokstaver");
		} else if (!isValidLastName(lastName)) {
			throw new IllegalArgumentException("Skriv inn et gyldig etternavn, kun bokstaver");
		} else if(!isValidStudentID(studentID)) {
			throw new IllegalArgumentException("Student ID skal bestå av 4 siffer");
		} else {
		this.firstName = firstName;
		this.lastName = lastName;
		this.STUDENT_ID = studentID;
		this.studentCourses = new ArrayList<Course>();
	}
}
	
	/*
	 * Adds course to studentCourses
	 * Throws IllegalArgumentException if not able to create new Course, validated in class Course
	 */

	public void addCourse(String courseID, String semester, int year, String grade) throws IllegalArgumentException {
		Course cour = new Course(courseID, semester, year, grade);
		this.studentCourses.add(cour);
	}
	
	
	/*
	 * Return a copy of studentCourses
	 */
	
	public List<Course> getCourses(){
		List<Course> studentCoursesCopy = new ArrayList<Course>();
		studentCoursesCopy.addAll(studentCourses);
		return studentCoursesCopy;
	}
	
	
	/*
	 * Method to find a Course by courseID
	 */

	public Course findCourseByID(String courseID) {
		Course cour = null;
		for(int i = 0; i < studentCourses.size(); i++) {
			if(getCourses().get(i).getCourseID().equalsIgnoreCase(courseID)) {
				cour = getCourses().get(i);
			}
		}return cour;
	}

	
	/*
	 * Removes course from studentCourses
	 */
	
	public void removeCourse(String courseID) {
		Iterator<Course> iterator = studentCourses.iterator();
		while(iterator.hasNext()) {
			Course nCourse = iterator.next();
			if(nCourse.getCourseID().equalsIgnoreCase(courseID)) {
				iterator.remove();
				studentCourses.remove(nCourse);
			}
		}
	}
	
	
	/*
	 * Gives grade a value
	 */
	
	public static int gradeAsInt(String grade) {
		if(grade.equals("A")) {
			return 5;
		}
		else if(grade.equals("B")){ 
			return 4;
		}
		else if(grade.equals("C")){ 
			return 3;
		}
		else if(grade.equals("D")){ 
			return 2;
		}
		else {
			return 1;
		}
	}
	
		
	/*
	 * Calculates the average of grades
	 * Throw new IllegalStateException if not logged in or no registered courses
	 */
	
	public String averageGrade (String studentID) {	
		
		double sum = 0;
		double num = 0;
		String avg = "";
		double average = 0;
		
		if (studentID == null) {
			throw new IllegalStateException("Vennligst logg inn først");
		} else if(studentCourses.size() == 0) {
			throw new IllegalStateException("Du har ingen registrerte emner");
		}
		else {
				for (int i = 0; i < studentCourses.size(); i++) {
					if (studentCourses.get(i).getGrade().equals("A")) {
						sum += 5;
						num += 1;
					} else if (studentCourses.get(i).getGrade().equals("B")) {
						sum += 4;
						num += 1;
					} else if (studentCourses.get(i).getGrade().equals("C")) {
						sum += 3;
						num += 1;
					} else if (studentCourses.get(i).getGrade().equals("D")) {
						sum += 2;
						num += 1;			
					} else if (studentCourses.get(i).getGrade().equals("E")) {
						sum += 1;
						num += 1;
				}
				average = sum/num;
				DecimalFormat df2 = new DecimalFormat("#.##");
				avg = df2.format(average);
		}
		return avg;
	}}
	
	
	/*
	 * Calculates the median of grades
	 * Throw new IllegalStateException if not logged in or no registered courses
	 */
	 
	public String medianGrade (String studentID) {	
		List<Integer> medianCourse = new ArrayList<Integer>();
		double middle = 0;
		String stringMedian = ""; 
			if (studentID == null) {
				throw new IllegalStateException("Vennligst logg inn først");
			} else if(studentCourses.size() == 0) {
				throw new IllegalStateException("Du har ingen registrerte emner");
			}
			else{
				for (int i = 0; i < studentCourses.size(); i++) {
					if (studentCourses.get(i).getGrade().equals("A")) {
						medianCourse.add(5);
					} else if (studentCourses.get(i).getGrade().equals("B")) {
						medianCourse.add(4);
					} else if (studentCourses.get(i).getGrade().equals("C")) {
						medianCourse.add(3);
					} else if (studentCourses.get(i).getGrade().equals("D")) {
						medianCourse.add(2);
					} else if (studentCourses.get(i).getGrade().equals("E")) {
						medianCourse.add(1);
					}
				}
				Collections.sort(medianCourse);	
				if (medianCourse.size() == 1) { //if only one course registered
					middle = medianCourse.get(0);
				}else if(medianCourse.size()%2 == 1) { //if odd numbers
					middle = (medianCourse.get((medianCourse.size()-1) /2));
				} else{ //if even number
					middle = (((double)medianCourse.get(medianCourse.size()/2)) + ((double)medianCourse.get(medianCourse.size()/2-1))) /2;
				}				
				stringMedian = String.valueOf(middle);		
				return stringMedian;
			}
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getStudentID() {
		return STUDENT_ID;
	}
	
	
	/*
	 * returns true if name is letters and white space, but not only white space
	 */
	
	private boolean isValidFirstName(String firstName) {
		return (firstName.matches("^[a-zA-ZæøåÆØÅ\s]*$") && !firstName.isBlank());
	}
	
	
	private boolean isValidLastName(String lastName) {
		return (lastName.matches("^[a-zA-ZæøåÆØÅ\s]*$") && !lastName.isBlank());
	}
	
	
	/*
	 * Returns true if studentID is a four digit number
	 */
	
	private boolean isValidStudentID(String studentID) {
		if ((!studentID.matches("^[0-9]*$") || studentID.length() != 4)) {
			return false; 
			}
		return true;
	}
	
	
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + STUDENT_ID;
	}
	
	
	/*
	 * Returns every courses in a string
	 */
	
	public String coursesString() {
		String string = "";
		for(int i = 0; i < studentCourses.size(); i++) {
			Course c = studentCourses.get(i);
			string += c.getCourseID() + ","+c.getSemester()+","+c.getYear()+","+c.getGrade() +",";
		}
		return string;
	}
	
	
	/*
	 * Returns full name, studentID and courses from that students courselist
	 * Formatted for FileManager
	 */
	
	public String toFullString() {
		return firstName + "," + lastName + "," + STUDENT_ID +",;"+coursesString();
	}
}

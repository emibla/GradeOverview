package Project;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.control.TableColumn;

/* 
 * KLasse som oppretter en student og sier hvilke attributter studenten skal ha, håndterer coursene til hver student
 */

// SPM TIL STUDASS: Kan man lage et student objekt hvis konstruktøren er private

public class Student {
	
	private String firstName;
	private String lastName;
	private final String studentID;
	
	//Arraylist med alle emner hver enkelt student har
	private ArrayList<Course> studentCourses;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param studentID
	 * @param studentCourses 
	 */
	public Student(String firstName, String lastName, String studentID) { 
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		//Lager en ArrayList studentCourses
		this.studentCourses = new ArrayList<Course>();	
	}
	
	//Legger til Course i studentCourses under Student
	public void addCourse(Course course) {
		this.studentCourses.add(course);
	}
	
	//henter ut course fra ArrayListen studentCourses
	public ArrayList<Course> getCourses(){
		return this.studentCourses;
	}

	public void removeCourse(String courseID) {
		Iterator<Course> iterator = studentCourses.iterator();
		while(iterator.hasNext()) {
			Course nCourse = iterator.next();
			if(nCourse.getCourseID().equals(courseID)) {
				iterator.remove();
				studentCourses.remove(nCourse);
				
			}
		}
	}
	
	/*
	 * Calculates the average of grades
	 */
	//TableColumn<Course, String> grade
	public String averageGrade (String studentID) {	
		
		double sum = 0;
		double num = 0;
		String avg = " hhh";
		double average = 0;
		//for (int s = 0; s < students.size(); s++) {
		
			//if(students.get(s).getStudentID().equals(studentID)){
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
				avg = String.valueOf(average);
		}
		return avg;
	}
	
	public Course findCourseByID(String courseID) {
		for(int i = 0; i < studentCourses.size(); i++) {
			if(getCourses().get(i).getCourseID().equals(courseID)) {
				return getCourses().get(i);
			}
		}
		return null;
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
	
	public String setFirstName() {
		return firstName;
	}
	
	public String setLastName() {
		return lastName;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + studentID;
	}
	
	
}

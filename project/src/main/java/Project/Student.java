package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
//import java.util.List;
//
//import javafx.scene.control.TableColumn;

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

	//Fjerner course fra ArrayListen, brukes for å kunne unngå duplikat av courses
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
	public void setAverage() {
		
		if (loggedIn.isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		}
		try {
		Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
		stud.averageGrade(loggedInStudentID.getText());
		averageLabel.setText("Ditt karaktersnitt er: " + stud.averageGrade(loggedInStudentID.getText()));
		} catch(IllegalStateException e){
			errorCalc.setText("Logg inn først!");
		}
	}
*/	
	
	
	
		
	/*
	 * Calculates the average of grades
	 */
	public String averageGrade (String studentID) {	
		
		double sum = 0;
		double num = 0;
		String avg = "";
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
	
	/*
	 * Calculates the median of grades
	 */
	 
	public String medianGrade (String studentID) {	
		ArrayList<Integer> medianCourse = new ArrayList<Integer>();
		double middle = 0;
		String stringMedian = ""; 
		//int num = 0;
			if (studentID == null) {
				throw new IllegalStateException("Logg inn først");
			} 
				for (int i = 0; i < studentCourses.size(); i++) {
					if (studentCourses.get(i).getGrade().equals("A")) {
						medianCourse.add(5);
						//num += 1;
					} else if (studentCourses.get(i).getGrade().equals("B")) {
						medianCourse.add(4);
						//num += 1;
					} else if (studentCourses.get(i).getGrade().equals("C")) {
						medianCourse.add(3);
						//num += 1;
					} else if (studentCourses.get(i).getGrade().equals("D")) {
						medianCourse.add(2);
						//num += 1;			s
					} else if (studentCourses.get(i).getGrade().equals("E")) {
						medianCourse.add(1);
						//num += 1;
				}
				Collections.sort(medianCourse);
				if (medianCourse.size()%2 == 1) {
					middle = (medianCourse.get(medianCourse.size()/2) + medianCourse.get((medianCourse.size()/2)-1) )/2;
				} else{
					middle = (medianCourse.get(medianCourse.size()/2));
				}
				
				stringMedian = String.valueOf(middle);
				
		}
		return stringMedian;
	}
	
	//må ha en nullsjekk hver gang brukes pga return null, se 
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
		return firstName + "," + lastName + "," + studentID;
	}
	
	public String coursesString() {
		String string = "";
		for(int i = 0; i < studentCourses.size(); i++) {
			Course c = studentCourses.get(i);
			string += c.getCourseID() + ","+c.getSemester()+","+c.getYear()+","+c.getGrade() +";";
		}
		return string;
	}
	
	public String toFullString() {
	
		
		return firstName + "," + lastName + "," + studentID +";"+ coursesString();
	}
	
	
}

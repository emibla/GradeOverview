package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements FileInterface{
	
	/*
	 * Reads from and writes to the school Register 
	 * Courses and Students
	 */

	private List<Student> allStudents = new ArrayList<Student>();
	//private List<Course> allCourses = new ArrayList<Course>();
	
	static StudentRegister testList = new StudentRegister();
	

	
	public void addStudent(Student student) {
		allStudents.add(student);
		}

	public void writeStudentsInRegister(String studentRegister) {
		
		try {
			PrintWriter writer = new PrintWriter(studentRegister);		
			for (Student student: allStudents) {
			writer.println(student.toFullString());
		}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("tjohei"+e.getMessage());
		}
	}

		
		public void getStudentsFromRegister(String studentRegister) throws FileNotFoundException {
			Scanner scanner = new Scanner(new File(studentRegister));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineInfo = line.split(",");
				String[] splitInfo = line.split(";");
				//System.out.println(splitInfo[1]);
				//splitter course på ,
				String[] courseLineInfo = splitInfo[1].split(",");
				//System.out.println(line);
				//ArrayList courseItems = new ArrayList();
				//courseItems.add(courseLineInfo);
				String firstName = lineInfo[0];
				String lastName = lineInfo[1];
				String studentID = lineInfo[2];
				Student student = new Student(firstName, lastName, studentID);
				System.out.println("");
				for(int i =0; i< courseLineInfo.length; i++) {
					System.out.print(courseLineInfo[i]);
				}
				System.out.println("");
				if(courseLineInfo.length > 0) {
					for(int i = 0; i < courseLineInfo.length; i++) {
							student.addCourse(new Course(courseLineInfo[0], courseLineInfo[1], Integer.parseInt(courseLineInfo[2]),courseLineInfo[3]) );
					
					}	
						
				}
				
				//System.out.println(student);
				this.addStudent(student);
			}
			scanner.close();
			}
		


		public void writeStudentRegister(String filename) {
			
			try {
				PrintWriter writer = new PrintWriter(filename);		
				for (int i = 0; i < testList.getStudents().size(); i++) {
					Student s = testList.getStudents().get(i);
				   writer.println(s.toFullString());
			}
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.println("tjohei"+e.getMessage());
			}
		}

		
	
	
	public static void main(String[] args) {
		testList.registerNewStudent("Emily", "Blakseth", "6786");
		testList.registerNewStudent("Espen", "Askeladd", "8282");
		Student stud = testList.findStudentByID("8282");
		stud.addCourse(new Course("TDT4100", "Vår", 2021, "A"));
		stud.addCourse(new Course("Matte","Høst",2020,"B"));
		Student stud2 = testList.findStudentByID("6786");
		stud2.addCourse(new Course("Matte","Høst",2020,"B"));
		
		
		FileManager manager = new FileManager();
		manager.addStudent(new Student("Lorraine", "Oplenskedal", "0202"));
		manager.addStudent(new Student("Roald", "Blakseth", "2209"));
		
		manager.writeStudentRegister("EmilysFil.txt");
		try{
		manager.getStudentsFromRegister("EmilysFil.txt");	
		}catch(FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
		
		//manager.writeStudentsInRegister("studentRegister.txt");
		//try {
		//	manager.getStudentsFromRegister("studentRegister.txt");
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	System.out.println("Filnavnet finnes ikke");
		//}
		//manager.writeStudentsInRegister("studentRegister.txt");

		
	}
	
	
	
	
	
	
	
	
	
}

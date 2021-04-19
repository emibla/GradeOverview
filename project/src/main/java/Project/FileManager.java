package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FileManager implements FileInterface{
	
	/*
	 * Reads from and writes to the school Register 
	 * Courses and Students
	 */

//	private List<Student> allStudents = new ArrayList<Student>();
//	//private List<Course> allCourses = new ArrayList<Course>();
//	
//	static StudentRegister testList = new StudentRegister();
//	
//	static StudentRegister afterList = new StudentRegister();
//	
//
//	
//	public void addStudent(Student student) {
//		allStudents.add(student);
//		}

	
	/*
	public void writeStudentsInRegister(String filename) {
		
		try {
			PrintWriter writer = new PrintWriter(filename+".txt");		
			for (Student student: allStudents) {
			writer.println(student.toFullString());
		}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("tjohei"+e.getMessage());
		}
	}
*/
		
		public void readStudentsFromFile(StudentRegister studentregister, String filename) throws FileNotFoundException {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineInfo = line.split(",");
				String[] splitInfo = line.split(";");
				//System.out.println(splitInfo[1]);
				//splitter course på ,
				//System.out.println(line);
				//ArrayList courseItems = new ArrayList();
				//courseItems.add(courseLineInfo);
				String firstName = lineInfo[0];
				String lastName = lineInfo[1];
				String studentID = lineInfo[2];
				//Student student = new Student(firstName, lastName, studentID);
				//System.out.println("");
				//afterList.registerNewStudent(firstName, lastName, studentID);   ***** DENNE
				studentregister.registerNewStudent(firstName, lastName, studentID);
				if(splitInfo.length > 1) {
					String[] courseLineInfo = splitInfo[1].split(",");
					for(int i =0; i< courseLineInfo.length;) {
					//System.out.println(studentID);  ******* OG DEN ØANGE UNDER
					//afterList.findStudentByID(studentID).addCourse(new Course(courseLineInfo[i], courseLineInfo[i+1],Integer.parseInt(courseLineInfo[i+2]) , courseLineInfo[i+3]));
					//System.out.println(courseLineInfo[i]);
						studentregister.findStudentByID(studentID).addCourse(new Course(courseLineInfo[i], courseLineInfo[i+1],Integer.parseInt(courseLineInfo[i+2]) , courseLineInfo[i+3]));
						i +=4;
			}}
			
			
		//	for(int i = 0; i < afterList.students.size(); i++) {
		//		System.out.println("heipådei"+afterList.getStudents().get(i).toFullString());
				
			//}
			} scanner.close();
		}
		


		public void writeStudentRegister(StudentRegister studentRegister, String filename) {
			
			try {
				PrintWriter writer = new PrintWriter(filename);		
				for (int i = 0; i < studentRegister.getStudents().size(); i++) {
					Student s = studentRegister.getStudents().get(i);
				   writer.println(s.toFullString());
			}
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}

		
	
}
		/**
		 * 
		 * @param args
		
		public static void main(String[] args) {
		//testList.registerNewStudent("Emily", "Blakseth", "6786");
		testList.registerNewStudent("Espen", "Askeladd", "8282");
		Student stud = testList.findStudentByID("8282");
		stud.addCourse(new Course("TDT4100", "Vår", 2021, "A"));
		stud.addCourse(new Course("Matte","Høst",2020,"B"));
		stud.addCourse(new Course("imatt1002", "vår", 2021, "C"));
		stud.addCourse(new Course("TFOR1002", "vår", 2021, "D"));
		//Student stud2 = testList.findStudentByID("6786");
		//stud2.addCourse(new Course("Matte","Høst",2020,"B"));
		
		
		FileManager manager = new FileManager();
		//manager.addStudent(new Student("Lorraine", "Oplenskedal", "0202"));
		//manager.addStudent(new Student("Roald", "Blakseth", "2209"));
		
		manager.writeStudentRegister("Registerfil");
		try{
		manager.readStudentsFromFile("Registerfil");	
		}catch(FileNotFoundException f) {
			System.out.println(f.getMessage());
		}
	}
}


 */
		//manager.writeStudentsInRegister("studentRegister.txt");
		//try {
		//	manager.getStudentsFromRegister("studentRegister.txt");
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	System.out.println("Filnavnet finnes ikke");
		//}
		//manager.writeStudentsInRegister("studentRegister.txt");

		

	
	/**
	 * 
	
	System.out.println(courseLineInfo[i]);
	System.out.println(courseLineInfo[i+1]);
	System.out.println(courseLineInfo[i+2]);
	System.out.println(courseLineInfo[i+3]);
	System.out.println(courseLineInfo[i+6]);
	
	int nr = 1;
	String courseID = courseLineInfo[nr];
	String sem = courseLineInfo[nr+1];
	int year = Integer.parseInt(courseLineInfo[nr+2]);
	String grade = courseLineInfo[nr+3];
	//System.out.print(courseLineInfo[i]);
	Course course = new Course(courseID,sem,year,grade);
	afterList.findStudentByID(studentID).addCourse(course);
	 */

//System.out.println("");
//if(courseLineInfo.length > 0) {
//	for(int i = 0; i < courseLineInfo.length; i++) {
//			student.addCourse(new Course(courseLineInfo[0], courseLineInfo[1], Integer.parseInt(courseLineInfo[2]),courseLineInfo[3]) );
		
//	}			
//}
//System.out.println(student);
//this.addStudent(student);
	
	
	
	


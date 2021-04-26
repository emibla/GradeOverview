package Project;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager implements FileInterface{
	
	
	/*
	 * Reads students from file
	 * Adds students to studentRegister
	 * Split student from courses by ;
	 */
		
		public void readStudentsFromFile(StudentRegister studentregister, String filename) throws FileNotFoundException {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineInfo = line.split(",");
				String[] splitInfo = line.split(";");
				String firstName = lineInfo[0];
				String lastName = lineInfo[1];
				String studentID = lineInfo[2];
				studentregister.registerNewStudent(firstName, lastName, studentID);
				if(splitInfo.length > 1) { // if student has any courses
					String[] courseLineInfo = splitInfo[1].split(","); // split course values by ,
					for(int i =0; i< courseLineInfo.length;) {
						studentregister.findStudentByID(studentID).addCourse(courseLineInfo[i], courseLineInfo[i+1],Integer.parseInt(courseLineInfo[i+2]) , courseLineInfo[i+3]);
						i +=4; // add a new course after every 4 values
			}}
			} scanner.close();
		}
		
		
		
		/*
		 * Creates a new file if filename does not exist
		 * Writes info from studentRegister into file
		 */
		
		public void writeStudentRegister(StudentRegister studentRegister, String filename)  {
			
			try {
				PrintWriter writer = new PrintWriter(filename);		
				for (int i = 0; i < studentRegister.getStudents().size(); i++) {
					Student s = studentRegister.getStudents().get(i);
				   writer.println(s.toFullString());
			}
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
			}
		}

		
	
}
	
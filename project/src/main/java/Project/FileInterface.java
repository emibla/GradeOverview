package Project;

import java.io.FileNotFoundException;

public interface FileInterface {
	
	public void writeStudentRegister(StudentRegister studentRegister, String filename);
	
	public void readStudentsFromFile(StudentRegister studentregister, String filename) throws FileNotFoundException;
	
}

package Project;

import java.io.FileNotFoundException;

public interface FileInterface {
	
	public void writeStudentsInRegister(String studentRegister);
	
	public void getStudentsFromRegister(String studentRegister) throws FileNotFoundException;
	
}

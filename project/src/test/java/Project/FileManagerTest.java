package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileManagerTest {
	
	FileManager fileManager = new FileManager();
	StudentRegister studentRegister = new StudentRegister();
	
	@BeforeEach
	public void setup() {
		studentRegister.registerNewStudent( "Ronald", "Wiltersen", "1100");
		studentRegister.findStudentByID("1100").addCourse("TDT4100", "Vår", 2021, "C");
		fileManager.writeStudentRegister(studentRegister, "testFileWithStudents");
	}
	
	
	@Test
	public void testReadEmptyFile() throws FileNotFoundException {
		StudentRegister emptyRegister = new StudentRegister();
		fileManager.writeStudentRegister(emptyRegister, "testEmptyFile");
		fileManager.readStudentsFromFile(studentRegister, "testEmptyFile");
		assertEquals(1, studentRegister.getStudents().size());
	}
	
	@Test
	public void testFileNotFound() {
		assertThrows(FileNotFoundException.class,
				() -> {fileManager.readStudentsFromFile(studentRegister, "notFoundFile");} );
	}
	
	@Test
	public void testReadStudentFromFile() throws IOException {
		StudentRegister testRegister = new StudentRegister();
		fileManager.readStudentsFromFile(testRegister, "testFileWithStudents");
		assertEquals("Ronald", testRegister.findStudentByID("1100").getFirstName());
		assertEquals("C", testRegister.findStudentByID("1100").findCourseByID("TDT4100").getGrade());
	}
	
	@Test
	public void testStudentAlreadyInRegister() {
		assertThrows(IllegalArgumentException.class,
				() -> {fileManager.readStudentsFromFile(studentRegister, "testFileWithStudents");});
	}


}

package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ControllerTest {


	StudentRegister studentRegister = new StudentRegister();
	

	public void fillWithTestData() {
	studentRegister.registerNewStudent("Emily", "Test", "1234");
	studentRegister.registerNewStudent("Mari", "Test", "2345");
	studentRegister.registerNewStudent("Kine", "Test", "3456");
	studentRegister.registerNewCourse("TDT4100", "Høst", "2019", "A");
	studentRegister.registerNewCourse("TMA4100", "Vår", "2020", "C");
	studentRegister.registerNewCourse("TDT4245", "Høst", "2020", "B");
	//Henter ut data fra course ArrayList
	}
	
	
	
	
	/**
	 * Positiv test tester at student blir lagt til i registeret
	 */
	@Test
	void registerNewStudentTest() {
		try {
			studentRegister.registerNewStudent("Donald", "trump","666");
		}catch(Exception e) {
		System.out.println(e.getMessage());
		}
		assertEquals(1, studentRegister.getStudents().size());
	}
	
	
	/**
	 * negativ test
	 * tester feilhåndtering av null
	 */
	@Test
	void registerNewStudentNullTest(){
		try {
			studentRegister.registerNewStudent("rudolf", "blodstrupmoen", null);
		}catch(NullPointerException n) {
			System.out.println(n.getMessage());
		}
		assertEquals(0, studentRegister.getStudents().size());
	}
	
	
	
	
}

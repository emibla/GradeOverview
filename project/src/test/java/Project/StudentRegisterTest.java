package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentRegisterTest {
	
	StudentRegister studentRegister = new StudentRegister();
	
	@BeforeEach
	public void setup() {
		studentRegister.registerNewStudent("Mikkel", "Rev", "1234");
		studentRegister.registerNewStudent("Donald", "Trump", "1111");
	}

	
	/**
	 * Positive test 
	 * testing successfully registering students
	 */
	@Test
	public void testRegisterNewStudent() {
		//StudentRegister studentRegister = new StudentRegister();
		studentRegister.registerNewStudent("Lilly", "Pop", "1000");
		assertEquals(3, studentRegister.getStudents().size());
	}
	
	@Test
	public void testRegisterNewStudentBlank() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent("Nicholas", "Flamel", "");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent("", "Flamel", "2020") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent("Nicholas", "", "2020") ;} );
	}
	@Test
	public void testRegisterInvalidFirstName() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry2", "Potter", "1991") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "2222", "Potter", "1991") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry&Jacob", "Potter", "1991") ;} );
	}
	
	@Test
	public void testRegisterInvalidLastName() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Potter2", "1991") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "22222", "1991") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Pott&&", "1991") ;} );
	}
	
	@Test
	public void testRegisterInvalidStudentID() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Potter", "wwww") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Potter", "123") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Potter", "12345") ;} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent( "Harry", "Potter", "12&&") ;} );
	}
	
	@Test
	public void testRegisterStudentAlreadyExist() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.registerNewStudent("Snømannen", "Kalle", "1234");} );
	}
	
	
	@Test
	public void testFindStudentByID() {
		studentRegister.registerNewStudent("Hermine", "Grang", "1122");
		assertEquals("Hermine", studentRegister.findStudentByID("1122").getFirstName());
	}
	
	@Test
	public void testFindStudentByIDNull() {
		studentRegister.registerNewStudent("Hermine", "Grang", "1122");
		assertNull(studentRegister.findStudentByID(null));
	}
	
	@Test
	public void testStudentByIDNotfound() {
		studentRegister.registerNewStudent("Hermine", "Grang", "1122");
		assertNull(studentRegister.findStudentByID("0000"));
	}
	
	

}

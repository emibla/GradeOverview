package Project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class StudentTest {

	StudentRegister studentRegister = new StudentRegister();
	
	@BeforeEach
	public void setUp() {
		studentRegister.registerNewStudent("Hermine", "Grang", "0001");
		studentRegister.registerNewStudent("Ronald", "Wiltersen", "1100");
		studentRegister.registerNewStudent("Harry", "Potter", "3107");
		studentRegister.findStudentByID("0001").addCourse("TDT4100", "Vår", 2021, "A");
		studentRegister.findStudentByID("0001").addCourse("TMA4100", "Høst", 2020, "B");
		studentRegister.findStudentByID("1100").addCourse("TDT4100", "Vår", 2021, "B");
		studentRegister.findStudentByID("1100").addCourse("TMA4140", "Høst", 2020, "A");
		studentRegister.findStudentByID("1100").addCourse("TMA4245", "Høst", 2020, "D");
	}
	
	
	@Test
	public void testGetStudentID() {
		assertEquals("0001", studentRegister.findStudentByID("0001").getStudentID());
	}
	
	@Test
	public void testGetStudentFirstName() {
		assertEquals("Hermine", studentRegister.findStudentByID("0001").getFirstName());
	}
	
	@Test
	public void testGetStudentLastName() {
		assertEquals("Grang", studentRegister.findStudentByID("0001").getLastName());
	}
	

	
	@Test
	public void testAddCourse () {
		studentRegister.findStudentByID("1100").addCourse("ITGK", "Høst", 2020, "A");
		assertEquals("ITGK", studentRegister.findStudentByID("1100").findCourseByID("ITGK").getCourseID());
		studentRegister.findStudentByID("3107").addCourse("TMA4100", "vår", 2020, "A");
		assertEquals("vår", studentRegister.findStudentByID("3107").findCourseByID("TMA4100").getSemester());
	}
	

	
	@Test
	public void testAddCourseBlank() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("", "Vår", 2021, "B");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("TDT4245", "", 2021, "C");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("TDT4245", "Vår", 2021,"");} );
	}
	

	
	@Test
	public void testAddCourseInvalidInput() {
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("0TDT4245", "Vår", 2021, "B");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("TDT4245", "Vinter", 2021, "C");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("TDT4245", "Vår", 2500,"");} );
		assertThrows(IllegalArgumentException.class,
				() -> {studentRegister.findStudentByID("0001").addCourse("TDT4245", "Vår", 2020,"J");} );
	}
	

	
	@Test
	public void testFindCourseByID() {
		assertEquals("CourseID: TDT4100 Semester: Vår Year: 2021 Grade: B", studentRegister.findStudentByID("1100").findCourseByID("TDT4100").toString());
	}

	
	@Test
	public void testRemoveCourse() {
		studentRegister.findStudentByID("1100").removeCourse("TDT4100");
		assertNull(studentRegister.findStudentByID("1100").findCourseByID("TDT4100"));
	}
	

	@Test
	public void testAverageGrade() {
		assertEquals("3,67", studentRegister.findStudentByID("1100").averageGrade("1100"));
	}
	

	@Test
	public void testAverageGradeNotLoggedIn() {
		assertThrows(IllegalStateException.class,
				() -> {studentRegister.findStudentByID("3107").averageGrade(null);});
	}
	
	@Test
	public void testAverageGradeNull() {
		assertThrows(IllegalStateException.class,
				() -> {studentRegister.findStudentByID("3107").averageGrade("3107");});
	}
	
	@Test
	public void testMedianGradeEvenNumber() {
		assertEquals("4.5",studentRegister.findStudentByID("0001").medianGrade("0001"));
	}
	
	@Test
	public void testMedianGradeOddNumber() {
		assertEquals("4.0", studentRegister.findStudentByID("1100").medianGrade("1100"));
	}
	
	@Test
	public void testMedianGradeNotLoggedIn() {
		assertThrows(IllegalStateException.class,
				() -> {studentRegister.findStudentByID("3107").medianGrade(null);});
	}
	
	@Test
	public void testMedianGradeNull() {
		assertThrows(IllegalStateException.class,
				() -> {studentRegister.findStudentByID("3107").medianGrade("3107");});
	}
	
	@Test
	public void testGradeAsInt() {
		assertEquals(5, Student.gradeAsInt("A"));
		assertEquals(3, Student.gradeAsInt("C"));
		assertEquals(1, Student.gradeAsInt("E"));
	}

	@Test
	public void testCoursesString() {
		assertEquals("TDT4100,Vår,2021,A,TMA4100,Høst,2020,B,", studentRegister.findStudentByID("0001").coursesString());
	}

	@Test
	public void testToFullString() {
		assertEquals("Hermine,Grang,0001,;TDT4100,Vår,2021,A,TMA4100,Høst,2020,B,", studentRegister.findStudentByID("0001").toFullString());
		
	}
}

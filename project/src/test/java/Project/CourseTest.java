package Project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CourseTest {
	
	@Test
	public void testCourseValidInput() {
		Course course = new Course("TDT4100", "Vår", 2021, "A");
		Course course2 = new Course("tma4100", "høst", 2020, "B");
		Course course3 = new Course("databaser", "vår", 2021, "C");
		assertEquals("TDT4100", course.getCourseID());
		assertEquals("Vår", course.getSemester());
		assertEquals(2021, course.getYear());
		assertEquals("A", course.getGrade());
		assertEquals("tma4100", course2.getCourseID());
		assertEquals("høst", course2.getSemester());
		assertEquals(2020, course2.getYear());
		assertEquals("B", course2.getGrade());
		assertEquals("databaser", course3.getCourseID());
		assertEquals("vår", course3.getSemester());
		assertEquals(2021, course3.getYear());
		assertEquals("C", course3.getGrade());
	}
	
	@Test
	public void testAddCourseInvalidInput() {
		assertThrows(IllegalArgumentException.class,
				() -> {new Course("4100", "Vår", 2021, "A");} );
		assertThrows(IllegalArgumentException.class,
				() -> {new Course("TDT4100", "VINTER", 2021, "A");} );
		assertThrows(IllegalArgumentException.class,
				() -> {new Course("TDT4100", "Vår", 1900, "A");} );
		assertThrows(IllegalArgumentException.class,
				() -> {new Course("TDT4100", "Vår", 2022, "A");} );
	}
	
	
}

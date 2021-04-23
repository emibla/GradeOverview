package Project;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CourseTest {
	
	@Test
	public void testCourse() {
		Course course = new Course("TDT4100", "Vår", 2021, "A");
		assertEquals("TDT4100", course.getCourseID());
		assertEquals("Vår", course.getSemester());
		assertEquals(2021, course.getYear());
		assertEquals("A", course.getGrade());
	}
}

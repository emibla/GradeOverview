package Project;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author eblakseth Controllerclass for App GradeOverview
 * 
 */
public class Controller {

	/**
	 * Connecting FXML components to Controller class
	 */

	// Register new Student
	@FXML
	private TextField firstName, lastName, studentID;
	@FXML
	private Button registerStudentButton;

	// Search for Student
	@FXML
	private TextField searchStudentID;
	@FXML
	private Button searchStudentButton;

	// Add course
	@FXML
	private TextField registerCourseID, courseYear;
	@FXML
	private ToggleButton gradeA, gradeB, gradeC, gradeD, gradeE, semesterAutumn, semesterSpring;
	@FXML
	private ToggleGroup semesterToggle, gradeToggle;
	@FXML
	private Button registerCourseButton;

	// Error labels
	@FXML
	private Label errorRegisterStudent, errorSearchStudent, errorRegisterCourse, errorCalc;

	// Logged in Labels
	@FXML
	private Label loggedInStudentName, loggedInStudentID;

	// TableView
	@FXML
	private TableView<Course> courseTable;
	@FXML
	private TableColumn<Course, String> courseID, grade, semester, year;

	// Calculating
	@FXML
	private Label averageLabel, medianLabel;

	/*
	 * initializing a new StudentRegister
	 */
	StudentRegister studentRegister = new StudentRegister();
	
	/*
	 * Initializing Filemanager
	 */

	FileManager fileManager = new FileManager();

	@FXML
	private void initialize() {
		
		// fillWithTestData(); //method to add test data to studentRegister
		

		// Fill table with columns
		TableColumn<Course, String> courseIDcol = new TableColumn<>("Emnekode");
		courseIDcol.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		courseTable.getColumns().add(courseIDcol);

		TableColumn<Course, String> gradeCol = new TableColumn<>("Karakter");
		gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
		courseTable.getColumns().add(gradeCol);

		TableColumn<Course, String> semesterCol = new TableColumn<>("Semester");
		semesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
		courseTable.getColumns().add(semesterCol);

		TableColumn<Course, String> yearCol = new TableColumn<>("År");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
		courseTable.getColumns().add(yearCol);

		
		// reads from StudentRegister file
		try {
			fileManager.readStudentsFromFile(studentRegister, "StudentRegister");

		} catch (FileNotFoundException f) {
			errorCalc.setText("Klarte ikke å lese fra fil");
		}

	} 
	
	/*
	 * Method for updating StudentRegister file
	 */

	private void updateFile() {
		try {
			fileManager.writeStudentRegister(studentRegister, "StudentRegister");
		} catch (Exception e) {
			errorCalc.setText("Det var et problem med å skrive til fil");
		}
	}
	

	/*
	 * Checks if valid info is inserted to Textfields and adds student to studentRegister 
	 * Catch IllegalArgumentException if unable to register
	 */

	@FXML
	public void addStudent() {
		clearLabels();
		Student stud = null;

		stud = studentRegister.findStudentByID(studentID.getText());

		if (ifAnyFieldRegisterStudentEmpty()) {
			errorRegisterStudent.setText("Fyll alle felter");
		} else if (stud != null) {
			errorRegisterStudent.setText("StudentID er allerede registrert,\nvennligst logg inn");
		} else {

			try {
				studentRegister.registerNewStudent(firstName.getText(), lastName.getText(), studentID.getText());
				errorRegisterStudent.setText("Registrert! Logg inn for å registrere emner");
				updateFile();
			} catch (IllegalArgumentException e) {
				errorRegisterStudent.setText(e.getMessage());
			}
		}
	}
	

	/*
	 * Search for student in students list
	 */
	
	@FXML
	public void searchStudent() {
		clearLabels();
		loggedInStudentID.setText("");
		loggedInStudentName.setText("");
		

		if (ifSearchFieldEmpty()) {
			errorSearchStudent.setText("Tast inn din Student ID");
		} else if (!isValidStudentID()) {
			errorSearchStudent.setText("Student ID skal bestå av 4 siffer");
		} else {

			courseTable.getItems().clear(); //clear table
			String studentID = searchStudentID.getText();
			Student stud = studentRegister.findStudentByID(studentID);
			if (stud == null) {
				errorSearchStudent.setText("StudentID ble ikke funnet,\nvennligst registrer deg");
			} else {
				courseTable.getItems().addAll(stud.getCourses());
				loggedInStudentID.setText(searchStudentID.getText());
				loggedInStudentName.setText(stud.getFirstName() + " " + stud.getLastName());
			}
		}
	}
	
	
	/*
	 * Collects info from TextFields and Togglebuttons and uses it to add a new course
	 * If adding a new course that already exist, checks if new grade is higher than already registered, if so replace old course
	 * Catches IllegalArguemntException if unable to add Course and if so print message to label
	 */

	@FXML
	public void addCourse() {
		clearLabels();

		if (loggedInStudentID.getText().isEmpty()) {
			errorRegisterCourse.setText("Logg inn først");
		} else if (ifAnyFieldEmpty()) {
			errorRegisterCourse.setText("Fyll alle felter");
		} else {

			String studentID = loggedInStudentID.getText();
			Student stud = studentRegister.findStudentByID(studentID);
			String cId = registerCourseID.getText();
			String newGrade = getGrade();
			int oldGradeAsInt = 0;
			int newGradeAsInt = Student.gradeAsInt(newGrade);

			if (stud.findCourseByID(cId) == null) { // checks if course is already registered
				oldGradeAsInt = 0;
			} else { //if so, set integer value to old grade
				oldGradeAsInt = Student.gradeAsInt(studentRegister.findStudentByID(studentID).findCourseByID(cId).getGrade());
			}

			if (stud.findCourseByID(cId) != null && (newGradeAsInt > oldGradeAsInt)) { //if course exist and new grade value is higher than old value
				stud.removeCourse(registerCourseID.getText()); //remove old course
				try {
					stud.addCourse(registerCourseID.getText(), getSemester(), Integer.parseInt(courseYear.getText()),getGrade());
				} catch (IllegalArgumentException e) {
					errorRegisterCourse.setText(e.getMessage());

				}
			} else if (stud.findCourseByID(cId) != null && (newGradeAsInt <= oldGradeAsInt)) { //if course exist and new grade is similar or lower
				errorRegisterCourse.setText("Du har bedre karakter i dette emnet fra før"); 
			} else {
				try {
					stud.addCourse(registerCourseID.getText(), getSemester(), Integer.parseInt(courseYear.getText()),getGrade());
				} catch (IllegalArgumentException e) {
					errorRegisterCourse.setText(e.getMessage());
				}
			}

			courseTable.getItems().clear(); //clear old table
			courseTable.getItems().addAll(stud.getCourses());
			updateFile();
		}
	}
	

	@FXML
	public void average() {
		clearLabels();

		if (loggedInStudentID.getText().isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		}
		try {
			Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
			stud.averageGrade(loggedInStudentID.getText());
			averageLabel.setText("Ditt karaktersnitt er: " + stud.averageGrade(loggedInStudentID.getText()));
		} catch (IllegalStateException e) {
			errorCalc.setText(e.getMessage());
		}
	}
	

	@FXML
	public void median() {
		clearLabels();

		if (loggedInStudentID.getText().isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		}

		try {
			Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
			medianLabel.setText("Medianen til ditt karaktersnitt er: " + stud.medianGrade(loggedInStudentID.getText()));
		} catch (IllegalStateException e) {
			errorCalc.setText(e.getMessage());
		}
	}
	

	/*
	 * Collects the semester from ToggleButton
	 */

	@FXML
	private String getSemester() {
		String sem = "";

		Toggle semesterTogger = semesterToggle.getSelectedToggle();

		if (semesterTogger == semesterAutumn) {
			sem = "Høst";
		} else if (semesterTogger == semesterSpring) {
			sem = "Vår";
		}
		return sem;
	}

	/*
	 * Collects the grade from ToggleButton
	 */

	@FXML
	private String getGrade() {

		String grad = "";

		Toggle gradeTogger = gradeToggle.getSelectedToggle();

		if (gradeTogger == gradeA) {
			grad = "A";
		} else if (gradeTogger == gradeB) {
			grad = "B";
		} else if (gradeTogger == gradeC) {
			grad = "C";
		} else if (gradeTogger == gradeD) {
			grad = "D";
		} else if (gradeTogger == gradeE) {
			grad = "E";
		}
		return grad;
	}


	private void clearLabels() {
		errorRegisterCourse.setText("");
		errorSearchStudent.setText("");
		errorRegisterStudent.setText("");
		errorCalc.setText("");
		averageLabel.setText("");
		medianLabel.setText("");
	}

	/*
	 * Returns true if any Textfields of registering a student is empty
	 */

	private boolean ifAnyFieldRegisterStudentEmpty() {
		return firstName.getText().isEmpty() || lastName.getText().isEmpty() || studentID.getText().isEmpty();
	}

	/*
	 * Returns true if Textfield searchStudentID is empty
	 */

	private boolean ifSearchFieldEmpty() {
		return searchStudentID.getText().isEmpty();
	}

	/*
	 * Returns true if Textfield registerCourseID is empty, or either of
	 * ToggleButtons is not pressed
	 */

	private boolean ifAnyFieldEmpty() {
		return registerCourseID.getText().isEmpty() || courseYear.getText().isEmpty()
				|| (!semesterAutumn.isSelected() && !semesterSpring.isSelected())
				|| (!gradeA.isSelected() && !gradeB.isSelected() && !gradeC.isSelected() && !gradeD.isSelected()
						&& !gradeE.isSelected());
	}

	/*
	 * Returns true if studentID is a number of 4 digits
	 */

	private boolean isValidStudentID() {
		if ((!searchStudentID.getText().matches("^[0-9]*$")) || searchStudentID.getText().length() != 4) {
			return false;
		}
		return true;
	}
	
	
	/*
	 * Method to fill register with testdata
	 */

	/*
	private void fillWithTestData() {
		studentRegister.registerNewStudent("Emily", "Test", "1234");
		studentRegister.registerNewStudent("Mari", "Test", "2345");
		studentRegister.registerNewStudent("Kine", "Test", "1234");
		 studentRegister.registerNewStudent("Bokstav", "Test", "wwww");
		 studentRegister.findStudentByID("1234").addCourse("TDT4245", "Vår", 2020, "B");
	}
	*/
}
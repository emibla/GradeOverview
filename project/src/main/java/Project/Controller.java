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
 * @author eblakseth
 * Controllerclass for App GradeOverview
 * 
 */
public class Controller{

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

	/**
	 * initializing a new StudentRegister
	 */
	StudentRegister studentRegister = new StudentRegister();
	
	FileManager fileManager = new FileManager();
	

	/**
	 * Method to fill register with testdata
	 */
	
	public void fillWithTestData() {
	studentRegister.registerNewStudent("Emily", "Test", "1234");
	studentRegister.registerNewStudent("Mari", "Test", "2345");
	studentRegister.registerNewStudent("Kine", "Test", "1234");
	//studentRegister.registerNewStudent("Bokstav", "Test", "wwww");
	//studentRegister.findStudentByID("1234").addCourse("TDT4245", "Vår", 2020, "B");

	}
	
	@FXML
	private void initialize() {
		
		//fillWithTestData();
		
			// oppretter kolonner i tableView
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
		
			try {
				fileManager.readStudentsFromFile(studentRegister, "StudentRegister");
				
			}catch (FileNotFoundException f){
				errorCalc.setText("Klarte ikke å lese fra fil");
	}

	}	//initialize end
	
	private void updateFile() {
		try {
			fileManager.writeStudentRegister(studentRegister, "StudentRegister");
		} catch (Exception e) {
			errorCalc.setText("Det var et problem med å skrive til fil");
		}
	}
	
	private void loggedInStudentName() {
		try {
		Student stud = studentRegister.findStudentByID(searchStudentID.getText());
		loggedInStudentName.setText(stud.getFirstName()+ " " + stud.getLastName());
		} catch (NullPointerException e){
		}
		
	}
	
	@FXML
	private void loggedInStudentID() {
		loggedInStudentID.setText(searchStudentID.getText());
	}
	
	/*
	 * Checks if valid info is inserted in Textfields and adds student to studentRegister
	 */
	
	@FXML
	public void addStudent() {
		clearLabels();
		Student stud = null;
		
		try {
		stud = studentRegister.findStudentByID(studentID.getText());
		} catch(NullPointerException e) {			
		}
		
		if(ifAnyFieldRegisterStudentEmpty()) {
			errorRegisterStudent.setText("Fyll alle felter");
		}else if(!isValidFirstName()) {
			errorRegisterStudent.setText("Skriv inn et gyldig fornavn, kun bokstaver");
		}else if(!isValidLastName()) {
			errorRegisterStudent.setText("Skriv inn et gyldig etternavn, kun bokstaver");
		}else if(!isValidSearchStudentID()) {
			errorRegisterStudent.setText("Student ID skal bestå av 4 siffer");
		}else if(stud != null) {
				errorRegisterStudent.setText("StudentID er allerede registrert,\nvennligst logg inn");
		}else {
		
		try {	
		studentRegister.registerNewStudent(firstName.getText(), lastName.getText(), studentID.getText());
		errorRegisterStudent.setText("Registrert! Logg inn for å registrere emner");
		updateFile();
		} catch(IllegalArgumentException e){ // | NullPointerException 
			errorRegisterStudent.setText(e.getMessage());
		}}		
}	
	
	/*
	 * Search for student in students arraylist
	 */
	@FXML
	public void searchStudent() {
		clearLabels();
		
		if(ifSearchFieldEmpty()) {
			errorSearchStudent.setText("Tast inn din Student ID");
		}
		else if(!isValidStudentID()) {
			errorSearchStudent.setText("Student ID skal bestå av 4 siffer");
		} else {
		
		courseTable.getItems().clear();
		String studentID = searchStudentID.getText();
		Student stud = studentRegister.findStudentByID(studentID);
		if(stud  == null) {
			errorSearchStudent.setText("StudentID ble ikke funnet,\nvennligst registrer deg");
		} else{
			//stud = studentRegister.findStudentByID(studentID);
			//Oppdaterer tabellen og getter alle course som ligger i studentRegister på nytt
			courseTable.getItems().addAll(stud.getCourses());
			loggedInStudentID();
			loggedInStudentName();
		}
		}
	}
	
	//Metode for å hente ut informasjon fra registrering av course
	@FXML
	public void addCourse() {
		clearLabels();
		
		if(loggedInStudentID.getText().isEmpty()) {
			errorRegisterCourse.setText("Logg inn først");
		}else if(ifAnyFieldEmpty()){
			errorRegisterCourse.setText("Fyll alle felter");
		} else if(!isValidCourseID()) {
			errorRegisterCourse.setText("Fyll inn emnenavn med bokstaver\nførst og tall etterpå");
		} else if(!isValidYear()) {
			errorRegisterCourse.setText("Tast inn gyldig år");
		} else {

		String studentID = loggedInStudentID.getText();
		Student stud = studentRegister.findStudentByID(studentID);
		String cId = registerCourseID.getText();
		String newGrade = getGrade();
		int oldGradeAsInt =0;
		int newGradeAsInt = Student.gradeAsInt(newGrade);
		
		if( stud.findCourseByID(cId) == null){
			oldGradeAsInt = 0;
		} else {
			oldGradeAsInt = Student.gradeAsInt(studentRegister.findStudentByID(studentID).findCourseByID(cId).getGrade());
		}
		
		//Sjekker om courseID er lagt til fra før. Hvis karakteren har en høyere verdi, erstattes den
		if(stud.findCourseByID(cId) != null && (newGradeAsInt > oldGradeAsInt)) {
				stud.removeCourse(registerCourseID.getText());
					stud.addCourse(registerCourseID.getText(), getSemester(), Integer.parseInt(courseYear.getText()), getGrade());
					courseTable.getItems().clear(); //Fjerner hele tabellen for å fjerne den gamle karakterern
					courseTable.getItems().addAll(stud.getCourses()); //Laster inn alle karakterene på nytt i tabellen
		} else if(stud.findCourseByID(cId) != null && (newGradeAsInt <= oldGradeAsInt)){
			errorRegisterCourse.setText("Du har bedre karakter i dette emnet fra før");	
		}else {
		stud.addCourse(registerCourseID.getText(), getSemester(), Integer.parseInt(courseYear.getText()), getGrade());
		}
		
		courseTable.getItems().clear();
		courseTable.getItems().addAll(stud.getCourses());
		updateFile();
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
		}
		else if (semesterTogger == semesterSpring) {
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
	}
	else if (gradeTogger == gradeB) {
		grad = "B";
	}
	else if (gradeTogger == gradeC) {
		grad = "C";
	}		
	else if (gradeTogger == gradeD) {
		grad = "D";
	}
	else if (gradeTogger == gradeE) {
		grad = "E";
	}		
	return grad;

	}
	
	@FXML
	public void average() {
		errorCalc.setText("");
		medianLabel.setText("");
		
		if (loggedInStudentID.getText().isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		}
		try {
		Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
		stud.averageGrade(loggedInStudentID.getText());
		averageLabel.setText("Ditt karaktersnitt er: " + stud.averageGrade(loggedInStudentID.getText()));
		} catch(IllegalStateException e){
			errorCalc.setText("Logg inn først!");
		}
		catch(NullPointerException n){
			errorCalc.setText(n.getMessage());
		}
	}
	
	
	@FXML
	public void median() {
		errorCalc.setText("");
		averageLabel.setText("");
		
		if (loggedInStudentID.getText().isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		} 
		
		try {
		Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
		//stud.medianGrade(loggedInStudentID.getText());
		medianLabel.setText("Medianen til ditt karaktersnitt er: " + stud.medianGrade(loggedInStudentID.getText()));
		} 
		catch(IllegalStateException e){
			errorCalc.setText(e.getMessage());
		}
		catch(NullPointerException n){
			errorCalc.setText(n.getMessage());
		}
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
	 * Returns true if any registration for student Textfields is empty
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
	 * Returns true if Textfield registerCourseID is empty, or either of ToggleButtons is not pressed
	 */
	
	private boolean ifAnyFieldEmpty() {
		return registerCourseID.getText().isEmpty() || courseYear.getText().isEmpty() || (!semesterAutumn.isSelected() && !semesterSpring.isSelected()) ||
				(!gradeA.isSelected() && !gradeB.isSelected() && !gradeC.isSelected() && !gradeD.isSelected() &&  !gradeE.isSelected());
	}
	
	/*
	 * returns true if name is not a String
	 */
	
	private boolean isValidFirstName() {
		return (firstName.getText().matches("^[a-zA-Z\s]*$"));
	}
		
	private boolean isValidLastName() {
		return (lastName.getText().matches("^[a-zA-Z\s]*$"));
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
	
	private boolean isValidSearchStudentID() {
		if ((!studentID.getText().matches("^[0-9]*$")) || studentID.getText().length() != 4) {
			return false; 
			}
		return true;
	}
	
	private boolean isValidCourseID() {
		if ((!registerCourseID.getText().matches("^[a-zA-Z][0-9a-zA-Z]*$"))){
			return false;
		}
		return true;
	}
	
	/*
	 * Returns true if Textfield courseYear is a year between 1900 and 2100
	 */ 
	
	private boolean isValidYear() {
		if (Integer.parseInt(courseYear.getText()) < 1900 || Integer.parseInt(courseYear.getText()) > 2100) {
			return false;
		}
		return true;
	}
}

 // en blokk med kode: cmd + 7
 // || = opt + 7
 // \ optn + shift + 7 

package Project;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class Controller {

	/**
	 * Connecting FXML components to Controller class
	 */
	
	// Register new Student
	@FXML
	public TextField firstName;
	@FXML
	public TextField lastName;
	@FXML
	public TextField studentID;

	// Search for Student
	@FXML
	public TextField searchStudentID;

	// Add course
	@FXML
	public TextField registerCourseID;
	@FXML
	public ToggleButton gradeA;
	@FXML
	public ToggleButton gradeB;
	@FXML
	public ToggleButton gradeC;
	@FXML
	public ToggleButton gradeD;
	@FXML
	public ToggleButton gradeE;
	@FXML
	public ToggleButton semesterAutumn;
	@FXML
	public ToggleButton semesterSpring;
	@FXML
	public TextField courseYear;
	@FXML 
	public ToggleGroup semesterToggle;
	@FXML
	public ToggleGroup gradeToggle;


	// Error labels
	@FXML
	public Label errorRegisterStudent;
	@FXML
	public Label errorSearchStudent;
	@FXML
	public Label errorRegisterCourse;
	@FXML
	public Label errorCalc;
	
	
	// Logged in Labels
	@FXML
	public Label loggedInStudentName;
	@FXML
	public Label loggedInStudentID;

	// TableView
	@FXML
	public TableView<Course> courseTable;
	@FXML
	public TableColumn<Course, String> courseID;
	@FXML
	public TableColumn<Course, String> grade;
	@FXML
	public TableColumn<Course, String> semester;
	@FXML
	public TableColumn<Course, String> year;
	
	// Calculating
	@FXML
	public Label averageLabel;
	@FXML
	public Label medianLabel;

	/**
	 * initializing a new StudentRegister
	 */
	StudentRegister studentRegister = new StudentRegister();


	/**
	 * Method to fill register with testdata
	 */
	
	public void fillWithTestData() {
	studentRegister.registerNewStudent("Emily", "Test", "1234");
	studentRegister.registerNewStudent("Mari", "Test", "2345");
	studentRegister.registerNewStudent("Kine", "Test", "3456");
	studentRegister.registerNewStudent("Bokstav", "Test", "wwww");
	//studentRegister.registerNewCourse("TDT4100", "Høst", "2019", "A");
	//studentRegister.registerNewCourse("TMA4100", "Vår", "2020", "C");
	//studentRegister.registerNewCourse("TDT4245", "Høst", "2020", "B");
	
	//Henter ut data fra course ArrayList
	//courseTable.getItems().addAll(studentRegister.getCourses());
	}
	
	
	@FXML
	private void initialize() {
		
		fillWithTestData(); //Fill register with testdata
		
		try {

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
			
			List<Student> students = studentRegister.getStudents();
			System.out.println(students);
			

		} catch (NullPointerException n) {
			System.out.println(n.getMessage());
		}
		
		//Test. Printer ut studentene i konsollen
		System.out.println(studentRegister.toString());
	}	//initialize end
	
	
	
	
	// Printer navnet til studenten når den er logget inn
	@FXML
	public void loggedInStudentName() {
		String ID = searchStudentID.getText();
		//Student stud = studentRegister.findStudentByID(studentID);
		
		Student stud = studentRegister.findStudentByID(ID);
		if(stud  == null) {
			errorSearchStudent.setText("StudentID ble ikke funnet,\nvennligst registrer deg");
		}else {
			try {
				loggedInStudentName.setText("Velkommen, "+ stud.getFirstName()+ " " + stud.getLastName());
			}catch(Exception e) {
				loggedInStudentName.setText("Finner ikke student");
		}}
	}
	
	public void loggedInStudentID() {
		try {
		loggedInStudentID.setText(searchStudentID.getText());
		}catch(Exception e) {
			loggedInStudentName.setText("");
		}
	}
	
	
	public void addStudent() {
		errorRegisterCourse.setText("");
		errorSearchStudent.setText("");
		errorRegisterStudent.setText(""); 
		
		if(ifAnyFieldRegisterStudentEmpty()) {
			errorRegisterStudent.setText("Fyll alle felter");
			throw new IllegalStateException("Fyll alle felter"); //argument eller state?
		}else if(isValidFirstName()) {
			errorRegisterStudent.setText("Skriv inn et gyldig fornavn, kun bokstaver");	
			throw new IllegalArgumentException("Skriv inn et gyldig fornavn, kun bokstaver");
		}else if(isValidLastName()) {
			errorRegisterStudent.setText("Skriv inn et gyldig etternavn, kun bokstaver");	
			throw new IllegalArgumentException("Tast inn et gyldig etternavn, kun bokstaver");
		}else if(!isValidStudentID()) {
			errorRegisterStudent.setText("Student ID består av 4 siffer");
			throw new IllegalArgumentException("Student ID består av 4 siffer");
		}	
		studentRegister.registerNewStudent(firstName.getText(), lastName.getText(), studentID.getText());
		errorRegisterStudent.setText("Registrert! Logg inn for å registrere emner");
	}
	
	
	/*
	 * Search for student in students arraylist
	 */
	
	public void searchStudent() {
		errorRegisterCourse.setText("");
		errorSearchStudent.setText("");
		errorRegisterStudent.setText("");
		errorCalc.setText("");
		
		if(ifSearchFieldEmpty()) {
			errorSearchStudent.setText("Tast inn din Student ID");
			throw new IllegalStateException("Tast inn din Student ID");
		}
		else if(!isValidStudentID()) {
			errorSearchStudent.setText("Student ID består av 4 siffer");
			throw new IllegalArgumentException("Student ID består av 4 siffer");
		}
		
		courseTable.getItems().clear();
		String studentID = searchStudentID.getText();
		Student stud = studentRegister.findStudentByID(studentID);
		
		if(stud  == null) {
			errorSearchStudent.setText("StudentID ble ikke funnet,\nvennligst registrer deg");
		}else {
			
		//Oppdaterer tabellen og getter alle course som ligger i studentRegister på nytt
		courseTable.getItems().addAll(stud.getCourses());
		loggedInStudentID();
		loggedInStudentName();
		}
}	

	//Metode for å hente ut informasjon fra registrering av course
	public void addCourse() {
		errorRegisterCourse.setText("");
		errorSearchStudent.setText("");
		errorRegisterStudent.setText(""); 
		
		//hvis ifAnyFieldEmpty returnerer True, kastes en feilmelding i label og returnerer
		if(loggedInStudentID.getText().isEmpty()) {
			errorRegisterCourse.setText("Logg inn først");
			throw new IllegalStateException("Logg inn først");
		}else if(ifAnyFieldEmpty()){
			errorRegisterCourse.setText("Fyll alle felter");
			throw new IllegalArgumentException("Fyll alle felter");
//		} else if(!isValidCourseID()) {
//			errorRegisterCourse.setText("Fyll inn emnenavn med bokstaver og tall");
//			throw new IllegalArgumentException("Feil i emnenavn");
		} else if(!isValidYear()) {
			errorRegisterCourse.setText("Tast inn gyldig år");
			throw new IllegalArgumentException("Ugyldig år");
		}
		
		getSemester();
		getGrade();
		
		String studentID = loggedInStudentID.getText();
		Student stud = studentRegister.findStudentByID(studentID);
		
//		//denne skal vel ikke være her?
//		if(stud  == null) {
//			errorSearchStudent.setText("StudentID ble ikke funnet,\nvennligst registrer deg");
//		return; //avsluttes hvis ikke studentID finnes
//		}
		
		Course cour = new Course(registerCourseID.getText(), getSemester(), Integer.parseInt(courseYear.getText()), getGrade());
		String cId = registerCourseID.getText();
		String newGrade = getGrade();
		int oldGradeAsInt = 0;
		int newGradeAsInt = Student.gradeAsInt(newGrade);
		
		//Skjer når man legger til en courseID som ikke er lagt til fra før (?)
		//Usikker på hvordan jeg burde sette opp denne sjekken, for den skal jo være null her, det er helt greit. Det er jo ikke lagt til et fag
//		if(stud.findCourseByID(cId)== null) {
//			throw new IllegalStateException("Fag registrert for første gang");
//		}
		try {
		oldGradeAsInt += Student.gradeAsInt(stud.findCourseByID(cId).getGrade());}
		catch(NullPointerException n) {
			System.out.println(n.getMessage());
		}
		
		
		//HVOR sjekker den om den eksisterer fra før???
		//Sjekker om courseID er lagt til fra før. Hvis karakteren har en høyere verdi, erstattes den
		if(stud.findCourseByID(cId) != null && (newGradeAsInt > oldGradeAsInt)) {
			System.out.println(cour.toString());
				stud.removeCourse(registerCourseID.getText());
					stud.addCourse(cour);
					courseTable.getItems().clear(); //Fjerner hele tabellen for å fjerne den gamle karakterern
					courseTable.getItems().addAll(stud.getCourses()); //Laster inn alle karakterene på nytt i tabellen
		} else if(stud.findCourseByID(cId) != null && (newGradeAsInt <= oldGradeAsInt)){
			errorRegisterCourse.setText("Du har bedre karakter i dette emnet fra før");	
		}else {
		//registrerer det nye courset og legger det i testList
		stud.addCourse(cour);
		}
		//Oppdaterer tabellen og getter alle course som ligger i testList på nytt for å unngå duplisering i tabellen
		courseTable.getItems().clear();
		courseTable.getItems().addAll(stud.getCourses());
		}
	
	/*
	 * Collects the semester from ToggleButton
	 */
	
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
	}
	
	
	public void median() {
		errorCalc.setText("");
		averageLabel.setText("");
		
		if (loggedInStudentID.getText().isEmpty()) {
			errorCalc.setText("Logg inn først");
			return;
		} 
		
		try {
		Student stud = studentRegister.findStudentByID(loggedInStudentID.getText());
		stud.medianGrade(loggedInStudentID.getText());
		medianLabel.setText("Medianen til ditt karaktersnitt er: " + stud.medianGrade(loggedInStudentID.getText()));
		} 
		catch(IllegalStateException e){
			errorCalc.setText("Logg inn først!");
		}				
	}
	
	
	

	
	/*
	 * Returns true if any registration Textfields is empty
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
	 * Returns || firstName.getText().length()<30	|| firstName.getText().length()>2)
	 */
	
	private boolean isValidFirstName() {
		return (!firstName.getText().matches("^[a-zA-Z]*$"));
	}
		
	private boolean isValidLastName() {
		return (!lastName.getText().matches("^[a-zA-Z]*$"));
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
	
	private boolean isValidCourseID() {
		if ((!courseID.getText().matches("^[0-9]*$")) || (!courseID.getText().matches("^[a-zA-Z]*$"))) {
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
	
	/*
	 * Returns True if format is first name of String, last name of String, and StudentID is of 4 digits
	 
	private boolean isValidRegistration() {
		if (!firstName.getText().matches("^[a-zA-Z]*$") || firstName.getText().length()>30	|| firstName.getText().length()<30 || 
				!lastName.getText().matches("^[a-zA-Z]*$") || lastName.getText().length()>30	|| lastName.getText().length()<30 ||
				(!isValidStudentID())) {
		return false; 
				}
				return true;
}
	
	 */
}



/*	
private boolean isValidCourseID() {
	if (searchStudentID.getText().isEmpty()){
		return false;
	}
	return true;
}



/*
 ******************************************************************************************************************
 
 // en blokk med kode: cmd + 7
 // || = opt + 7
 // \ optn + shift + 7 
 
 * Lager en ny liste med Student av klassetypen Student, students er det jeg kaller den nye lista, testStudents er der dataen ligger, getSTudents er navnet på getteren i StudentRegister
 			List<Student> students = testStudents.getStudents();
			System.out.println(students);
 * 


	}*/

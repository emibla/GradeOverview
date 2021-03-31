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

public class Controller {

	// Register new Student
	@FXML
	public TextField firstName;
	@FXML
	public TextField lastName;
	@FXML
	public TextField studentID;
	@FXML
	public Button registerStudentButton;

	// Hent inn Student
	@FXML
	public TextField searchStudentID;
	@FXML
	public Button searchStudentButton;

	// Legg til karakterer
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
	public Button registerCourseButton;
	@FXML 
	public ToggleGroup semesterToggle;
	@FXML
	public ToggleGroup gradeToggle;


	// ErrorLabels
	@FXML
	public Label errorRegisterStudent;
	@FXML
	public Label errorSearchStudent;
	@FXML
	public Label errorRegisterCourse;

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
	
	@FXML
	public Label loggedInStudentName;

	/*
	 * @FXML private ObservableList<Course> list;
	 */


	StudentRegister testStudents = new StudentRegister();
	StudentRegister testCourse = new StudentRegister();


	public void fillWithTestData() {
	testStudents.registerNewStudent("Emily", "Test", "1234");
	testStudents.registerNewStudent("Mari", "Test", "2345");
	testStudents.registerNewStudent("Kine", "Test", "3456");
	testCourse.registerNewCourse("TDT4100", "Høst", "2019", "A");
	testCourse.registerNewCourse("TMA4100", "Vår", "2020", "C");
	testCourse.registerNewCourse("TDT4245", "Høst", "2020", "B");
	
	//Henter ut data fra course ArrayList
	courseTable.getItems().addAll(testCourse.getCourses());
	}
	
	@FXML
	private void initialize() {
		fillWithTestData();
		
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
			
			List<Student> students = testStudents.getStudents();
			System.out.println(students);
			
			//Når vi fjerner denne, vil det ikke vises testdata lenger
			// courseTable.getItems().add(new Course("TDT4246", "Høst", "2020", "B"));
			

		} catch (NullPointerException n) {
			System.out.println(n.getMessage());
		}
		
		//Test. Printer ut studentene i konsollen
		System.out.println(testStudents.toString());
			
		//knapp for å registrere student
		registerStudentButton.setOnAction(actionEvent -> {
			addStudent();
		});
		
		//knapp for å søke etter student og sette navn på studenten i label
		searchStudentButton.setOnAction(actionEvent -> {
			loggedInStudentName();
			searchStudent();
		});
		
		//knapp for å registrere emne
		registerCourseButton.setOnAction(actionEvent -> {
			addCourse();
		});
	}

	//Tester om buttonclick endrer label og henter fra textfield
	@FXML
	public void changeLabel() {
		errorSearchStudent.setText(searchStudentID.getText());
	}
	
	public void addStudent() {
		StudentRegister studReg;
		//lager et nytt objekt av typen StudentRegister
		studReg = new StudentRegister();
		errorRegisterStudent.setText(""); //setText og ikke = fordi det er en label og ikke en String 
		if(ifAnyFieldRegisterStudentEmpty()) {
			errorRegisterStudent.setText("Fyll alle felter");
			return;
		}
		//registrerer det nye courset og legger det i testList
		studReg.registerNewStudent(firstName.getText(), lastName.getText(), studentID.getText());
		//Oppdaterer tabellen og getter alle course som ligger i testList på nytt
		System.out.println(studReg.getStudents());
	}
	
	//Søker etter student i students- arraylist
	public void searchStudent() {
		if(ifSearchFieldEmpty()) {
			errorSearchStudent.setText("Tast inn studentID");
			return;
		}
	}
	
	// Printer navnet til studenten når den er logget inn
	@FXML
	public void loggedInStudentName() {
		String ID = searchStudentID.getText();
		ArrayList<Student> stud = testStudents.findStudentByID(ID);
		try {
		loggedInStudentName.setText("Velkommen "+ stud.get(0).getFirstName()+ " " + stud.get(0).getLastName());
		}catch(Exception e) {
			loggedInStudentName.setText("Not found");
		}
	}
	
	//Metode for å hente ut informasjon fra registrering av course
	public void addCourse() {
		StudentRegister testList;
		// lager en ny liste av klassen StudentRegister
		testList = new StudentRegister();
		errorRegisterCourse.setText("");
		//hvis ifAnyFieldEmpty returnerer True, kastes en feilmelding i label og returnerer
		if(ifAnyFieldEmpty())
		{
			errorRegisterCourse.setText("Fyll alle felter");
			return;
		}
		String sem = "";
		String grad = "";
		
		Toggle semesterTogger = semesterToggle.getSelectedToggle();
		if (semesterTogger == semesterAutumn) {
			sem = "Høst";			
		}
		else if (semesterTogger == semesterSpring) {
			sem = "Vår";
		}
		
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
	
		//registrerer det nye courset og legger det i testList
		testList.registerNewCourse(registerCourseID.getText(), sem, courseYear.getText(), grad);
		//Oppdaterer tabellen og getter alle course som ligger i testList på nytt
		courseTable.getItems().addAll(testList.getCourses());
		updateTables();
	}
	

	//Update table method som kjøres hver gang noen legger til et nytt course
	public void updateTables() {
		//something
	}
	
	//Returnerer True hvis firstName, lastName eller studentID ikke er fylt ut
	private boolean ifAnyFieldRegisterStudentEmpty() {
		return firstName.getText().isEmpty()  || lastName.getText().isEmpty() || studentID.getText().isEmpty();
				
	}
	
	//Returnerer True hvis searchStudentID textField er empty
	private boolean ifSearchFieldEmpty() {
		return searchStudentID.getText().isEmpty();
	}
	
	//returnerer True hvis registerCourseID eller courseYear eller en av togglebuttons ikke er fylt inn/presset
	private boolean ifAnyFieldEmpty() {
		return registerCourseID.getText().isEmpty()  || courseYear.getText().isEmpty() || (!semesterAutumn.isSelected() && !semesterSpring.isSelected()) ||
				(!gradeA.isSelected() && !gradeB.isSelected() && !gradeC.isSelected() && !gradeD.isSelected() &&  !gradeE.isSelected());
	}

}

/*
 ******************************************************************************************************************
 
 * Lager en ny liste med Student av klassetypen Student, students er det jeg kaller den nye lista, testStudents er der dataen ligger, getSTudents er navnet på getteren i StudentRegister
 			List<Student> students = testStudents.getStudents();
			System.out.println(students);
 * 
 //hente ut text fra textfield
public void changeLabel() {
	errorSearchStudent.setText(searchStudentID.getText());
}


 * public void getCourses() { ObservableList<String> listItems =
 * FXCollections.observableArrayList(); for(int i=0; i< courses.size(); i++) {
 * Course element = courses.get(i); } Course.setItems(listItems);
 * 
 * }
 * 	
	// SøkButtonOnAction --- brukes ikke
	@FXML
	public void searchStudent_button_on_action() {
		try {
			// Hente ut verdien tastet inn i textfield og finne studenten med samme
			// studentID i arrayList Student
			String studentID = searchStudentID.getText();
			System.out.println(studentID);

		} catch (Exception e) {
			errorSearchStudent.setText("Finner ikke student, er du sikker på at du har tastet rett StudentID?");
		}

	}*/

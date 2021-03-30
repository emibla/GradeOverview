package Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller {
	
	/**
	 * 
	 * @param args
	 

	private static void main(String[] args) { 
		//adding some test data
		StudentRegister testStudents = new StudentRegister();
		StudentRegister testCourse = new StudentRegister();
		testStudents.registerNewStudent("Emily", "Test", 1234);
		testStudents.registerNewStudent("Mari", "Test", 2345);
		testStudents.registerNewStudent("Kine", "Test", 3456);
		testCourse.registerNewCourse("TDT4100", "Høst", 2019, "A");
		testCourse.registerNewCourse("TMA4100", "Vår", 2020, "C");
		testCourse.registerNewCourse("TDT4245", "Høst", 2020, "B");
	}
	*/
	/*
	 * Register new Student
	 */
	
	@FXML
	public TextField firstname;
	@FXML	
	public TextField lastName;
	@FXML
	public TextField studentID;
	@FXML
	public Button registerStudentButton;
	
	
	/*
	 * Hent inn Student
	 */
	
	@FXML
	public TextField searchStudentID;
	@FXML
	public Button searchStudentButton;
	
	
	/*
	 * Legg til karakterer
	 */
	
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
	public Label errorRegisterStudent;
	@FXML
	public Label errorSearchStudent;
	@FXML
	public Label errorRegisterCourse;

	
	
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
    private ObservableList<Course> list;
	
	@FXML
	private void initialize() {
		
		StudentRegister testStudents = new StudentRegister();
		StudentRegister testCourse = new StudentRegister();
		
		testStudents.registerNewStudent("Emily", "Test", "1234");
		testStudents.registerNewStudent("Mari", "Test", "2345");
		testStudents.registerNewStudent("Kine", "Test", "3456");
		testCourse.registerNewCourse("TDT4100", "Høst", "2019", "A");
		testCourse.registerNewCourse("TMA4100", "Vår", "2020", "C");
		testCourse.registerNewCourse("TDT4245", "Høst", "2020", "B");
		testCourse.getCourses();
		
		
		
		try {
		
		//oppretter kolonner i tableView
		TableColumn<Course, String> courseIDcol = new TableColumn<>("Emnekode");
		courseIDcol.setCellValueFactory(new PropertyValueFactory<> ("courseID"));
		courseTable.getColumns().add(courseIDcol);
		
		TableColumn<Course, String> gradeCol = new TableColumn<>("Karakter");
		gradeCol.setCellValueFactory(new PropertyValueFactory<> ("grade"));
		courseTable.getColumns().add(gradeCol);
		
		TableColumn<Course, String> semesterCol = new TableColumn<>("Semester");
		semesterCol.setCellValueFactory(new PropertyValueFactory<> ("semester"));
		courseTable.getColumns().add(semesterCol);
		
		TableColumn<Course, String> yearCol = new TableColumn<>("År");
		yearCol.setCellValueFactory(new PropertyValueFactory<> ("year"));
		courseTable.getColumns().add(yearCol);
		

        courseTable.getItems().add(new Course("TDT4246", "Høst", "2020", "B"));

		
		}catch(NullPointerException n) {
			System.out.println(n.getMessage());
		}
		System.out.println(testStudents.toString());
		searchStudentButton.setOnAction(actionEvent -> { changeLabel();
		
		
		});
		
	}
	public void changeLabel() {
		errorSearchStudent.setText("Test");
}
	
	//SøkButtonOnAction
	@FXML
	public void searchStudent_button_on_action() {
		try {
			//Hente ut verdien tastet inn i textfield og finne studenten med samme studentID i arrayList Student
			int studentID = Integer.parseInt(searchStudentID.getText());
			System.out.println(studentID);

		}
		catch(Exception e){
			errorSearchStudent.setText("Finner ikke student, er du sikker på at du har tastet rett StudentID?");}
		   
	}
	
    public void addNewCourse(Course course) {
        courseTable.getItems().add(course);

}
    /*
    public void getCourses() {
    	ObservableList<String> listItems = FXCollections.observableArrayList();
    	for(int i=0; i< courses.size(); i++) {	
    		Course element = courses.get(i);
    	}
    	Course.setItems(listItems);
    	
    }*/
    
}
	


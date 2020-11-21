/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Course;

/**
 *
 * @author a9512
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
      @FXML
    private Button createbutton;

    @FXML
    private Button readbutton;

    @FXML
    private Button updatebutton;

    @FXML
    private Button deletebutton;
    
    @FXML
    private Button readbyidbutton;

    @FXML
    private Button readbymabutton;
    
     @FXML
    private Button searchButton;
     
    @FXML
    private TableView<Course> CourseTable;

    @FXML
    private TableColumn<Course, Integer> idColum;

    @FXML
    private TableColumn<Course, String> CourseColum;

    @FXML
    private TableColumn<Course, String> majorColum;

    @FXML
    private TableColumn<Course, String> pronameColum;
    
    @FXML
    private TextField searchBar;

    @FXML
    private Button advancedSearch; 
    @FXML
    private Button showDetail;
    @FXML
    private Button showDetailwindow;
    
    @FXML
    void showDetailPlace(ActionEvent event) throws IOException {
        System.out.println("clicked");

        //Source: Demo Code
        Course selectedCourse= CourseTable.getSelectionModel().getSelectedItem();

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));

        Parent detailedModelView = loader.load();

        Scene tableViewScene = new Scene(detailedModelView);

        DetailedModelViewController detailedControlled = loader.getController();


        detailedControlled.initData(selectedCourse);

        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedControlled.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(tableViewScene);
        stage.show();
    }

    
     @FXML
    void showDetailButton(ActionEvent event) throws IOException {
       System.out.println("clicked");

      //Source: Demo Code  
        
        Course selectedCourse = CourseTable.getSelectionModel().getSelectedItem();
        
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));

        
        Parent DetailedModelView = loader.load();

      
        Scene tableViewScene = new Scene(DetailedModelView);

      
        DetailedModelViewController detailedControlled = loader.getController();


        detailedControlled.initData(selectedCourse);

   
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
    }

    
     @FXML
    void advancedSearch1(ActionEvent event) {
        //source: demo code
        System.out.println("clicked");
       
        String name = searchBar.getText();

    
        List<Course> courses = readByCourseAdvanced(name);

     
        if (courses == null || courses.isEmpty()) {

           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Search Dialog Box");
            alert.setHeaderText("Search Results");
            alert.setContentText("No Course Found");
            alert.showAndWait(); 
        } else {
           
            setTableData(courses);
        }
    }

//Source: Demo Code
        private ObservableList<Course> courseData;

    
    public void setTableData(List<Course> courseList) {

        
        courseData = FXCollections.observableArrayList();

        
        courseList.forEach(c -> {
            courseData.add(c);
        });

        
        CourseTable.setItems(courseData);
        CourseTable.refresh();
    }
    
//Source:Demo Code
    @FXML
    void createCourse(ActionEvent event) {
       Scanner input = new Scanner(System.in);
        
    
        System.out.println("Enter Course ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Course Name:");
        String name = input.next();
        
        System.out.println("Enter Course Major:");
        String major = input.next();
        
        System.out.println("Enter Professor Name:");
        String proname = input.next();
        
       
        Course courses = new Course();
        
       
        courses.setId(id);
        courses.setName(name);
        courses.setMajor(major);
        courses.setProname(proname);
           
        create(courses);
    }
//source: demo code
    @FXML
    void deleteCourse(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Course ID:");
        int id = input.nextInt();
        
        Course s = readById(id);
        System.out.println("we are deleting this course: "+ s.toString());
        delete(s);

    }
    //Source:Demo Code
    @FXML
    void readCourse(ActionEvent event) {
         Query query = manager.createNamedQuery("Course.findAll");
        List<Course> courses = query.getResultList();

        for (Course s : courses) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getMajor()+" "+s.getProname());
        }
        
    }
//source:demo code
    @FXML
    void updateCourse(ActionEvent event) {
       Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Course ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Course Name:");
        String name = input.next();
        
        System.out.println("Enter Course Major:");
        String major = input.next();
        
        System.out.println("Enter Course Professor Name:");
        String proname = input.next();
        
        Course courses = new Course();
        
        courses.setId(id);
        courses.setName(name);
        courses.setMajor(major);
        courses.setProname(proname);   
        update(courses);
        System.out.println("Course Updated");
    }
    //source: demo code
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Query query = manager.createNamedQuery("Course.findAll");
        List<Course> data = query.getResultList();

        for (Course s : data) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getMajor()+" "+s.getProname());
        }
    }
    //Source: demo code
     @FXML
    void readByID(ActionEvent event) {
       Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Course ID:");
        int id = input.nextInt();
        
        Course s = readById(id);
        System.out.println(s.toString());
    }
//source: demo code
    @FXML
    void readByMajor(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Major:");
        String major = input.next();
        List<Course> s = readByMajor(major);
        System.out.println(s.toString());
    }
    
     @FXML
    void searchButton1(ActionEvent event) {
        System.out.println("Clicked");
        
        //Source: Demo Code     
        String name = searchBar.getText();

       
        List<Course> courses = readByCourse(name);

        if (courses == null || courses.isEmpty()) {

           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Search Dialog Box");
            alert.setHeaderText("Search Results");
            alert.setContentText("No Course Found");
            alert.showAndWait(); 
        } else {

        
            setTableData(courses);
        }
    }


    //Source:Demo Code
    EntityManager manager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Source:Demo Code
        manager = (EntityManager) Persistence.createEntityManagerFactory("YuanHaoHsuFXMLPU").createEntityManager();
        
        //Source: Demo Code
         CourseColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColum.setCellValueFactory(new PropertyValueFactory<>("id"));
        pronameColum.setCellValueFactory(new PropertyValueFactory<>("proname"));
        majorColum.setCellValueFactory(new PropertyValueFactory<>("major"));

        CourseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }    
    //Source:Demo Code
public void create(Course courses) {
        try {
            manager.getTransaction().begin();
            
            if (courses.getId() != null) {
                
                manager.persist(courses);
                
                manager.getTransaction().commit();
                
                System.out.println(courses.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
//source: demo code
public Course readById(int id){
        Query query = manager.createNamedQuery("Course.findById");
        
        query.setParameter("id", id);
        
        Course courses = (Course) query.getSingleResult();
        if (courses != null) {
            System.out.println(courses.getId() + " " + courses.getName() + " " + courses.getMajor()+" "+courses.getProname());
        }
        
        return courses;
    }        
//source:demo code
  public void delete(Course courses) {
        try {
            Course existingCourse = manager.find(Course.class, courses.getId());

            if (existingCourse != null) {
                
                manager.getTransaction().begin();
                
                manager.remove(existingCourse);
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }      
  //Source: Demo Code
     public void update(Course model) {
        try {

            Course existingCourse = manager.find(Course.class, model.getId());

            if (existingCourse != null) {

                manager.getTransaction().begin();
                
                existingCourse.setName(model.getName());
                existingCourse.setMajor(model.getMajor());
                existingCourse.setProname(model.getProname());
         
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     //source: demo code
      public List<Course> readByMajor(String major){
        Query query = manager.createNamedQuery("Course.findByMajor");
        
        query.setParameter("major", major);
        
        List<Course> courses =  query.getResultList();
        for (Course c: courses) {
            System.out.println(c.getId() + " " + c.getName() + " " + c.getMajor()+" "+c.getProname());
        }
        
        return courses;
    } 
      //Source:Demo Code
     public List<Course> readByCourse(String name) {
        Query query = manager.createNamedQuery("Course.findByName");

        query.setParameter("name", name);

        List<Course> courses = query.getResultList();
        for (Course course : courses) {
            System.out.println(course.getId() + " " + course.getName() + " " + course.getMajor()+" "+course.getProname());
        }

        return courses;
    }
     //source:demo code
     public List<Course> readByCourseAdvanced(String name) {
        Query query = manager.createNamedQuery("Course.findByCourseNameAdvanced");


        query.setParameter("name", name);


        List<Course> courses = query.getResultList();
        for (Course course : courses) {
            System.out.println(course.getId() + " " + course.getName() + " " + course.getMajor()+" "+course.getProname());
        }

        return courses;
    }
    }        


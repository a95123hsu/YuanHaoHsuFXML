/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


    //Source:Demo Code
    EntityManager manager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Source:Demo Code
        manager = (EntityManager) Persistence.createEntityManagerFactory("YuanHaoHsuFXMLPU").createEntityManager();

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
    }        


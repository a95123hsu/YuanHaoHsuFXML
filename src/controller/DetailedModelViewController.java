/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Course;

/**
 *
 * @author a9512
 */
public class DetailedModelViewController {
  @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label CourseMajor;

    @FXML
    private Label courseID;

    @FXML
    private Label CourseName;

    @FXML
    private Label ProName;

    @FXML
    private ImageView image;
    
      @FXML
    private Button backButton;

    @FXML
    void backButton1(ActionEvent event) {
        //Source:Demo Code
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
        if (previousScene != null) {
            stage.setScene(previousScene);
        }


    }
//Source: Demo Code
     Scene previousScene;
//Source: Demo Code
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }
    Course selectedModel;
       public void initData(Course model) {
        selectedModel = model;
        courseID.setText(model.getId().toString());
        CourseName.setText(model.getName());
        ProName.setText(model.getProname());
        CourseMajor.setText(model.getMajor());

        try {

            String imagename = "/resource/images/" + model.getProname()+ ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

 @FXML
    void initialize() {
        assert CourseMajor != null : "fx:id=\"CourseMajor\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert courseID != null : "fx:id=\"courseID\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert CourseName != null : "fx:id=\"CourseName\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert ProName != null : "fx:id=\"ProName\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailedModelView.fxml'.";

    }
}
 

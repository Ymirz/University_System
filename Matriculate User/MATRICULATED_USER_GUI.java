/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import static boola_university_management_system_main.BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN.setTextLimit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class MATRICULATED_USER_GUI {
    
    public TextField userSsnTf = new TextField();
    public TextField userFnameTf = new TextField();
    public TextField userLnameTf = new TextField();
    public TextField userMnameTf = new TextField();
    public TextField userStreetAddressTf = new TextField();
    public TextField userCityTf = new TextField();
    public TextField userZipCodeTf = new TextField();
    public TextField userDateTf = new TextField();
    
    public CheckBox userHighSchool = new CheckBox();
    public CheckBox userImmunization = new CheckBox();
    
    public ComboBox userStatesCombo = new ComboBox();
    public ComboBox userDegreesCombo = new ComboBox();
    public ComboBox userYrMatriculationCombo = new ComboBox();
    
    public Button clearBtn = new Button("Clear");
    public Button submitBtn = new Button("Submit");
    public Button cancelBtn = new Button("Cancel");
    
    public Label userSsnLb = new Label("SSN# ");
    public Label userFnameLb = new Label("First Name: ");
    public Label userLnameLb = new Label("Last Name: ");
    public Label userMnameLb = new Label("MI: ");
    public Label userStrtAddrLb = new Label("Street Address: ");
    public Label userCityLb = new Label("City: ");
    public Label userStateLb = new Label("State: ");
    public Label userZipCodeLb = new Label("Zip Code: ");
    public Label userDateLb = new Label("Date: ");
    public Label yrOfMatriculationLb = new Label("Matriculation Yr:");
    public Label degreeLb = new Label("Degree: ");
    public Label highSchLb = new Label("HS Diploma:");
    public Label immunizationLb = new Label("Immunization:");
    public Label updateLb = new Label("Updates:");
    
    public TextField updateTxt = new TextField();
    
    public GridPane root = new GridPane();
    
    MATRICULATED_USER_BACKEND userInfo;
    
    Stage primaryStage = new Stage();
    
    public MATRICULATED_USER_GUI() {
        
    updateTxt.setEditable(false);
    updateTxt.setStyle("-fx-background-color: #7f7f7f");
    
    userStatesCombo.getItems().addAll(
        "AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA"
            ,"HI","ID","IL","IN","IA","KS","KY","LA","MT"
            ,"NE","NV","NH","NJ","NM","NY","NC","ND","OH"
            ,"OK","OR","PA","RI","SC","SD","TN","TX","ME"
            ,"MD","MA","MI","MN","MS","MO","UT","VT","VA"
            ,"WA","WV","WI","WY"
    );
    
    
    userDegreesCombo.getItems().addAll (
        "Associate Of Science in CP",
        "Associate of Arts in Humanities"
    );
     
    userYrMatriculationCombo.getItems().addAll (
        "Freshman",
        "Sophmore",
        "junior",
        "Senior"
    );
    
    setTextLimit(userSsnTf, 8);
    
    userYrMatriculationCombo.setMaxWidth(150.0);
    userStatesCombo.setMaxWidth(150.0);
    userDegreesCombo.setMaxWidth(150.0);
    
    userDateTf.setEditable(false);
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    userDateTf.setText(dateFormat.format(date));
    
    setTextLimit(userSsnTf, 8);
    setTextLimit(userMnameTf,1);
    
    submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    userInfo =
                            new MATRICULATED_USER_BACKEND(userSsnTf.getText(), userFnameTf.getText(), userLnameTf.getText(), userMnameTf.getText(), userStreetAddressTf.getText(),
                                    userCityTf.getText(), userZipCodeTf.getText(), userDateTf.getText(), userStatesCombo.getValue().toString(), 
                                    userDegreesCombo.getValue().toString(),userYrMatriculationCombo.getValue().toString(), userHighSchool.isSelected(), userImmunization.isSelected());
                    
                    primaryStage.close();
                    
                } catch (Exception ex) {
                    Logger.getLogger(MATRICULATED_USER_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(userInfo.isValid) {
                   updateTxt.setText("Registered info");
                   //Send info to sql database
                   
               } else {
                   updateTxt.setText("Not Registered");
               }
            }
            
    });
        
    // Styles 
    updateLb.setLayoutX(5);
    updateLb.setLayoutY(510);
        
    updateTxt.setMaxWidth(400);
    updateTxt.setMaxHeight(50);
    updateTxt.setLayoutX(5);
    updateTxt.setLayoutY(535);
        
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(10,10,10,10));
    
    root.add(userSsnLb, 0, 0);
    root.add(userSsnTf, 1, 0);
    
    root.add(userFnameLb,0,1);
    root.add(userFnameTf,1,1);
    
    root.add(userLnameLb,2,1);
    root.add(userLnameTf,3,1);
    
    root.add(userMnameLb, 4, 1);
    root.add(userMnameTf, 5, 1);
    
    root.add(userStrtAddrLb,0,2);
    root.add(userStreetAddressTf,1,2);
    
    root.add(userCityLb,0,3);
    root.add(userCityTf,1,3);
    
    root.add(userStateLb,2,3);
    root.add(userStatesCombo,3,3);
    
    root.add(userZipCodeLb,4,3);
    root.add(userZipCodeTf,5,3);
    
    root.add(userDateLb,0,4);
    root.add(userDateTf,1,4);
    
    root.add(yrOfMatriculationLb, 2, 4);
    root.add(userYrMatriculationCombo,3,4);
    
    root.add(degreeLb,0,5);
    root.add(userDegreesCombo,1,5);
    
    root.add(highSchLb,0,6);
    root.add(userHighSchool,1,6);
    
    root.add(immunizationLb,2,6);
    root.add(userImmunization,3,6);
    
    root.add(clearBtn,0,7);
    root.add(submitBtn,1,7);
    root.add(cancelBtn,2,7);
    
    root.add(updateLb, 0,9);
    root.add(updateTxt, 1, 9);
    
    Scene scene = new Scene(root, 750, 640);
        
    primaryStage.setResizable(false);
    primaryStage.setTitle("Boola Boola University");
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    
    public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(even -> {
        String string = textField.getText();
        
        if (string.length() > length) {
            textField.setText(string.substring(0, length));
            textField.positionCaret(string.length());
        }
        });
    }
}

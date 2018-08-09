/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import static boola_university_management_system_main.BOOLA_BOOLA_DB.getConnection;
import static java.lang.Double.parseDouble;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class REGISTRATION_FULL_TIME_GUI {
    
    BOOLA_BOOLA_DB boolaDb;
         
    public TableView table = new TableView();
    
    public Label studentNameLb = new Label("Student Name: ");
    public Label courseNumberLb = new Label("Course Number: ");
    public Label priceLb = new Label("Total Price: ");
    public Label creditLb = new Label("Total Credits: ");
    public Label updateLb = new Label("Updates:");
    
    public TextField studentNameTf = new TextField();
    public TextField coursePricesTf = new TextField();
    public TextField creditTf = new TextField();
      
    public Button registerBtn = new Button("Register");
    public Button clearBtn = new Button("Clear Field");
    public Button cancelBtn = new Button("Cancel");
    public Button addBtn = new Button("Add Course");
    
    int totalCred;
    double total;
    
    public TextArea txtArea = new TextArea();
    
    public Pane root = new Pane();
    
    REGISTRATION_FULL_TIME_BACKEND userRegistration = new REGISTRATION_FULL_TIME_BACKEND();
    
    BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN main = new BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN();
    
    Boolean isCreditValid;
    
     int tempCred = 0;
    
    public String retrieveCourseId, retrieveCourseCredit, retrieveCourseName, retrieveCoursePrice, retrieveUserSsn, userFname, userLname;
    
    Stage primaryStage = new Stage();
    
    Scene scene;
    
    public REGISTRATION_FULL_TIME_GUI (String userSsn, String userFname, String userLname) throws Exception {
        
        this.userFname = userFname;
        this.userLname = userLname;
        
        this.retrieveUserSsn = userSsn;
        
        studentNameTf.setEditable(false);
        coursePricesTf.setEditable(false);
        creditTf.setEditable(false);
       
                
        System.out.println("User SSN REGISTRATION: " +retrieveUserSsn);
        
        TableColumn<REGISTRATION_FULL_TIME_BACKEND, String> courseColumn = new TableColumn<>("Course Number");
        courseColumn.setMinWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
        
        TableColumn<REGISTRATION_FULL_TIME_BACKEND, String> creditColumn = new TableColumn<>("Credits");
        creditColumn.setMinWidth(100);
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("courseCredit"));
        
        TableColumn<REGISTRATION_FULL_TIME_BACKEND, String> courseNameColumn = new TableColumn<>("Course Name");
        courseNameColumn.setMinWidth(100);
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
                
        TableColumn<REGISTRATION_FULL_TIME_BACKEND, String> courseCostColumn = new TableColumn<>("Course Costs");
        courseCostColumn.setMinWidth(100);
        courseCostColumn.setCellValueFactory(new PropertyValueFactory<>("courseCost"));
        
        table.getColumns().addAll(courseColumn, creditColumn, courseNameColumn, courseCostColumn);
        table.setItems(getUserRegistration());
            
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isCreditValid) {
                    System.out.println("Dummy: Added To Sql database");
                    primaryStage.close();
                } else {
                    System.out.println("Dummy: Not sent to sql database");
                }
            }
        });
        
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                total = 0;
                totalCred = 0;
                coursePricesTf.setText("");
                creditTf.setText("");
                txtArea.setText("");
            }
        });
        
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Dummy: Returning to main menu");
            }
        });
        
       addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("User SSN REGISTRATION in add: " + retrieveUserSsn);
                
                try {
                    tempCred = getCredits();
                } catch (Exception ex) {
                    Logger.getLogger(REGISTRATION_FULL_TIME_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                ObservableList<REGISTRATION_FULL_TIME_BACKEND> itemSelected, allItems;
                allItems = table.getItems();
                itemSelected = table.getSelectionModel().getSelectedItems();
                
                
                for(REGISTRATION_FULL_TIME_BACKEND user:itemSelected){
                    
                    if(user.getCourseCredit().equalsIgnoreCase("NC")) {
                        String courseNam = user.getCourseName();
                        try {
                            user.storeInfo(retrieveUserSsn, courseNam);
                        } catch (Exception ex) {
                            Logger.getLogger(REGISTRATION_FULL_TIME_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    try{
                             
                         if (checkCredit(tempCred)) {
                            String courseNum = user.getCourseNumber();
                            String courseCredit = user.getCourseCredit();
                            String courseNam = user.getCourseName();
                            String courseCost = user.getCourseCost();
                    
                            System.out.println(courseNum);
                            System.out.println(courseCost);
                            System.out.println(courseCredit);
                    
                            double temp = parseDouble(user.getCourseCost());
                            total += temp;
                   
                            coursePricesTf.setText(String.valueOf(total));
                            creditTf.setText(String.valueOf(totalCred));
                    
                            txtArea.appendText(courseNum + " " + courseNam + " " + courseCredit + " " + courseCost + "\n");
                    
                            user.storeInfo(retrieveUserSsn, courseNam);
                         }
                        
                    } catch (NumberFormatException e) {
                      
                    
       
                    }   catch (Exception ex) {
                        Logger.getLogger(REGISTRATION_FULL_TIME_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
        });
       
        studentNameTf.setText(userFname + " " + userLname);
        
        // Styles placed on scene
        updateLb.setLayoutX(5);
        updateLb.setLayoutY(510);
        
        txtArea.setMaxWidth(400);
        txtArea.setMaxHeight(50);
        txtArea.setLayoutX(5);
        txtArea.setLayoutY(535);
        
        studentNameLb.setLayoutX(5);
        studentNameLb.setLayoutY(10);
        
        studentNameTf.setLayoutX(100);
        studentNameTf.setLayoutY(5);
        
        priceLb.setLayoutX(500);
        priceLb.setLayoutY(10);
        
        coursePricesTf.setLayoutX(585);
        coursePricesTf.setLayoutY(5);
        
        creditLb.setLayoutX(500);
        creditLb.setLayoutY(55);
        
        creditTf.setLayoutX(585);
        creditTf.setLayoutY(50);
        
        table.setLayoutX(5);
        table.setLayoutY(50);
        
        registerBtn.setLayoutX(5);
        registerBtn.setLayoutY(475);
        
        clearBtn.setLayoutX(105);
        clearBtn.setLayoutY(475);
        
        cancelBtn.setLayoutX(355);
        cancelBtn.setLayoutY(475);
        
        addBtn.setLayoutX(225);
        addBtn.setLayoutY(475);
     
        root.getChildren().addAll(studentNameLb, studentNameTf, table, registerBtn, clearBtn, cancelBtn, addBtn, priceLb, coursePricesTf, creditTf, creditLb, updateLb, txtArea);
        
        scene = new Scene(root, 750, 640);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Boola Boola University");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Use this code to connect to SQL datbase then verify with NAME ** _BACKEND
    
public ObservableList<REGISTRATION_FULL_TIME_BACKEND> getUserRegistration() throws Exception {
        boolaDb = new BOOLA_BOOLA_DB();
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("select courseNumber,courseCredits,courseName,courseCost from courses"); 
        ResultSet result = st.executeQuery(sql);
        
        ObservableList<REGISTRATION_FULL_TIME_BACKEND> userRegistrations = FXCollections.observableArrayList();
        
        while(result.next()) {
            userRegistrations.add(new REGISTRATION_FULL_TIME_BACKEND(result.getString("courseNumber"), result.getString("courseCredits"), result.getString("courseName"),result.getString("courseCost")));
         }
         
        return userRegistrations;
    }
    
public boolean checkCredit(int credits) {
        if(credits >= 9) {
            txtArea.setText("");
            txtArea.appendText("Maximum limit: Cannot More than '9' credits, if 'NC' will add to data\n");
            total = 0;
            totalCred = 0;
            creditTf.setText("");
            coursePricesTf.setText("");
            isCreditValid = false;
            return false;
        } else {
            isCreditValid = true;
            return true;
        }
    }
    
public int getCredits() throws Exception {
        
        int tmpCredits = 0;
        int cnt = 0;
        boolaDb = new BOOLA_BOOLA_DB();
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("select * from studentinfo inner join courses on studentInfo.courseName = courses.courseName"); 
        ResultSet result = st.executeQuery(sql);
       
        while(result.next()) {
            cnt++;
            if(retrieveUserSsn.equalsIgnoreCase(result.getString("ssn"))) {
                if(!result.getString("courseCredits").equalsIgnoreCase("NC")) {
                    tmpCredits += Integer.parseInt(result.getString("courseCredits"));
                    System.out.println("Accumulating Credits: " + tmpCredits);
                    System.out.println("Accumulating Loop: " + cnt);
                }
            }
        }
        return tmpCredits;
        
    }
}

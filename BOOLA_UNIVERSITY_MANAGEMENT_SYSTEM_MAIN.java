/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN extends Application  {
    
    BOOLA_BOOLA_DB boolaDb;
    
    Label ssnLb = new Label("SSN #");
    Label studentFNameLb = new Label("First Name");
    Label studentLNameLb = new Label("Last Name");
    
    TextField userSsnTf = new TextField();
    TextField studentFNameTf = new TextField();
    TextField studentLNameTf = new TextField();
    TextArea updateTxtArea = new TextArea();
    
    Button loginBtn = new Button("Login");
    Button logoutBtn = new Button("logout");
    
    Boolean isValidInfo;
    
    final Menu menu1 = new Menu("Menu");
    
    final Menu admissionsMenu = new Menu("Admissions");
    final Menu registrationMenu = new Menu("Registration");
    final Menu reportsMenu = new Menu("Reports");
    
    final MenuItem quit = new MenuItem("Quit");
    
    final MenuItem matriculatedMenuItm = new MenuItem("Matriculated");
    final MenuItem nonMatriculatedMenuItm = new MenuItem("Non-Matriculated");
    
    final MenuItem fullTimeMenuItm = new MenuItem("Full Time");
    final MenuItem partTimeMenuItm = new MenuItem("Part Time");
    
    final MenuItem receivablesMenuItm = new MenuItem("Receivables");
    final MenuItem classScheduleMenuItm = new MenuItem("Class Schedule");
    
    public String _USER_SSN, userFname, userLname;
    
    MenuBar menuBar = new MenuBar();
    
    public Pane root = new Pane();
    
    public Scene scene;
         
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        boolaDb = new BOOLA_BOOLA_DB();
        
        menu1.getItems().addAll(admissionsMenu, registrationMenu, reportsMenu, quit);
        
        admissionsMenu.getItems().addAll(matriculatedMenuItm, nonMatriculatedMenuItm);
        registrationMenu.getItems().addAll(partTimeMenuItm, fullTimeMenuItm);
        reportsMenu.getItems().addAll(receivablesMenuItm, classScheduleMenuItm);
       
        menuBar.getMenus().addAll(menu1);
        
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        
        if(_USER_SSN == "") {
            fullTimeMenuItm.setDisable(true);
            reportsMenu.setDisable(true);
            receivablesMenuItm.setDisable(true);
            classScheduleMenuItm.setDisable(true);
            partTimeMenuItm.setDisable(true);
        } else {
            reportsMenu.setDisable(false);
            receivablesMenuItm.setDisable(false);
            classScheduleMenuItm.setDisable(false);
            partTimeMenuItm.setDisable(false);
            fullTimeMenuItm.setDisable(false);
        }
        
        setTextLimit(userSsnTf, 8);
        
        matriculatedMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MATRICULATED_USER_GUI matriculatedUser = new MATRICULATED_USER_GUI();
            }
        });
        
        nonMatriculatedMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NON_MATRICULATED_USER_GUI nonMatriculatedUser = new NON_MATRICULATED_USER_GUI();
            }
        });
        
        
        fullTimeMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             
                    try {
                        REGISTRATION_FULL_TIME_GUI registrationFullTime = new REGISTRATION_FULL_TIME_GUI(_USER_SSN,userFname, userLname);
                    } catch (Exception ex) {
                        Logger.getLogger(BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
        });
        
        partTimeMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
                try {
                        REGISTRATION_PART_TIME_GUI registrationPartTime = new REGISTRATION_PART_TIME_GUI(_USER_SSN, userFname, userLname);
                    } catch (Exception ex) {
                        Logger.getLogger(BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
        });
        
        receivablesMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                try {
                        RECIEVABLES_USER recievables = new RECIEVABLES_USER (_USER_SSN);
                    } catch (Exception ex) {
                        Logger.getLogger(BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
        });
        
        classScheduleMenuItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             
                try {
                        CLASS_SCHEDULES_USER classSchedules = new CLASS_SCHEDULES_USER(_USER_SSN);
                    } catch (Exception ex) {
                        Logger.getLogger(BOOLA_UNIVERSITY_MANAGEMENT_SYSTEM_MAIN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
        });
        
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    QUIT_USER quit = new QUIT_USER();
                
            }
        });
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try{
                    
                    boolaDb = new BOOLA_BOOLA_DB();
                    Connection con = boolaDb.getConnection();
                    Statement st = con.createStatement();
                    String sql = ("select ssn, firstName, lastName from students"); 
                    ResultSet result = st.executeQuery(sql);
                    
                    String userSsn = userSsnTf.getText();
                    userFname = studentFNameTf.getText();
                    userLname = studentLNameTf.getText();
                    
                     _USER_SSN = userSsn;
                    
                    
                    while(result.next()) {
                        if(userSsn.equalsIgnoreCase(result.getString("ssn")) && userFname.equalsIgnoreCase(result.getString("firstName")) && userLname.equalsIgnoreCase(result.getString("lastName"))){
                            
                            System.out.println("DEBUG SSN: " + _USER_SSN);
                            
                            isValidInfo = true;
                            
                            studentFNameTf.setText("");
                            studentLNameTf.setText("");
                            userSsnTf.setText("");
                            
                            studentFNameTf.setVisible(false);
                            studentLNameTf.setVisible(false);
                            userSsnTf.setVisible(false);
                            
                            studentFNameLb.setVisible(false);
                            studentLNameLb.setVisible(false);
                            ssnLb.setVisible(false);
                            
                            logoutBtn.setVisible(true);
                            
                        } else {
                             isValidInfo = false;
                             studentFNameTf.setText("");
                             studentLNameTf.setText("");
                             userSsnTf.setText("");
                        }
                    }
                    
                    System.out.println("Info is valid "+ isValidInfo);
               
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                            isValidInfo = true;

                            studentFNameTf.setVisible(true);
                            studentLNameTf.setVisible(true);
                            userSsnTf.setVisible(true);
                            
                            studentFNameLb.setVisible(true);
                            studentLNameLb.setVisible(true);
                            ssnLb.setVisible(true);
                            
                            logoutBtn.setVisible(false);
            }
        });
        
        updateTxtArea.setStyle("-fx-font-alignment: center");
        updateTxtArea.setText("You must login in order to view schedule and recievables");
        
        // Styles
        ssnLb.setStyle("-fx-background-color: #ffffff");
        studentFNameLb.setStyle("-fx-background-color: #ffffff");
        studentLNameLb.setStyle("-fx-background-color: #ffffff");
        
        ssnLb.setLayoutX(290);
        ssnLb.setLayoutY(70);
        
        studentFNameLb.setLayoutX(290);
        studentFNameLb.setLayoutY(140);
        
        studentLNameLb.setLayoutX(290);
        studentLNameLb.setLayoutY(210);
        
        userSsnTf.setLayoutX(290);
        userSsnTf.setLayoutY(100);
        
        studentFNameTf.setLayoutX(290);
        studentFNameTf.setLayoutY(170);
        
        studentLNameTf.setLayoutX(290);
        studentLNameTf.setLayoutY(240);
        
        loginBtn.setLayoutX(340);
        loginBtn.setLayoutY(300);
        
        logoutBtn.setLayoutX(340);
        logoutBtn.setLayoutY(300);
        
        logoutBtn.setVisible(false);
        
        updateTxtArea.setLayoutX(135);
        updateTxtArea.setLayoutY(600);
        
        updateTxtArea.setEditable(false);
        updateTxtArea.setMaxWidth(800);
        updateTxtArea.setMaxHeight(25);
        
        
        //public Pane root = new Pane();
        root.setStyle("-fx-background-color: #303030;");
        
        root.getChildren().addAll(menuBar, ssnLb, userSsnTf, studentLNameTf, studentFNameLb, studentFNameTf, studentLNameLb, loginBtn, logoutBtn, updateTxtArea);
  
        scene = new Scene(root, 750, 640);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Boola Boola University");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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

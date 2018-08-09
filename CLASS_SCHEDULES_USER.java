/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import boola_university_management_system_main.BOOLA_BOOLA_DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class CLASS_SCHEDULES_USER {
    
    CLASS_SCHEDULES_BACKEND back = new CLASS_SCHEDULES_BACKEND();
    
    BOOLA_BOOLA_DB boolaDb;
    
    Stage primaryStage = new Stage();
    
    public Pane root = new Pane();
    
    String scheduleSsn, tmpCourseName;
    
    int count = 0;
    
    public TextArea txtArea = new TextArea();
    
    public CLASS_SCHEDULES_USER(String userSsn) throws Exception
    {
        scheduleSsn = userSsn;
        
        System.out.println("Do i have ssn? " + scheduleSsn);
        
        txtArea.setEditable(false);
         
        getUserInfo();
        
        txtArea.setLayoutX(135);
        txtArea.setLayoutY(50);      
       
        
        root.getChildren().add(txtArea);
        
        Scene scene = new Scene(root, 750, 640);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Boola Boola University");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void getUserInfo() throws SQLException, Exception {
         
        boolaDb = new BOOLA_BOOLA_DB();
        
        System.out.println("Do i have ssn inside getUser? " + scheduleSsn);
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("select ssn, firstName, lastName, yrOfMatriculation from students"); 
        ResultSet result = st.executeQuery(sql);
        
        while(result.next()) {
               
            if(scheduleSsn.equalsIgnoreCase(result.getString("ssn"))) {
                
                String tmpSsn = result.getString("ssn");
                String tmpFirstName = result.getString("firstName");
                String tmpLastName = result.getString("lastName");
                String tmpYrOfMatric = result.getString("yrOfMatriculation");
                txtArea.appendText("User Info: [SSN#]: " + tmpSsn + " [FIRST]: " + tmpFirstName + " [LAST]: " + tmpLastName + " [MATRICULATION]: " + tmpYrOfMatric + "\n\n");
            }
        }
        
        result.close();
        result = st.getResultSet();
        
        //This string combines table 1 and table 2 using inner join method in SQL cmd
        sql = ("select * from studentinfo inner join courses on studentInfo.courseName = courses.courseName"); 
        result = st.executeQuery(sql);
       
        while(result.next()) {
              
            if(scheduleSsn.equalsIgnoreCase(result.getString("ssn")))
            {
                String tmpCourseNumber = result.getString("courseNumber");
                String tmpCourseName = result.getString("courseName");
                String tmpCourseTime = result.getString("courseTime");
                txtArea.appendText("\n" + tmpCourseNumber + " [Course Name]: " + tmpCourseName + " [Course Time]: " + tmpCourseTime);
            }
         }   
    }
}
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class RECIEVABLES_USER {
    
    BOOLA_BOOLA_DB boolaDb;
    
    Stage primaryStage = new Stage();
    
    public Pane root = new Pane();
    
    String scheduleSsn, tmpCourseName;
    
    int count = 0, totalCred, totalPrice;
    
    public TextArea txtArea = new TextArea();
    
    boolean isPartTime;
    
    public RECIEVABLES_USER(String userSsn) throws Exception
    {
        scheduleSsn = userSsn;
        
        System.out.println("Do i have ssn? " + scheduleSsn);
        
        txtArea.setEditable(false);
         
        getUserInfo();
       
        
        totalCred = 0;
        
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
        
        ArrayList<String> tmpList = new ArrayList<String>();
        
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
                try {
                String tmpCourseNumber = result.getString("courseNumber");
                String tmpCourseName = result.getString("courseName");
                String tmpCourseCredit = result.getString("courseCredits");
                String tmpCoursePrice = result.getString("courseCost");
                
                
                int tempCred = Integer.parseInt(tmpCourseCredit);
                totalCred += tempCred;
                
                int tempPrices = Integer.parseInt(tmpCoursePrice);
                totalPrice += tempPrices;
                    
                txtArea.appendText("\n" + tmpCourseNumber + " [Course Name]: " + tmpCourseName + " [Course Credit]: " + tmpCourseCredit + " [Total Price]: " + totalPrice);
                } catch (NumberFormatException e) {
                    
                String tmpCourseNumber = result.getString("courseNumber");
                String tmpCourseName = result.getString("courseName");
                String tmpCourseCredit = result.getString("courseCredits");
                String tmpCoursePrice = result.getString("courseCost");
                
                int tempPrices = Integer.parseInt(tmpCoursePrice);
                totalPrice += tempPrices;
                    
                txtArea.appendText("\n" + tmpCourseNumber + " [Course Name]: " + tmpCourseName + " [Course Credit]: " + tmpCourseCredit + " [Accumulated Price]: " + totalPrice);
                }
            }
         }
         txtArea.appendText("\n[Total Credit]: " + totalCred);
    }
    
public boolean checkCredit(int credits) {
        if(credits > 6) {
            txtArea.setText("");
            totalCred = 0;
            return false;
        } else {
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
            if(!result.getString("courseCredits").equalsIgnoreCase("NC")) {
                tmpCredits += Integer.parseInt(result.getString("courseCredits"));
                System.out.println("Accumulating Credits: " + tmpCredits);
                System.out.println("Accumulating Loop: " + cnt);
            }
        }
        return tmpCredits;
        
    }
    
}

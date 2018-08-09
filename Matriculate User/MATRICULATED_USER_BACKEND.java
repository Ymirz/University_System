/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Myles
 */

public class MATRICULATED_USER_BACKEND {
    
    String userSsn, userFname, userLname, userMname, userStrtAddress, userCity, userZipCode, userDate,userStates, userDegree, userMatric;
    public boolean userHighSchool, userImmunization, isEmpty, isValid;
    
    BOOLA_BOOLA_DB boolaDb;
    
    public MATRICULATED_USER_BACKEND(String userSsn, String userFname, String userLname, String userMname, String userStrtAddress
    ,String userCity, String userZipCode, String userDate, String userStates, String userDegree, String userMatric, Boolean userHighSchool, Boolean userImmunization) throws Exception {
        this.userSsn = userSsn;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userMname = userMname;
        this.userStrtAddress = userStrtAddress;
        this.userCity = userCity;
        this.userZipCode = userZipCode;
        this.userDate = userDate;
        this.userStates = userStates;
        this.userDegree = userDegree;
        this.userMatric = userMatric;
        this.userHighSchool = userHighSchool;
        this.userImmunization = userImmunization;
        
        System.out.println("User SSN: " + userSsn + " " + userFname + " " + userStates + " " + userDegree + " " + userHighSchool + " " + userImmunization + " " + userMatric + " " + userDate);
        
        if(userHighSchool && userImmunization) {
           isValid = isInteger(userSsn);
           
        } else {
            isValid = false;
        }
        
        
        
        try {
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO students (ssn, firstName, mi, lastName, streetAddr, city, state, zipCode, date, yrOfMatriculation, degree, highSchool, immunization) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, userSsn);
        pstmt.setString(2, userFname);
        pstmt.setString(3, userMname);
        pstmt.setString(4, userLname);
        pstmt.setString(5, userStrtAddress);
        pstmt.setString(6, userCity);
        pstmt.setString(7, userStates);
        pstmt.setString(8, userZipCode);
        pstmt.setString(9, userDate);
        pstmt.setString(10, userMatric);
        pstmt.setString(11, userDegree);
        pstmt.setBoolean(12, userHighSchool);
        pstmt.setBoolean(13, userImmunization);
        pstmt.executeUpdate();
        
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Boolean isInteger(String input) {
        try{
            Integer.parseInt(input);
            return true;
        } catch( Exception e) {
            return false;
        }
    }
    
}

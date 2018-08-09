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

/**
 *
 * @author Myles
 */
public class REGISTRATION_PART_TIME_BACKEND {
    
   public String courseNumber, courseCredit, courseName, courseCost;
   
   BOOLA_BOOLA_DB boolaDb;
    
    public REGISTRATION_PART_TIME_BACKEND() {
        
    }
    
    public REGISTRATION_PART_TIME_BACKEND(String courseNumber, String courseCredit, String courseName, String courseCost) {
        
        this.courseNumber = courseNumber;
        this.courseCredit = courseCredit;
        this.courseName = courseName;
        this.courseCost = courseCost;
    }
    
    public String getCourseNumber() {
            return courseNumber;
    }
    
    public String setCourseNumber(String courseNum) {
        return this.courseNumber = courseNum;
    }
    
    public String getCourseCredit() {
            return courseCredit;
    }
    
    public String setCourseCredit(String courseCredit) {
        return this.courseCredit = courseCredit;
    }
    
    public String getCourseName() {
            return courseName;
    }
    
    public String setCourseName (String courseName) {
        return this.courseName = courseName;
    }
    
    public String getCourseCost() {
            return courseCost;
    }
    
    public String setCourseCosts(String courseCost) {
        return this.courseCost = courseCost;
    }
    
    public void storeInfo(String courseSsn, String courseName) throws Exception {
        
        try {
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO studentInfo (ssn, courseName) values(?,?)"); 
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, courseSsn);
        pstmt.setString(2, courseName);
   
        pstmt.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}

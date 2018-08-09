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
public class CLASS_SCHEDULES_BACKEND {
    
    public String ssn, firstName, lastName, yrOfMatriculation, courseNumber, courseName, courseLocation;
   
    BOOLA_BOOLA_DB boolaDb;
    
    public CLASS_SCHEDULES_BACKEND() {
        
    }
    
    public CLASS_SCHEDULES_BACKEND(String ssn, String firstName, String lastName, String yrOfMatriculation) {
        
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yrOfMatriculation = yrOfMatriculation;
    }
    
    public CLASS_SCHEDULES_BACKEND(String courseNumber, String courseName) {
        
        this.courseNumber = courseNumber;
        this.courseName = courseName;
 
    }
    
    public CLASS_SCHEDULES_BACKEND(String courseLocation) {
        
        this.courseLocation = courseLocation;
 
    }
    
    public String getFirstName() {
            return firstName;
    }
    
    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }
    
    public String getLastName() {
            return lastName;
    }
    
    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }
    
    public String getSsn() {
            return ssn;
    }
    
    public String setSsn (String ssn) {
        return this.ssn = ssn;
    }
    
    public String getYrOfMatriculation() {
            return yrOfMatriculation;
    }
    
    public String setYrOfMatriculation (String yrOfMatriculation) {
        return this.yrOfMatriculation = yrOfMatriculation;
    }
    
    public String getCourseLocation() {
            return yrOfMatriculation;
    }
    
    public String setCourseLocation (String courseLocation) {
        return this.courseLocation = courseLocation;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boola_university_management_system_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Myles
 */
public class NON_MATRICULATED_USER_BACKEND {
    
    BOOLA_BOOLA_DB boolaDb;
        
    String userSsn, userFname, userLname, userMname, userStreetAddress, recOfImmune;
    
    public NON_MATRICULATED_USER_BACKEND(String userSsn, String userFname, String userLname, String userMname, String userStreetAddress, boolean recOfImmune) throws Exception {
        this.userSsn = userSsn;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userMname = userMname;
        this.userStreetAddress = userStreetAddress;
        //this.recOfImmune = recOfImmune;
        String tmp = String.valueOf(recOfImmune);
        
        this.recOfImmune = tmp;
        
        System.out.println("DEBUG: " + userSsn + " " + recOfImmune);
        
        try {
        Connection con = boolaDb.getConnection();
        Statement st = con.createStatement();
        String sql = ("INSERT INTO students (ssn, firstName, mi, lastName, streetAddr,city,state,zipCode,date,yrOfMatriculation,degree,highSchool,immunization) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        pstmt.setString(1, userSsn);
        pstmt.setString(2, userFname);
        pstmt.setString(3, userMname);
        pstmt.setString(4, userLname);
        pstmt.setString(5, userStreetAddress);
        pstmt.setString(6, "NULL");
        pstmt.setString(7, "NULL");
        pstmt.setString(8, "NULL");
        pstmt.setString(9, "NULL");
        pstmt.setString(10, "NULL");
        pstmt.setString(11, "NULL");
        pstmt.setString(12, "NULL");
        pstmt.setBoolean(13, recOfImmune);
      
        pstmt.executeUpdate();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

package boola_university_management_system_main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Myles
 */

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class BOOLA_BOOLA_DB {
    
    
    BOOLA_BOOLA_DB() throws Exception {
        
        getConnection();
    }
    
    public static Connection getConnection() throws Exception {
        try{
            
        String driver = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/boolauni?autoReconnect=true&useSSL=false";
        String USERNAME = "root";
        String PASSWORD = "Dunkin50";
        Class.forName(driver);
        
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        
        System.out.println("Coneected !");
        return conn;
               
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class jdbcConnection {
 
    Connection con;
    
    public static Connection jdbcConnect(){
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase","root","");
            return con;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    public static void main(String[] args) {
        jdbcConnection jd = new jdbcConnection();
    }
}

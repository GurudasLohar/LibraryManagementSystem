/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class contactPage extends JFrame {
    
    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbImage,loginTitle;
   
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public contactPage() {
        
        super("Reader' Circle");
        
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        setLayout(null);
////        setSize(1000,750);
////        setLocation(500, 200);
////        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundImage();
        ContactPanel();
        //ContactComponents();
        
        con = jdbcConnection.jdbcConnect();
        
    }
     public void BackgroundImage(){
        
        backImage = new ImageIcon("contactPage.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void ContactPanel(){
    
        login = new JPanel();
        login.setLayout(null);
        login.setSize(800,500);
        login.setBackground(new Color(0,0,0,199));
        login.setBounds(100,130,800,500);
        lbImage.add(login);
    
    }
    public void ContactComponents(){
    
    
    
    }
    public static void main(String[] args) {
        contactPage cp = new contactPage();
            cp.setSize(1000,750);
            cp.setLocation(500, 200);
            cp.setVisible(true);
        
    }
    
}

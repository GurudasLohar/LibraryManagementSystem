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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class accountPage extends JFrame{
         
    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbImage,lbUserId,lbUserName,lbOut;
    JLabel lbUserId1,lbUserName1;
    
        
    JTable tb;
    String [] colName = {"Book Id","Book Name","Issued Date","Return Date"};
    
    Connection con;
    ResultSet rs,rs1;
    PreparedStatement pst,pst1;

    public accountPage(){
    
        super("Reader' Circle");
        
        con = jdbcConnection.jdbcConnect();
        
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        
        setLayout(null);
        setSize(1000,750);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundImage();
        MenuPanel();
        MenuComp();
        
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("accountPage.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void MenuPanel(){
        
        menu = new JPanel();
        menu.setLayout(null);
        menu.setSize(1000,70);
        menu.setBackground(new Color(255,200,205,245));
        menu.setBounds(0,100,1000,70);
        lbImage.add(menu);
    }
    public void MenuComp(){
    
        Font mf1 = new Font("Segoe UI Historic",Font.BOLD,16);
        Font mf2 = new Font("Consolas",Font.BOLD,14);
        Font mf3 = new Font("Segoe UI",Font.BOLD,17);
        
        lbUserId = new JLabel("User Id");
        lbUserId.setBounds(50,20,60,25);
        lbUserId.setFont(mf3);
        lbUserId.setForeground(Color.WHITE);
        menu.add(lbUserId);
        
        lbUserId1 = new JLabel();
        lbUserId1.setBounds(120,20,10,25);
        lbUserId1.setFont(mf3);
        lbUserId1.setForeground(Color.BLACK);
        menu.add(lbUserId1);
        
        lbUserName = new JLabel("User Name");
        lbUserName.setBounds(180,20,90,25);
        lbUserName.setFont(mf3);
        lbUserName.setForeground(Color.WHITE);
        menu.add(lbUserName);
                
        lbUserName1 = new JLabel();
        lbUserName1.setBounds(280,20,100,25);
        lbUserName1.setFont(mf3);
        lbUserName1.setForeground(Color.BLACK);
        menu.add(lbUserName1);
                
        lbOut = new JLabel("Log Out");
        lbOut.setBounds(850,20,120,25);
        lbOut.setFont(mf3);
        lbOut.setForeground(Color.WHITE);
        menu.add(lbOut);
        lbOut.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    loginPage lp = new loginPage();
                        lp.setSize(1000,750);
                        lp.setLocation(500, 200);
                        lp.setVisible(true);
                    
                }
        });
    }
    
    public void BookTable(String userid){

       DefaultTableModel mod = new DefaultTableModel();
       mod.setColumnIdentifiers(colName);
       tb = new JTable();
       tb.setModel(mod);
       tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       tb.setFillsViewportHeight(true);
       tb.setForeground(Color.BLACK);
       tb.setRowHeight(20);
       tb.setBackground(new Color(255,200,210));
       JScrollPane js = new JScrollPane(tb);
      
       String bId = "";
       String bName = "";
       String iDate = "";
       String rDate = "";
       String temp;
       
       String sts = "Issued";
       String sql2 = "select BookId,IssueDate,ReturnDate from issuedetails where UserId = '"+userid+"' and status = '"+sts+"'";
       
       try{
           pst = con.prepareStatement(sql2);
           rs = pst.executeQuery();
           
           while(rs.next()){
              
              bId = rs.getString(1);
              iDate = rs.getString(2);
              rDate = rs.getString(3);
              
              temp = rs.getString(1);
              String sqlQ = "select BookTitle from bookcatalog where BookId = '"+temp+"'";
              
              pst1 = con.prepareStatement(sqlQ);
              rs1 = pst1.executeQuery();
              
              while(rs1.next()){
                  bName = rs1.getString(1);
                  mod.addRow(new Object[]{bId,bName,iDate,rDate});
              }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        js.setBounds(150,250,700,150);
        lbImage.add(js);
    }
    
    
    public static void main(String[] args) {
        accountPage ap = new accountPage();
//        ap.setSize(1000,750);
//        ap.setLocation(500, 200);
//        ap.setVisible(true);
        
    }
}

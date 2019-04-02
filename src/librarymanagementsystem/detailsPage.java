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
public class detailsPage extends JFrame{
    
    ImageIcon backImage,loginImage;
    JPanel login;
    JLabel lbImage;
    JLabel lbTable1,lbTable2;
    
    JTable tb1,tb2;
    String [] colName1 = {"Book Id","User Id","Issue Date","Return Date"};
    String [] colName2 = {"Book Id","User Id","Issue Date","Return Date"};
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public detailsPage() {
        
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
        BookTable1();
        BookTable2();   
       
    }
         public void BackgroundImage(){
        
        backImage = new ImageIcon("book3.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
        
    public void BookTable1(){
       
       Font f1 = new Font("Segoe UI",Font.BOLD,20);
        
       lbTable1 = new JLabel("Table for Issued books");
       lbTable1.setBounds(50,75,300,45);
       lbTable1.setFont(f1);
       lbTable1.setForeground(new Color(239,228,158,225));
       lbImage.add(lbTable1); 
        
       DefaultTableModel mod = new DefaultTableModel();
       mod.setColumnIdentifiers(colName1);
       tb1 = new JTable();
       tb1.setModel(mod);
       tb1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       tb1.setFillsViewportHeight(true);
       tb1.setBackground(new Color(239,228,158,225));
       JScrollPane js = new JScrollPane(tb1);
      
       String bId = "";
       String uId = "";
       String Iss = "";
       String Ret = "";
            
       String sts = "Issued";
       String sql2 = "select BookId,UserId,IssueDate,ReturnDate from issuedetails where status = '"+sts+"'";
       
       try{
           pst = con.prepareStatement(sql2);
           rs = pst.executeQuery();
           
           while(rs.next()){
              bId = rs.getString(1);
              uId = rs.getString(2);
              Iss = rs.getString(3);
              Ret = rs.getString(4);
              
              mod.addRow(new Object[]{bId,uId,Iss,Ret});
           } 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        js.setBounds(50,125,500,200);
        lbImage.add(js);
    }     
        
    public void BookTable2(){
        
       Font f1 = new Font("Segoe UI",Font.BOLD,20);
        
       lbTable2 = new JLabel("Table for Returned books");
       lbTable2.setBounds(50,400,300,45);
       lbTable2.setFont(f1);
       lbTable2.setForeground(new Color(239,228,158,225));
       lbImage.add(lbTable2); 
        
       DefaultTableModel mod = new DefaultTableModel();
       mod.setColumnIdentifiers(colName2);
       tb2 = new JTable();
       tb2.setModel(mod);
       tb2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       tb2.setFillsViewportHeight(true);
       tb2.setBackground(new Color(239,228,158,225));
       JScrollPane js = new JScrollPane(tb2);
      
       String bId = "";
       String uId = "";
       String Iss = "";
       String Ret = "";
            
       String sts = "Returned";
       String sql2 = "select BookId,UserId,IssueDate,ReturnDate from issuedetails where status = '"+sts+"'";
       
       try{
           pst = con.prepareStatement(sql2);
           rs = pst.executeQuery();
           
           while(rs.next()){
              bId = rs.getString(1);
              uId = rs.getString(2);
              Iss = rs.getString(3);
              Ret = rs.getString(4);
              
              mod.addRow(new Object[]{bId,uId,Iss,Ret});
           } 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        js.setBounds(50,450,500,200);
        lbImage.add(js);
    }         
         
         
         
    public static void main(String[] args) {
        detailsPage dp = new detailsPage();
//            dp.setSize(1000,750);
//            dp.setLocation(500, 200);
//            dp.setVisible(true);
        
    }  
}

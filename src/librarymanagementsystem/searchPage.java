/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author LENOVO
 */
public class searchPage  extends JFrame {
    
    ImageIcon backImage,loginImage;
    JPanel Issue;
    JLabel lbImage;
    JTable SearchTb;
    DefaultTableModel model;
        
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public searchPage() throws HeadlessException {
        
      
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
        SearchComponents();
        searchTable();
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("searchPage.jpeg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void SearchComponents(){
        
        Font f3 = new Font("Calisto MT",Font.BOLD,13);
       
        SearchTb = new JTable();    
        SearchTb.setBounds(150,100,700,350);
        SearchTb.setFont(f3);
        SearchTb.setRowHeight(16);
        SearchTb.setBackground(new Color(239,228,158,225));
        JScrollPane sp=new JScrollPane(SearchTb); 
        sp.setBounds(150,150,700,350);
        lbImage.add(sp);
    }
    public void searchTable(){
            try{
            
            String sql = "select * from bookcatalog";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            SearchTb.setModel((DbUtils.resultSetToTableModel(rs)));
            
            SearchTb.getColumnModel().getColumn(0).setPreferredWidth(20);
            SearchTb.getColumnModel().getColumn(1).setPreferredWidth(150);
            SearchTb.getColumnModel().getColumn(2).setPreferredWidth(150);
            SearchTb.getColumnModel().getColumn(3).setPreferredWidth(20);
            SearchTb.getColumnModel().getColumn(4).setPreferredWidth(20);
            SearchTb.getColumnModel().getColumn(5).setPreferredWidth(20);
            
            }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
            }
    }
    
     public static void main(String[] args) {
        
        searchPage sp = new searchPage();
//            sp.setSize(1000,750);
//            sp.setLocation(500, 200);
//            sp.setVisible(true);
    }
    
}

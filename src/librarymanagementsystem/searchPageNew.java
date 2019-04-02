/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author LENOVO
 */
public class searchPageNew extends JFrame{
    
    ImageIcon backImage,loginImage;
    JPanel search;
    JLabel lbImage;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public searchPageNew() {
        super("Reader' Circle");
         con = jdbcConnection.jdbcConnect();
         
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        
        setLayout(null);
        setSize(1000,750);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TableSearch();
    }
    
    public void TableSearch(){
    
        String sql = "select * from bookcatalog";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmt = rs.getMetaData(); 
            int c = rsmt.getColumnCount();
            Vector column = new Vector(c);

                for(int i = 1; i <= c; i++) {
                        column.add(rsmt.getColumnName(i)); 
                } 

                Vector data = new Vector(); 
                Vector row = new Vector(); 

                while(rs.next()) { 
                    row = new Vector(c); 

                        for(int i = 1; i <= c; i++){
                                row.add(rs.getString(i)); 
                        } 

                    data.add(row); 
                }
             
                search = new JPanel();
                JTable table = new JTable(data,column);
                JScrollPane jsp = new JScrollPane(table); 
                search.setLayout(new BorderLayout()); 
                search.add(jsp,BorderLayout.CENTER); 
                setContentPane(search); 
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    
    }
    
    
    public static void main(String[] args) {
        
        searchPageNew sp = new searchPageNew();
//            sp.setSize(1000,750);
//            sp.setLocation(500, 200);
//            sp.setVisible(true);
    }
    
}

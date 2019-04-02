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
public class removePage extends JFrame implements ActionListener{
        
    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbImage,remTitle;
    JLabel lbUserid,lbUsername;
    JTextField txtUserid,txtUsername;
    JButton btSearch,btSubmit,btClear;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public removePage(){
    
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
        RemovePanel();
        RemoveComponents();
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("removePage.jpeg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void RemovePanel(){
        
        login = new JPanel();
        login.setLayout(null);
        login.setSize(700,400);
        login.setBackground(new Color(192,192,192,190));
        login.setBounds(150,100,700,400);
        lbImage.add(login);
    }
    public void RemoveComponents(){
    
        Font f1 = new Font("Verdana",Font.BOLD,25);
        Font f2 = new Font("Segoe UI Symbol",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.BOLD,14);
        Font f4 = new Font("Segoe UI",Font.BOLD,16);
        
        remTitle = new JLabel("Student Removal");
        remTitle.setBounds(250,20,250,45);
        remTitle.setFont(f1);
        remTitle.setForeground(Color.BLACK);
        login.add(remTitle);
    
        lbUserid = new JLabel("User Id");
        lbUserid.setBounds(30,150,60,30);
        lbUserid.setFont(f2);
        lbUserid.setForeground(Color.BLACK);
        login.add(lbUserid);
        
        txtUserid = new JTextField();
        txtUserid.setBounds(110,150,150,30);
        txtUserid.setFont(f3);
        txtUserid.setOpaque(false);
        txtUserid.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtUserid.setForeground(Color.BLACK);
        login.add(txtUserid);
        
        ImageIcon serImg = new ImageIcon("ser.png");
        btSearch = new JButton(serImg);
        btSearch.setBounds(280,150,30,30);
        login.add(btSearch);
        btSearch.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
                SearchName();
            }
        });
        
        lbUsername = new JLabel("User Name");
        lbUsername.setBounds(370,150,80,30);
        lbUsername.setFont(f2);
        lbUsername.setForeground(Color.BLACK);
        login.add(lbUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(470,150,150,30);
        txtUsername.setFont(f3);
        txtUsername.setOpaque(false);
        txtUsername.setEditable(false);
        txtUsername.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtUsername.setForeground(Color.BLACK);
        login.add(txtUsername);
        
        btSubmit = new JButton("Confirm");
        btSubmit.setBounds(125,250,100,35);
        btSubmit.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        btSubmit.setBackground(new Color(200,191,240,190));
        btSubmit.setForeground(Color.BLACK);
        btSubmit.setFont(f4);
        login.add(btSubmit);
        btSubmit.addActionListener(this);
        
        btClear = new JButton("Clear");
        btClear.setBounds(500,250,100,35);
        btClear.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        btClear.setBackground(new Color(200,191,240,190));
        btClear.setForeground(Color.BLACK);
        btClear.setFont(f4);
        login.add(btClear);
        btClear.addActionListener(this);
    }
    public void SearchName(){
    
       String userid = txtUserid.getText();
       String sql1 = "select UserName from userdetails where UserId = '"+userid+"'";
       try{
           pst = con.prepareStatement(sql1);
           rs = pst.executeQuery();
           while(rs.next()){
                   txtUsername.setText(rs.getString(1));
           }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void RemoveStd(){
    
        String userId = txtUserid.getText();
        String sql2 = "delete from userdetails where UserId = '"+userId+"'";
        
        try{
            JOptionPane.showMessageDialog(null,"Are you want to remove above student details??");
            pst = con.prepareStatement(sql2);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Student details removed successfully");
            clear();
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void clear(){
    
        txtUserid.setText("");
        txtUsername.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSubmit){
            RemoveStd();
        }
        if(e.getSource() == btClear){
            clear();
        }
    }
  
    public static void main(String[] args) {
        removePage rp = new removePage();
//        rp.setSize(1000,750);
//        rp.setLocation(500, 200);
//        rp.setVisible(true);
        
    }
}

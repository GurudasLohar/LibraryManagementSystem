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
public class forgetPage extends JFrame implements ActionListener{
   
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    ImageIcon backImage;
    JPanel forget;
    JLabel lbImage,forgetTitle,lbUser,lbEmail,lbQuestion,lbAnswer,lbPassword;
    JTextField txtUser,txtEmail,txtQuestion,txtAnswer,txtPassword;
    JButton btSearch,btRetrive,btLogin;
    
    public forgetPage() {
    
        super("Reader' Circle");
        con = jdbcConnection.jdbcConnect();

        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        
        setLayout(null);
        setSize(1000,750);
        setLocation(500,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundImage();
        ForgetPanel();
        ForgetComponents();
       
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("library7.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void ForgetPanel(){
    
        forget = new JPanel();
        forget.setLayout(null);
        forget.setSize(500,650);
        forget.setBackground(new Color(115,115,255,160));
        forget.setBounds(250,100,550,570);
        lbImage.add(forget);
        
    }
    public void ForgetComponents(){
    
    
        Font f1 = new Font("Verdana",Font.BOLD,25);
        Font f2 = new Font("Segoe UI Symbol",Font.PLAIN,12);
        Font f3 = new Font("Consolas",Font.PLAIN,15);
        Font f4 = new Font("Arial",Font.PLAIN,16);
        Font f5 = new Font("Arial",Font.BOLD,18);
        
        forgetTitle = new JLabel("Forget Password?");
        forgetTitle.setBounds(110,18,250,45);
        forgetTitle.setFont(f1);
        forgetTitle.setForeground(Color.white);
        forget.add(forgetTitle);
        
        lbUser = new JLabel("USERNAME");
        lbUser.setBounds(50,80,100,30);
        lbUser.setFont(f2);
        lbUser.setForeground(Color.WHITE);
        forget.add(lbUser);
        
        txtUser = new JTextField();
        txtUser.setBounds(50,110,320,35);
        txtUser.setOpaque(false);
        txtUser.setFont(f3);
        txtUser.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtUser.setForeground(Color.WHITE);
        //txtUser.setBackground(new Color(115,115,255,160));
        forget.add(txtUser);
        
        lbEmail = new JLabel("Email");
        lbEmail.setBounds(50,150,100,30);
        lbEmail.setFont(f2);
        lbEmail.setForeground(Color.WHITE);
        forget.add(lbEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(50,180,320,35);
        txtEmail.setOpaque(false);
        txtEmail.setFont(f3);
        txtEmail.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setEditable(false);
        forget.add(txtEmail);
        
        lbQuestion = new JLabel("Question");
        lbQuestion.setBounds(50,220,100,30);
        lbQuestion.setFont(f2);
        lbQuestion.setForeground(Color.WHITE);
        forget.add(lbQuestion);
        
        txtQuestion = new JTextField();
        txtQuestion.setBounds(50,250,320,35);
        txtQuestion.setOpaque(false);
        txtQuestion.setFont(f3);
        txtQuestion.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtQuestion.setForeground(Color.white);
        txtQuestion.setEditable(false);
        forget.add(txtQuestion);
        
        lbAnswer = new JLabel("Answer");
        lbAnswer.setBounds(50,290,100,30);
        lbAnswer.setFont(f2);
        lbAnswer.setForeground(Color.WHITE);
        forget.add(lbAnswer);
        
        txtAnswer = new JTextField();
        txtAnswer.setBounds(50,320,320,35);
        txtAnswer.setOpaque(false);
        txtAnswer.setFont(f3);
        txtAnswer.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtAnswer.setForeground(Color.WHITE);
        forget.add(txtAnswer);
        
        lbPassword = new JLabel("Password");
        lbPassword.setBounds(50,360,100,30);
        lbPassword.setFont(f2);
        lbPassword.setForeground(Color.WHITE);
        forget.add(lbPassword);
        
        txtPassword = new JTextField();
        txtPassword.setBounds(50,390,320,35);
        txtPassword.setOpaque(false);
        txtPassword.setFont(f3);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setEditable(false);
        forget.add(txtPassword);
        
        btSearch = new JButton("Search");
        btSearch.setBounds(400,110,120,35);
        btSearch.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE));
        btSearch.setBackground(new Color(115,115,255,240));
        btSearch.setForeground(Color.WHITE);
        btSearch.setFont(f5);
        forget.add(btSearch);
        btSearch.addActionListener(this);
        
        btRetrive = new JButton("Retrive");
        btRetrive.setBounds(400,320,120,35);
        btRetrive.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE));
        btRetrive.setBackground(new Color(115,115,255,240));
        btRetrive.setForeground(Color.WHITE);
        btRetrive.setFont(f5);
        forget.add(btRetrive);
        btRetrive.addActionListener(this);
        
        btLogin = new JButton("Go to Login");
        btLogin.setBounds(150,475,200,35);
        btLogin.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE));
        btLogin.setBackground(new Color(115,115,255,240));
        btLogin.setForeground(Color.WHITE);
        btLogin.setFont(f5);
        forget.add(btLogin);
        btLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btSearch){
            SearchData();
        }
        if(e.getSource() == btRetrive){
            RetriveData();
        }
        
        if(e.getSource() == btLogin){
            loginPage ls  = new loginPage();
            ls.setSize(1000,750);
            ls.setLocation(500, 200);
            ls.setVisible(true);
        }
    }
    public void SearchData(){
    
        String user = txtUser.getText();
        //System.out.println(user);
        String sqlQ1 = "select * from userdetails where username = '"+user+"'";
        try{
            pst = con.prepareStatement(sqlQ1);
            rs = pst.executeQuery();
                if(rs.next()){
                        txtEmail.setText(rs.getString(4));
                        txtQuestion.setText(rs.getString(5));
                }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void RetriveData(){
    
        String ans = txtAnswer.getText();
        System.out.println(ans);
        String sqlQ2 = "select * from userdetails where answer = '"+ans+"'";
        try{
            pst = con.prepareStatement(sqlQ2);
            rs = pst.executeQuery();
                if(rs.next()){
                      txtPassword.setText(rs.getString(3));   
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Answer");
                }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public static void main(String[] args) {
        
       forgetPage fp = new forgetPage();
    }
    
}

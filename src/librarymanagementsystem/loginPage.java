/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author LENOVO
 */

public class loginPage extends JFrame implements ActionListener{
    
    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbImage,lbUser,lbPassword,loginTitle;
    JLabel lbQuestion,lbSignup,lbForget;
    JLabel lbAdmin,lbContact;
    JTextField txtUser;
    JPasswordField txtPassword;
    JButton btLogin;
    JSeparator separator;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    String temp11;
    
    public loginPage() {
    
        super("Reader' Circle");
                
        con = jdbcConnection.jdbcConnect();
        
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundImage();
        LoginPanel();
        MenuPanel();
        LoginComponents();
        MenuComponents();

   }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("library14.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void LoginPanel(){
        
        login = new JPanel();
        login.setLayout(null);
        login.setSize(400,500);
        login.setBackground(new Color(0,0,0,210));
        login.setBounds(450,150,400,475);
        lbImage.add(login);
    }
    public void MenuPanel(){
        
        menu = new JPanel();
        menu.setLayout(null);
        menu.setSize(1000,70);
        menu.setBackground(Color.BLACK);
        menu.setBounds(0,50,1000,70);
        lbImage.add(menu);
    }
    public void LoginComponents(){
    
        Font f1 = new Font("Verdana",Font.BOLD,30);
        Font f2 = new Font("Segoe UI Historic",Font.PLAIN,11);
        Font f3 = new Font("Consolas",Font.PLAIN,14);
        Font f4 = new Font("Segoe UI",Font.PLAIN,13);
        Font f5 = new Font("Yu Gothic UI",Font.BOLD,18);
        Font f6 = new Font("Segoe UI",Font.PLAIN,14);
        Font f7 = new Font("Yu Gothic UI",Font.BOLD,16);
        
        loginTitle = new JLabel("Sign In");
        loginTitle.setBounds(140,18,150,45);
        loginTitle.setFont(f1);
        loginTitle.setForeground(Color.WHITE);
        login.add(loginTitle);
        
        lbUser = new JLabel("USERNAME");
        lbUser.setBounds(50,100,100,20);
        lbUser.setFont(f2);
        lbUser.setForeground(Color.WHITE);
        login.add(lbUser);
        
        txtUser = new JTextField();
        txtUser.setBounds(50,130,320,35);
        txtUser.setFont(f3);
        txtUser.setOpaque(false);
        txtUser.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtUser.setForeground(Color.WHITE);
        txtUser.setBackground(new Color(0,0,0,210));
        login.add(txtUser);
        
        lbPassword = new JLabel("PASSWORD");
        lbPassword.setBounds(50,190,100,20);
        lbPassword.setFont(f2);
        lbPassword.setForeground(Color.WHITE);
        login.add(lbPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(50,220,320,35);
        txtPassword.setFont(f3);
        txtPassword.setOpaque(false);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBackground(new Color(0,0,0,210));
        login.add(txtPassword);
        
        lbForget = new JLabel("Lost your Password?");
        lbForget.setBounds(250,260,150,30);
        lbForget.setFont(f4);
        lbForget.setForeground(Color.WHITE);
        login.add(lbForget);
        lbForget.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    forgetPage fp = new forgetPage();
                }
        });
       
        btLogin = new JButton("Login");
        btLogin.setBackground(Color.BLACK);
        btLogin.setForeground(Color.WHITE);
        btLogin.setBounds(90,340,250,35);
        btLogin.setFont(f5);
        login.add(btLogin);
        btLogin.addActionListener(this);
        
        lbQuestion = new JLabel("New User?");
        lbQuestion.setBounds(130,420,80,30);
        lbQuestion.setFont(f6);
        lbQuestion.setForeground(Color.WHITE);
        login.add(lbQuestion);
        
        lbSignup = new JLabel("Signup");
        lbSignup.setBounds(200,420,70,30);
        lbSignup.setFont(f7);
        lbSignup.setForeground(Color.WHITE);
        login.add(lbSignup);
        lbSignup.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    signupPage sp = new signupPage();
                }
        });
        
        lbQuestion = new JLabel("Here.");
        lbQuestion.setBounds(255,420,60,30);
        lbQuestion.setFont(f6);
        lbQuestion.setForeground(Color.WHITE);
        login.add(lbQuestion);
    }
    
    public void MenuComponents(){
    
        Font mf1 = new Font("Segoe UI Historic",Font.BOLD,14);
        Font mf2 = new Font("Consolas",Font.BOLD,14);
        Font mf3 = new Font("Segoe UI",Font.BOLD,18);
        
        lbContact = new JLabel("Contact us");
        lbContact.setBounds(755,20,120,25);
        lbContact.setFont(mf1);
        lbContact.setForeground(Color.WHITE);
        menu.add(lbContact);
        lbContact.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    contactPage cp = new contactPage();
                }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btLogin){
            Authentication();
        }
    }
    
    
    public void Authentication(){
    
        String sqlQuery = "select username,password,RoleId from userdetails where username = ? and password = ?";
        
        temp11 = txtUser.getText();
        //System.out.println("temp11  "+temp11);
        try{
           pst = con.prepareStatement(sqlQuery);
           pst.setString(1,txtUser.getText());
           pst.setString(2,(String.valueOf(txtPassword.getPassword())));
           rs = pst.executeQuery();
           rs.next();
           if(rs.getRow() > 0){
               
                if(rs.getInt(3) == 1){
                    adminPage al = new adminPage();
                }
                else{
                    firstPage fp = new firstPage();
                    fp.lbUserName.setText(temp11);
                    fp.Search(temp11);
                                       
                }
            }
            else{
               JOptionPane.showMessageDialog(null,"Incorrect credentials");
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    public static void main(String[] args) {
        
        loginPage ls =  new loginPage();        
            ls.setSize(1000,750);
            ls.setLocation(500, 200);
            ls.setVisible(true);        
}
}

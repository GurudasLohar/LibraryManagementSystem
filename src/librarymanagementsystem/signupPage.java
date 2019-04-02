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
import javax.swing.border.LineBorder;
/**
 *
 * @author LENOVO
 */
public class signupPage extends JFrame implements ActionListener{
   
    Connection con;
    
    ImageIcon backImage;
    JLabel lbImage,signupTitle,lbUser,lbPassword;
    JLabel lbQuestion,lbEmail,lbAnswer;
    JTextField txtUser,txtEmail,txtAnswer;
    JPasswordField txtPassword;
    JComboBox cbQuestion;
    JPanel signup;
    JButton btSubmit,btCancel;
    
    public signupPage() {
    
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
        SignupPanel();
        SignupComponents();
    }
    
    public void BackgroundImage(){
        
        backImage = new ImageIcon("signPage.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
     public void SignupPanel(){
        
        signup = new JPanel();
        signup.setLayout(null);
        signup.setSize(400,650);
        signup.setBackground(new Color(0,0,0,210));
        signup.setBounds(280,100,450,570);
        lbImage.add(signup);
     }
     public void SignupComponents(){
     
        Font f1 = new Font("Verdana",Font.BOLD,25);
        Font f2 = new Font("Segoe UI Symbol",Font.PLAIN,12);
        Font f3 = new Font("Consolas",Font.PLAIN,14);
        Font f4 = new Font("Arial",Font.PLAIN,16);
        Font f5 = new Font("Arial",Font.BOLD,16);
        
        signupTitle = new JLabel("Sign Up Form");
        signupTitle.setBounds(110,18,250,45);
        signupTitle.setFont(f1);
        signupTitle.setForeground(Color.WHITE);
        signup.add(signupTitle);
        
        lbUser = new JLabel("USERNAME");
        lbUser.setBounds(50,80,100,30);
        lbUser.setFont(f2);
        lbUser.setForeground(Color.WHITE);
        signup.add(lbUser);
        
        txtUser = new JTextField();
        txtUser.setBounds(50,110,320,35);
        txtUser.setFont(f3);
        txtUser.setOpaque(false);
        txtUser.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtUser.setForeground(Color.WHITE);
        signup.add(txtUser);
        
        lbPassword = new JLabel("PASSWORD");
        lbPassword.setBounds(50,150,100,30);
        lbPassword.setFont(f2);
        lbPassword.setForeground(Color.WHITE);
        signup.add(lbPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(50,180,320,35);
        txtPassword.setFont(f3);
        txtPassword.setOpaque(false);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtPassword.setForeground(Color.WHITE);
        signup.add(txtPassword);
        
        lbEmail = new JLabel("E-Mail");
        lbEmail.setBounds(50,220,100,30);
        lbEmail.setFont(f2);
        lbEmail.setForeground(Color.WHITE);
        signup.add(lbEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(50,250,320,35);
        txtEmail.setFont(f3);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtEmail.setForeground(Color.WHITE);
        signup.add(txtEmail);
        
        lbQuestion = new JLabel("Secret Question");
        lbQuestion.setBounds(50,290,150,30);
        lbQuestion.setFont(f2);
        lbQuestion.setForeground(Color.WHITE);
        signup.add(lbQuestion);
        
        String que[] = {"please,select question","Favorite book?","Hometown?","Nickname?","School name?","Favorite player?"};
        cbQuestion = new JComboBox(que);
        cbQuestion.setBounds(50,320,320,35);
        cbQuestion.setFont(f3);
        cbQuestion.setOpaque(false);
        cbQuestion.setForeground(Color.WHITE);
        cbQuestion.setBackground(new Color(0,0,0,210));
        signup.add(cbQuestion);
        
        lbAnswer = new JLabel("Answer");
        lbAnswer.setBounds(50,360,100,30);
        lbAnswer.setFont(f2);
        lbAnswer.setForeground(Color.WHITE);
        signup.add(lbAnswer);
        
        txtAnswer = new JTextField();
        txtAnswer.setBounds(50,390,320,35);
        txtAnswer.setFont(f3);
        txtAnswer.setOpaque(false);
        txtAnswer.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.WHITE));
        txtAnswer.setForeground(Color.WHITE);
        txtAnswer.setBackground(new Color(255,175,125,210));
        signup.add(txtAnswer);
        
        btSubmit = new JButton("Submit");
        btSubmit.setBounds(90,450,100,35);
        btSubmit.setBackground(Color.BLACK);
        btSubmit.setForeground(Color.WHITE);
        btSubmit.setFont(f5);
        signup.add(btSubmit);
        btSubmit.addActionListener(this);
     
        btCancel = new JButton("Cancel");
        btCancel.setBounds(240,450,100,35);
        btCancel.setBackground(Color.BLACK);
        btCancel.setForeground(Color.WHITE);
        btCancel.setFont(f5);
        signup.add(btCancel);
        btCancel.addActionListener(this);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btSubmit){
        
            InsertData();
        }
        
        if(e.getSource() == btCancel){
            loginPage ls = new loginPage();
            ls.setSize(1000,750);
            ls.setLocation(500, 200);
            ls.setVisible(true);
        }
    }
    public void InsertData(){
    
        String sqlQ = "insert into userdetails values (default,?,?,?,?,?,?)";      // Null for Auto increment column
        try{
           
            PreparedStatement pst = con.prepareStatement(sqlQ);
            
            pst.setString(1,txtUser.getText());
            pst.setString(2,String.valueOf(txtPassword.getPassword()));
            pst.setString(3,txtEmail.getText());
            pst.setString(4,cbQuestion.getSelectedItem().toString());
            pst.setString(5,txtAnswer.getText());
            pst.setInt(6,2);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sign up successful");
            con.close();
            cancelData();
        
        }
        catch(Exception e){
        
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void cancelData(){
        
        txtUser.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        cbQuestion.setSelectedIndex(0);
        txtAnswer.setText("");
    }
    
    
    public static void main(String[] args) {
        
       signupPage ap = new signupPage();
//        ap.setSize(1000,750);
//        ap.setLocation(500,200);
//        ap.setVisible(true);
    }
}

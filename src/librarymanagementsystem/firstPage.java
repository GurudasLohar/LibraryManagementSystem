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

public class firstPage extends JFrame{
  
    ImageIcon backImage;
    JLabel lbImage,lbUser,lbUserName;
    JPanel login,menu;
    JLabel lbIssImg,lbIss,lbRetImg,lbRet,lbSrImg,lbSr;
    JLabel lbAcc,lbOut;
    JTextField txtUser;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    String tempId,tempName;
       
    public firstPage() {
    
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
        UserPanel();
        MenuPanel();        
        PageComponents();
        MenuComp();
        
    }

    public void BackgroundImage(){
        
        backImage = new ImageIcon("library2.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void UserPanel(){
    
        login = new JPanel();
        login.setLayout(null);
        login.setSize(550,400);
        login.setBackground(new Color(40,30,35,220));
        login.setBounds(230,220,550,400);
        lbImage.add(login);
    }
     public void MenuPanel(){
        
        menu = new JPanel();
        menu.setLayout(null);
        menu.setSize(1000,70);
        menu.setBackground(new Color(40,30,35,245));
        menu.setBounds(0,50,1000,70);
        lbImage.add(menu);
    }
     public void PageComponents(){
     
        Font f1 = new Font("Verdana",Font.BOLD,30);
        Font f2 = new Font("Segoe UI Historic",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.PLAIN,16);
        
        ImageIcon serBook = new ImageIcon("s1.PNG");
        lbSrImg= new JLabel(serBook);
        lbSrImg.setBounds(100,150,serBook.getIconWidth(),serBook.getIconHeight());
        login.add(lbSrImg);
        lbSrImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    searchPage sp = new searchPage();
                    
                }
        });
        
        lbSr = new JLabel("Search Book");
        lbSr.setBounds(100,245,90,13);
        lbSr.setFont(f2);
        lbSr.setForeground(Color.WHITE);
        login.add(lbSr);
        
        ImageIcon addBook = new ImageIcon("i1.PNG");
        lbIssImg= new JLabel(addBook);
        lbIssImg.setBounds(250,150,addBook.getIconWidth(),addBook.getIconHeight());
        login.add(lbIssImg);
        lbIssImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    issuePage ip = new issuePage();
                    
                }
        });
        
        lbIss = new JLabel("Issue Book");
        lbIss.setBounds(250,245,90,13);
        lbIss.setFont(f2);
        lbIss.setForeground(Color.WHITE);
        login.add(lbIss);
        
        ImageIcon retBook = new ImageIcon("r1.PNG");
        lbRetImg= new JLabel(retBook);
        lbRetImg.setBounds(400,150,retBook.getIconWidth(),retBook.getIconHeight());
        login.add(lbRetImg);
        lbRetImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    returnPage rp = new returnPage();
                    
                }
        });
        
        lbRet = new JLabel("Return Book");
        lbRet.setBounds(400,245,90,13);
        lbRet.setFont(f2);
        lbRet.setForeground(Color.WHITE);
        login.add(lbRet);
    }
    public void MenuComp(){
    
        Font mf1 = new Font("Segoe UI Historic",Font.BOLD,16);
        Font mf2 = new Font("Consolas",Font.BOLD,14);
        Font mf3 = new Font("Segoe UI",Font.BOLD,17);        
 
        lbUser = new JLabel("Hiii and Welcome");
        lbUser.setBounds(30,20,150,25);
        lbUser.setFont(mf3);
        lbUser.setForeground(Color.WHITE);
        menu.add(lbUser);
        
        lbUserName = new JLabel();
        lbUserName.setBounds(180,20,100,25);
        lbUserName.setFont(mf3);
        lbUserName.setForeground(Color.WHITE);
        menu.add(lbUserName);
        
        lbAcc = new JLabel("My Account");
        lbAcc.setBounds(720,20,120,25);
        lbAcc.setFont(mf3);
        lbAcc.setForeground(Color.WHITE);
        menu.add(lbAcc);
        lbAcc.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                   MyAccount();
                    
                }
        });
        
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
    
    public void Search(String username){
        
        String sql1 = "select UserId,UserName from userdetails where userName = '"+username+"'";
        
        try{
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                tempId = rs.getString(1);
                tempName = rs.getString(2);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void MyAccount(){
    
       accountPage ap = new accountPage();
       ap.lbUserId1.setText(tempId);
       ap.lbUserName1.setText(tempName);
       ap.BookTable(tempId);
    
    } 
    
    public static void main(String[] args) {
        firstPage fp1 = new firstPage();
//                fp1.setSize(1000,750);
//        fp1.setLocation(500,200);
//        fp1.setVisible(true);
    }
}

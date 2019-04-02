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
public class adminPage extends JFrame implements ActionListener{

    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbAddImage,lbAdd,lbSearchImg,lbSearch,lbUpdateImg,lbUpdate,lbLogout,lblUserRole;
    JLabel lbImage,lbStdAddImg,lbStdAdd,lbStdReImg,lbStdRe,lbStatImg,lbStat;
    JLabel lbIssImg,lbIss,lbRetImg,lbRet;
    JButton btLogout;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public adminPage() {
    
        super("Reader' Circle");
        setLayout(null);
        
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
                
        setSize(1000,750);
        setLocation(500, 200);
        setVisible(true);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BackgroundImage();
        AdminPanel();
        AdminComponents();
        
        con = jdbcConnection.jdbcConnect();
    
    }
     public void BackgroundImage(){
        
        backImage = new ImageIcon("adminPage.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
     public void AdminPanel(){
    
        login = new JPanel();
        login.setLayout(null);
        login.setSize(470,550);
        login.setBackground(new Color(100,55,40,180));
        login.setBounds(460,80,470,550);
        lbImage.add(login);
    
    }
    public void AdminComponents(){
    
        Font f1 = new Font("Verdana",Font.BOLD,30);
        Font f2 = new Font("Segoe UI Historic",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.PLAIN,16);
        
        ImageIcon addBook = new ImageIcon("a.PNG");
        lbAddImage = new JLabel(addBook);
        lbAddImage.setBounds(50,50,addBook.getIconWidth(),addBook.getIconHeight());
        login.add(lbAddImage);
        lbAddImage.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    bookDetails bd = new bookDetails();
                }
        });
        
        lbAdd = new JLabel("Add Book");
        lbAdd.setBounds(50,135,80,13);
        lbAdd.setFont(f2);
        lbAdd.setForeground(Color.WHITE);
        login.add(lbAdd);
        
        ImageIcon searchBook = new ImageIcon("s.png");
        lbSearchImg = new JLabel(searchBook);
        lbSearchImg.setBounds(200,50,searchBook.getIconWidth(),searchBook.getIconHeight());
        login.add(lbSearchImg);
        lbSearchImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    searchPageNew sp = new searchPageNew();
                    
                }
        });
        
        lbSearch = new JLabel("Search Book");
        lbSearch.setBounds(195,135,100,13);
        lbSearch.setFont(f2);
        lbSearch.setForeground(Color.WHITE);
        login.add(lbSearch);
        
        
        ImageIcon updateBook = new ImageIcon("u.png");
        lbUpdateImg = new JLabel(updateBook);
        lbUpdateImg.setBounds(350,50,updateBook.getIconWidth(),updateBook.getIconHeight());
        login.add(lbUpdateImg);
        lbUpdateImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    updatePage up = new updatePage();
                }
        });
        
        lbUpdate = new JLabel("Update Book");
        lbUpdate.setBounds(350,135,100,13);
        lbUpdate.setFont(f2);
        lbUpdate.setForeground(Color.WHITE);
        login.add(lbUpdate);
        
        ImageIcon issBook = new ImageIcon("i.png");
        lbIssImg = new JLabel(issBook);
        lbIssImg.setBounds(50,190,issBook.getIconWidth(),issBook.getIconHeight());
        login.add(lbIssImg);
        lbIssImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    issuePage ip = new issuePage();
                }
        });
        
        lbIss = new JLabel("Issue Book");
        lbIss.setBounds(50,275,100,13);
        lbIss.setFont(f2);
        lbIss.setForeground(Color.WHITE);
        login.add(lbIss);
        
        ImageIcon retBook = new ImageIcon("r.png");
        lbRetImg = new JLabel(retBook);
        lbRetImg.setBounds(200,190,retBook.getIconWidth(),retBook.getIconHeight());
        login.add(lbRetImg);
        lbRetImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                   
                   returnPage rp = new returnPage();
                }
        });
        
        lbRet = new JLabel("Return Book");
        lbRet.setBounds(200,275,100,13);
        lbRet.setFont(f2);
        lbRet.setForeground(Color.WHITE);
        login.add(lbRet);
        
        ImageIcon statBook = new ImageIcon("st.png");
        lbStatImg = new JLabel(statBook);
        lbStatImg.setBounds(350,190,statBook.getIconWidth(),statBook.getIconHeight());
        login.add(lbStatImg);
        lbStatImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                     detailsPage dp = new detailsPage();
                }
        });
        
        lbStat = new JLabel("Issue Details");
        lbStat.setBounds(350,275,100,13);
        lbStat.setFont(f2);
        lbStat.setForeground(Color.WHITE);
        login.add(lbStat);
        
        ImageIcon stdAdd = new ImageIcon("ad.png");
        lbStdAddImg = new JLabel(stdAdd);
        lbStdAddImg.setBounds(130,375,stdAdd.getIconWidth(),stdAdd.getIconHeight());
        login.add(lbStdAddImg);
        lbStdAddImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    signupPage sp = new signupPage();
                }
        });
        
        
        lbStdAdd = new JLabel("Add Student");
        lbStdAdd.setBounds(130,460,100,13);
        lbStdAdd.setFont(f2);
        lbStdAdd.setForeground(Color.WHITE);
        login.add(lbStdAdd);
        
        ImageIcon stdRe = new ImageIcon("re.png");
        lbStdReImg = new JLabel(stdRe);
        lbStdReImg.setBounds(280,375,stdRe.getIconWidth(),stdRe.getIconHeight());
        login.add(lbStdReImg);
        lbStdReImg.addMouseListener(new MouseAdapter()
        {
                @Override
                public void mousePressed(MouseEvent e){
                    
                    removePage rp = new removePage();
                }
        });
        
        lbStdRe = new JLabel("Remove Student");
        lbStdRe.setBounds(265,460,130,13);
        lbStdRe.setFont(f2);
        lbStdRe.setForeground(Color.WHITE);
        login.add(lbStdRe);
        
        btLogout = new JButton("Logout");
        btLogout.setBounds(190,510,100,30);
        btLogout.setBackground(new Color(255,128,64,250));
        btLogout.setForeground(Color.WHITE);
        btLogout.setFont(f3);
        login.add(btLogout);
        btLogout.addActionListener(this);
        
        
        lblUserRole = new JLabel();
        lblUserRole.setFont(f2);
        lblUserRole.setForeground(Color.WHITE);
        lblUserRole.setBounds(10,10,100,20);
        login.add(lblUserRole);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btLogout){
           loginPage ls  = new loginPage();
            ls.setSize(1000,750);
            ls.setLocation(500, 200);
            ls.setVisible(true);
           
       }
    }
    public static void main(String[] args) {
        adminPage ap = new adminPage();
//        
//        ap.setSize(1000,750);
//        ap.setLocation(500, 200);
//        ap.setVisible(true);
    }
    
}

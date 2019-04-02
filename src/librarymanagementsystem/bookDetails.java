/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author LENOVO
 */
public class bookDetails extends JFrame implements ActionListener{
 
    ImageIcon backImage,loginImage;
    JPanel login;
    JLabel lbImage;
    JLabel lbBookid,lbBookname,lbPurdate,lbTitle;
    JLabel lbAuthor,lbQuan,lbPrice,lbRack;
    JTextField txtBookid,txtBookname,txtAuthor;
    JTextField txtQun,txtPrice,txtRack;
    JButton btSave,btClear;
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public bookDetails() {
        
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
        BookPanel();
        BookComponents();
        
        Random();
             
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("BookDetails.jpeg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void BookPanel(){
    
        login = new JPanel();
        login.setLayout(null);
        login.setSize(800,550);
        login.setBackground(new Color(0,64,105,195));
        login.setBounds(100,100,800,550);
        lbImage.add(login);
    }
    public void BookComponents(){
    
        Font f1 = new Font("Verdana",Font.BOLD,30);
        Font f2 = new Font("Segoe UI Historic",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.PLAIN,14);
        Font f4 = new Font("Segoe UI",Font.PLAIN,13);
        Font f5 = new Font("Yu Gothic UI",Font.BOLD,18);
        Font f6 = new Font("Segoe UI",Font.PLAIN,14);
        Font f7 = new Font("Yu Gothic UI",Font.BOLD,16);
        
        lbTitle = new JLabel("Book Information");
        lbTitle.setBounds(250,50,350,45);
        lbTitle.setFont(f1);
        lbTitle.setForeground(Color.WHITE);
        login.add(lbTitle);
        
        
        lbBookid = new JLabel("Book Id");
        lbBookid.setBounds(50,150,100,20);
        lbBookid.setFont(f2);
        lbBookid.setForeground(Color.WHITE);
        login.add(lbBookid);
        
        txtBookid = new JTextField();
        txtBookid.setBounds(50,180,300,35);
        txtBookid.setFont(f3);
        txtBookid.setOpaque(false);
        txtBookid.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtBookid.setForeground(Color.WHITE);
        txtBookid.setBackground(new Color(0,0,0,210));
        login.add(txtBookid);
        
        lbBookname = new JLabel("Book Name");
        lbBookname.setBounds(50,250,100,20);
        lbBookname.setFont(f2);
        lbBookname.setForeground(Color.WHITE);
        login.add(lbBookname);
        
        txtBookname = new JTextField();
        txtBookname.setBounds(50,280,300,35);
        txtBookname.setFont(f3);
        txtBookname.setOpaque(false);
        txtBookname.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtBookname.setForeground(Color.WHITE);
        txtBookname.setBackground(new Color(0,0,0,210));
        login.add(txtBookname);
    
        lbAuthor = new JLabel("Book Author");
        lbAuthor.setBounds(50,350,100,20);
        lbAuthor.setFont(f2);
        lbAuthor.setForeground(Color.WHITE);
        login.add(lbAuthor);
        
        txtAuthor = new JTextField();
        txtAuthor.setBounds(50,380,300,35);
        txtAuthor.setFont(f3);
        txtAuthor.setOpaque(false);
        txtAuthor.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtAuthor.setForeground(Color.WHITE);
        txtAuthor.setBackground(new Color(0,0,0,210));
        login.add(txtAuthor);
        
        lbQuan = new JLabel("Books Quantity");
        lbQuan.setBounds(450,150,130,20);
        lbQuan.setFont(f2);
        lbQuan.setForeground(Color.WHITE);
        login.add(lbQuan);
        
        txtQun = new JTextField();
        txtQun.setBounds(450,180,300,35);
        txtQun.setFont(f3);
        txtQun.setOpaque(false);
        txtQun.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtQun.setForeground(Color.WHITE);
        txtQun.setBackground(new Color(0,0,0,210));
        login.add(txtQun);
        
        lbPrice = new JLabel("Book Price");
        lbPrice.setBounds(450,250,100,20);
        lbPrice.setFont(f2);
        lbPrice.setForeground(Color.WHITE);
        login.add(lbPrice);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(450,280,300,35);
        txtPrice.setFont(f3);
        txtPrice.setOpaque(false);
        txtPrice.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtPrice.setForeground(Color.WHITE);
        txtPrice.setBackground(new Color(0,0,0,210));
        login.add(txtPrice);
        
        lbRack = new JLabel("Rack Number");
        lbRack.setBounds(450,350,100,20);
        lbRack.setFont(f2);
        lbRack.setForeground(Color.WHITE);
        login.add(lbRack);
        
        txtRack = new JTextField();
        txtRack.setBounds(450,380,300,35);
        txtRack.setFont(f3);
        txtRack.setOpaque(false);
        txtRack.setBorder(BorderFactory.createLineBorder(Color.RED));
        txtRack.setForeground(Color.WHITE);
        txtRack.setBackground(new Color(0,0,0,210));
        login.add(txtRack);
        
        btSave = new JButton("Save");
        btSave.setBackground(Color.RED);
        btSave.setForeground(Color.WHITE);
        btSave.setBounds(130,475,150,35);
        btSave.setFont(f5);
        login.add(btSave);
        btSave.addActionListener(this);
                
                
        btClear = new JButton("Clear");
        btClear.setBackground(Color.RED);
        btClear.setForeground(Color.WHITE);
        btClear.setBounds(530,475,150,35);
        btClear.setFont(f5);
        login.add(btClear);
        btClear.addActionListener(this);
    }
    public void Random(){
    
        Random rm = new Random();
        txtBookid.setText(""+rm.nextInt(1000+1));
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btSave){
                AddBook();
        }
        
        if(e.getSource() == btClear){
                Clear();
        
        }
    }
    public void AddBook(){
    
            String sqlQ = "insert into bookcatalog values (?,?,?,?,?,?)";
            try{
            
                PreparedStatement pst = con.prepareStatement(sqlQ);
                
                pst.setString(1,txtBookid.getText());
                pst.setString(2,txtBookname.getText());
                pst.setString(3,txtAuthor.getText());
                pst.setString(4,txtQun.getText());
                pst.setString(5,txtPrice.getText());
                pst.setString(6,txtRack.getText());
                
                pst.execute();
                JOptionPane.showMessageDialog(null,"Book added successfully");
                
                Clear();
                Random();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
    }
    public void Clear(){
    
        txtBookid.setText("");
        txtBookname.setText("");
        txtAuthor.setText("");
        txtQun.setText("");
        txtPrice.setText("");
        txtRack.setText("");
    }
    
    public static void main(String[] args) {
        
        bookDetails bd = new bookDetails();
        
    }    
}

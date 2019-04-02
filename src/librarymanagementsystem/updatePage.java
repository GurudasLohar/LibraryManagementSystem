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
public class updatePage extends JFrame implements ActionListener{
    
    ImageIcon backImage,loginImage;
    JPanel login,menu;
    JLabel lbImage,loginTitle;
    JLabel lbBookid,lbBookname,lbPurdate,lbTitle;
    JLabel lbAuthor,lbQuan,lbPrice,lbRack;
    JTextField txtBookid,txtBookname,txtAuthor;
    JTextField txtQun,txtPrice,txtRack;
    JButton btSave,btClear,btSearch;
   
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public updatePage() throws HeadlessException {
    
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
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("updatePage.jpeg");
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
        login.setBackground(new Color(255,255,205,220));
        login.setBounds(100,100,800,550);
        lbImage.add(login);
    }
    public void BookComponents(){
    
        Font f1 = new Font("Verdana",Font.BOLD,30);
        Font f2 = new Font("Segoe UI Historic",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.BOLD,14);
        Font f4 = new Font("Segoe UI",Font.PLAIN,13);
        Font f5 = new Font("Yu Gothic UI",Font.BOLD,18);
        Font f6 = new Font("Segoe UI",Font.PLAIN,14);
        Font f7 = new Font("Yu Gothic UI",Font.BOLD,16);
        
        lbTitle = new JLabel("Book Updation");
        lbTitle.setBounds(250,50,350,45);
        lbTitle.setFont(f1);
        lbTitle.setForeground(Color.BLACK);
        login.add(lbTitle);
        
        
        lbBookid = new JLabel("Book Id");
        lbBookid.setBounds(50,150,100,20);
        lbBookid.setFont(f2);
        lbBookid.setForeground(Color.BLACK);
        login.add(lbBookid);
        
        txtBookid = new JTextField();
        txtBookid.setBounds(50,180,300,35);
        txtBookid.setFont(f3);
        txtBookid.setOpaque(false);
        txtBookid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtBookid.setForeground(Color.BLACK);
        txtBookid.setBackground(new Color(0,0,0,210));
        login.add(txtBookid);
        
        ImageIcon serImg = new ImageIcon("ser.png");
        btSearch = new JButton(serImg);
        btSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btSearch.setBounds(370,180,30,30);
        login.add(btSearch);
        btSearch.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
            
                Search();
            }
        });
        
        
        lbBookname = new JLabel("Book Name");
        lbBookname.setBounds(50,250,100,20);
        lbBookname.setFont(f2);
        lbBookname.setForeground(Color.BLACK);
        login.add(lbBookname);
        
        txtBookname = new JTextField();
        txtBookname.setBounds(50,280,300,35);
        txtBookname.setFont(f3);
        txtBookname.setOpaque(false);
        txtBookname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtBookname.setForeground(Color.BLACK);
        txtBookname.setBackground(new Color(0,0,0,210));
        login.add(txtBookname);
    
        lbAuthor = new JLabel("Book Author");
        lbAuthor.setBounds(50,350,100,20);
        lbAuthor.setFont(f2);
        lbAuthor.setForeground(Color.BLACK);
        login.add(lbAuthor);
        
        txtAuthor = new JTextField();
        txtAuthor.setBounds(50,380,300,35);
        txtAuthor.setFont(f3);
        txtAuthor.setOpaque(false);
        txtAuthor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtAuthor.setForeground(Color.BLACK);
        txtAuthor.setBackground(new Color(0,0,0,210));
        login.add(txtAuthor);
        
        lbQuan = new JLabel("Books Quantity");
        lbQuan.setBounds(450,150,130,20);
        lbQuan.setFont(f2);
        lbQuan.setForeground(Color.BLACK);
        login.add(lbQuan);
        
        txtQun = new JTextField();
        txtQun.setBounds(450,180,300,35);
        txtQun.setFont(f3);
        txtQun.setOpaque(false);
        txtQun.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtQun.setForeground(Color.BLACK);
        txtQun.setBackground(new Color(0,0,0,210));
        login.add(txtQun);
        
        lbPrice = new JLabel("Book Price");
        lbPrice.setBounds(450,250,100,20);
        lbPrice.setFont(f2);
        lbPrice.setForeground(Color.BLACK);
        login.add(lbPrice);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(450,280,300,35);
        txtPrice.setFont(f3);
        txtPrice.setOpaque(false);
        txtPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtPrice.setForeground(Color.BLACK);
        txtPrice.setBackground(new Color(0,0,0,210));
        login.add(txtPrice);
        
        lbRack = new JLabel("Rack Number");
        lbRack.setBounds(450,350,100,20);
        lbRack.setFont(f2);
        lbRack.setForeground(Color.BLACK);
        login.add(lbRack);
        
        txtRack = new JTextField();
        txtRack.setBounds(450,380,300,35);
        txtRack.setFont(f3);
        txtRack.setOpaque(false);
        txtRack.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtRack.setForeground(Color.BLACK);
        txtRack.setBackground(new Color(0,0,0,210));
        login.add(txtRack);
        
        btSave = new JButton("Update");
        btSave.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btSave.setBackground(new Color(255,255,205,250));
        btSave.setForeground(Color.BLACK);
        btSave.setBounds(130,475,150,35);
        btSave.setFont(f5);
        login.add(btSave);
        btSave.addActionListener(this);
                
                
        btClear = new JButton("Clear");
        btClear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btClear.setBackground(new Color(255,255,205,250));
        btClear.setForeground(Color.BLACK);
        btClear.setBounds(530,475,150,35);
        btClear.setFont(f5);
        login.add(btClear);
        btClear.addActionListener(this);
    }
    public void Search(){
     
       String bookid = txtBookid.getText();
       String sql1 = "select BookTitle,BookAuthor,Quantity,Price,RackNumber from bookcatalog where BookId = '"+bookid+"'";
       try{
           pst = con.prepareStatement(sql1);
           rs = pst.executeQuery();
           while(rs.next()){
           
               txtBookname.setText(rs.getString(1));
               txtAuthor.setText(rs.getString(2));
               txtQun.setText(rs.getString(3));
               txtPrice.setText(rs.getString(4));
               txtRack.setText(rs.getString(5));
            }
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void BookUpdate(){
    
        String bookid = txtBookid.getText();
        String sql2 = "update bookcatalog set BookTitle=?,BookAuthor=?,Quantity=?,Price=?,RackNumber=? where BookId ='"+bookid+"'";
        try{
            pst = con.prepareStatement(sql2);
            
            pst.setString(1,txtBookname.getText());
            pst.setString(2,txtAuthor.getText());
            pst.setString(3,txtQun.getText());
            pst.setString(4,txtPrice.getText());
            pst.setString(5,txtRack.getText());

            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Book updated successfully");
            Clear();
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
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource() == btSave){
            BookUpdate();
        }
        if(e.getSource() == btClear){
            Clear();
        }
    }
    
    public static void main(String[] args) {
        updatePage up = new updatePage();
//            up.setSize(1000,750);
//            up.setLocation(500, 200);
//            up.setVisible(true);
    }

}

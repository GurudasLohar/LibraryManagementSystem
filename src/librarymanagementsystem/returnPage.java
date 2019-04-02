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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class returnPage extends JFrame implements ActionListener {
    
    ImageIcon backImage,loginImage;
    JPanel Issue,Issue1;
    JLabel lbImage;
    JLabel retTitle;
    JLabel lbUserid,lbUsername,lbBookId,lbIssDate,lbRetDate;
    JLabel lbFine1,lbFine2;
    JTextField txtUserid,txtUsername,txtBookId,txtIssDate,txtRetDate;
    JButton btSearch,btReturn,btSubmit,btClear;
    
    String IssDate,RetDate;
    Date iDate1;
    java.sql.Date DateRet,iDate;
    String stringFine;
    
    JTable tb;
    String [] colName = {"Book Id","Book Name"};
    
    Connection con;
    ResultSet rs,rs1,rs2;
    PreparedStatement pst,pst1,pst2;
    
    public returnPage(){
    
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
        DateCalculation();
        returnPanel1();
        returnComp();
    }
    public void BackgroundImage(){
        
        backImage = new ImageIcon("returnPage.png");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000,750,Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("",backImage,JLabel.CENTER);
        lbImage.setBounds(0,0,1000,750);
        add(lbImage);
    }
    public void returnPanel1(){
        
        Issue = new JPanel();
        Issue.setLayout(null);
        Issue.setSize(400,600);
        Issue.setBackground(new Color(220,210,240,190));
        Issue.setBounds(100,50,400,600);
        lbImage.add(Issue);
    }
    public void returnComp(){
    
        Font f1 = new Font("Verdana",Font.BOLD,25);
        Font f2 = new Font("Segoe UI Symbol",Font.BOLD,13);
        Font f3 = new Font("Consolas",Font.BOLD,14);
        Font f4 = new Font("Times New Roman",Font.BOLD,22);
        Font f5 = new Font("Segoe UI",Font.BOLD,16);
        
        
        retTitle = new JLabel("Book to be Return");
        retTitle.setBounds(30,18,280,45);
        retTitle.setFont(f1);
        retTitle.setForeground(Color.BLACK);
        Issue.add(retTitle);
        
        lbUserid = new JLabel("User Id");
        lbUserid.setBounds(30,100,60,30);
        lbUserid.setFont(f2);
        lbUserid.setForeground(Color.BLACK);
        Issue.add(lbUserid);
        
        txtUserid = new JTextField();
        txtUserid.setBounds(110,100,150,30);
        txtUserid.setFont(f3);
        txtUserid.setOpaque(false);
        txtUserid.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtUserid.setForeground(Color.BLACK);
        Issue.add(txtUserid);
        
        ImageIcon serImg = new ImageIcon("ser.png");
        btSearch = new JButton(serImg);
        btSearch.setBounds(290,100,30,30);
        Issue.add(btSearch);
        btSearch.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
            
                    SearchUserName();
                    BookTable();
            }
        });
        
        lbUsername = new JLabel("User Name");
        lbUsername.setBounds(30,175,80,30);
        lbUsername.setFont(f2);
        lbUsername.setForeground(Color.BLACK);
        Issue.add(lbUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(110,175,150,30);
        txtUsername.setFont(f3);
        txtUsername.setOpaque(false);
        txtUsername.setEditable(false);
        txtUsername.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtUsername.setForeground(Color.BLACK);
        Issue.add(txtUsername);
        
        lbBookId = new JLabel("Book Id");
        lbBookId.setBounds(30,250,80,30);
        lbBookId.setFont(f2);
        lbBookId.setForeground(Color.BLACK);
        Issue.add(lbBookId);
        
        txtBookId = new JTextField();
        txtBookId.setBounds(110,250,150,30);
        txtBookId.setFont(f3);
        txtBookId.setOpaque(false);
        txtBookId.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtBookId.setForeground(Color.BLACK);
        Issue.add(txtBookId);
        
        ImageIcon retImg = new ImageIcon("ret.png");
        btReturn = new JButton(retImg);
        btReturn.setBounds(290,250,30,30);
        Issue.add(btReturn);
        btReturn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
            
                SearchDate(); 
                FineCalculation();
            }
        });
        
        lbIssDate = new JLabel("Issue Date");
        lbIssDate.setBounds(30,325,80,30);
        lbIssDate.setFont(f2);
        lbIssDate.setForeground(Color.BLACK);
        Issue.add(lbIssDate);
        
        txtIssDate = new JTextField();
        txtIssDate.setBounds(110,325,150,30);
        txtIssDate.setFont(f3);
        txtIssDate.setOpaque(false);
        txtIssDate.setEditable(false);
        txtIssDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtIssDate.setForeground(Color.BLACK);
        Issue.add(txtIssDate);
        
        lbRetDate = new JLabel("Return Date");
        lbRetDate.setBounds(30,400,80,30);
        lbRetDate.setFont(f2);
        lbRetDate.setForeground(Color.BLACK);
        Issue.add(lbRetDate);
        
        txtRetDate = new JTextField();
        txtRetDate.setBounds(110,400,150,30);
        txtRetDate.setFont(f3);
        txtRetDate.setOpaque(false);
        txtRetDate.setEditable(false);
        txtRetDate.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        txtRetDate.setForeground(Color.BLACK);
        Issue.add(txtRetDate);
        txtRetDate.setText(RetDate);
     
        lbFine1 = new JLabel();
        lbFine1.setBounds(100,470,150,30);
        lbFine1.setFont(f4);
        lbFine1.setForeground(Color.RED);
        //Issue.add(lbFine1);
        
        lbFine2 = new JLabel();
        lbFine2.setBounds(250,470,30,30);
        lbFine2.setFont(f4);
        lbFine2.setForeground(Color.RED);
        //Issue.add(lbFine2);
        
        btSubmit = new JButton("Confirm");
        btSubmit.setBounds(50,550,100,35);
        btSubmit.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        btSubmit.setBackground(new Color(200,191,240,190));
        btSubmit.setForeground(Color.BLACK);
        btSubmit.setFont(f5);
        Issue.add(btSubmit);
        btSubmit.addActionListener(this);
        
        btClear = new JButton("Clear");
        btClear.setBounds(200,550,100,35);
        btClear.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        btClear.setBackground(new Color(200,191,240,190));
        btClear.setForeground(Color.BLACK);
        btClear.setFont(f5);
        Issue.add(btClear);
        btClear.addActionListener(this);
    
    }
    public void SearchUserName(){
    
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
    
    public void BookTable(){

       DefaultTableModel mod = new DefaultTableModel();
       mod.setColumnIdentifiers(colName);
       tb = new JTable();
       tb.setModel(mod);
       tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       tb.setFillsViewportHeight(true);
       tb.setBackground(new Color(220,210,240,190));
       JScrollPane js = new JScrollPane(tb);
      
       String bId = "";
       String bName = "";
       String temp;
       
       String userid = txtUserid.getText();
       String sts = "Issued";
       String sql2 = "select BookId from issuedetails where UserId = '"+userid+"' and status = '"+sts+"'";
       
       try{
           pst = con.prepareStatement(sql2);
           rs = pst.executeQuery();
           
           while(rs.next()){
              
              bId = rs.getString(1);
              
              temp = rs.getString(1);
              String sqlQ = "select BookTitle from bookcatalog where BookId = '"+temp+"'";
              
              pst1 = con.prepareStatement(sqlQ);
              rs1 = pst1.executeQuery();
              
              while(rs1.next()){
                  bName = rs1.getString(1);
                  mod.addRow(new Object[]{bId,bName});
              }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        js.setBounds(600,100,300,100);
        lbImage.add(js);
    }
    public void SearchDate(){
        
       String bookid = txtBookId.getText();
       String sql2 = "select IssueDate from issuedetails where BookId = '"+bookid+"'";
       try{
           pst = con.prepareStatement(sql2);
           rs = pst.executeQuery();
           while(rs.next()){ 
                
                iDate = rs.getDate(1);
                txtIssDate.setText(rs.getString(1));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void DateCalculation() {
               
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar cal = Calendar.getInstance();
        RetDate = sdf.format(cal.getTime());
        
        java.util.Date rDate = cal.getTime();
        DateRet  = new java.sql.Date(rDate.getTime());
        //System.out.println("RetDate "+DateRet);
    }
    
    public void FineCalculation(){
        
        long diff = DateRet.getTime() - iDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        //System.out.println("Days Difference is"+diffDays);
        
        if(diffDays <= 15){
            Issue.add(lbFine1);
            lbFine1.setText("Your Fine is  ₹");
            Issue.add(lbFine2);
            stringFine = "0";
            lbFine2.setText(stringFine);
        }
        else{
            
            long fineDays = diffDays - 15;      
            long longFine = fineDays*5;
            stringFine = String.valueOf(longFine);
            
            Issue.add(lbFine1);
            Issue.add(lbFine2);
            lbFine2.setText(stringFine);
        }
    }
    public void StatusUpdate(){
    
        String uId = txtUserid.getText();
        String sts = txtBookId.getText();
        String sql = "UPDATE `issuedetails` SET `Status`='Returned',`Fine`= ? WHERE `BookId`='"+sts+"' AND`UserId`='"+uId+"'";
        
        int fineValue = Integer.parseInt(stringFine);
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,fineValue);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Book returned successfully");
            UpdateQuan();
            cancelData();
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void UpdateQuan(){
    
        String bookId = txtBookId.getText();

        String sql4 = "select Quantity from bookcatalog where BookID = '"+bookId+"'";
        
        int temp;
        
        try{
            pst = con.prepareStatement(sql4);
            rs = pst.executeQuery();
            
            while(rs.next()){
                temp = rs.getInt(1);
                
                temp = temp + 1;
                String sql5 = "update bookcatalog set Quantity = "+temp+" where BookId = '"+bookId+"'";
                
                pst1 = con.prepareStatement(sql5);
                pst1.executeUpdate();
            }
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void cancelData(){
        
        txtUserid.setText("");
        txtUsername.setText("");
        txtBookId.setText("");
        txtIssDate.setText("");
        txtRetDate.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSubmit){
            StatusUpdate();
        }
        if(e.getSource() == btClear){
            cancelData();
        }
    }
    
    public static void main(String[] args){
        
        returnPage rp = new returnPage();

//            rp.setSize(1000,750);
//            rp.setLocation(500, 200);
//            rp.setVisible(true);
    }
}

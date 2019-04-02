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
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author LENOVO
 */
public class issuePage extends JFrame implements ActionListener {

    ImageIcon backImage, loginImage;
    JPanel Issue;
    JLabel lbImage;
    JLabel IssTitle;
    JLabel lbBookid, lbBookname, lbStdId, lbIssDate, lbRetDate;
    JTextField txtBookid, txtBookname, txtStdId, txtIssDate, txtRetDate;
    JTextField txt;
    JButton btSearch, btConfirm, btClear;
    String IssDate, RetDate;

    java.sql.Date DateRet, DateIss;

    Connection con;
    ResultSet rs, rs1;
    PreparedStatement pst, pst1;

    public issuePage() {

        super("Reader' Circle");
        con = jdbcConnection.jdbcConnect();        
        
        ImageIcon img = new ImageIcon("icon2.png");
        setIconImage(img.getImage());
        
        setLayout(null);
        setSize(1000,750);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CalculateDate();
        BackgroundImage();
        IssuePanel();
        IssueComponents();

    }

    public void BackgroundImage() {

        backImage = new ImageIcon("issuePage.jpg");
        Image img = backImage.getImage();
        Image tempImg = img.getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
        backImage = new ImageIcon(tempImg);
        lbImage = new JLabel("", backImage, JLabel.CENTER);
        lbImage.setBounds(0, 0, 1000, 750);
        add(lbImage);
    }

    public void IssuePanel() {

        Issue = new JPanel();
        Issue.setLayout(null);
        Issue.setSize(350, 550);
        Issue.setBackground(new Color(239, 228, 158, 200));
        Issue.setBounds(100, 100, 350, 550);
        lbImage.add(Issue);
    }

    public void IssueComponents() {

        Font f1 = new Font("Verdana", Font.BOLD, 25);
        Font f2 = new Font("Segoe UI Symbol", Font.BOLD, 13);
        Font f3 = new Font("Consolas", Font.BOLD, 14);
        Font f4 = new Font("Arial", Font.PLAIN, 16);
        Font f5 = new Font("Segoe UI", Font.BOLD, 16);

        IssTitle = new JLabel("Book to be Issue");
        IssTitle.setBounds(50, 18, 250, 45);
        IssTitle.setFont(f1);
        IssTitle.setForeground(Color.BLACK);
        Issue.add(IssTitle);

        lbBookid = new JLabel("Book Id");
        lbBookid.setBounds(30, 100, 60, 30);
        lbBookid.setFont(f2);
        lbBookid.setForeground(Color.BLACK);
        Issue.add(lbBookid);

        txtBookid = new JTextField();
        txtBookid.setBounds(120, 100, 150, 30);
        txtBookid.setFont(f3);
        txtBookid.setOpaque(false);
        txtBookid.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtBookid.setForeground(Color.BLACK);
        Issue.add(txtBookid);

        ImageIcon serImg = new ImageIcon("ser.png");
        btSearch = new JButton(serImg);
        btSearch.setBounds(290, 100, 30, 30);
        Issue.add(btSearch);
        btSearch.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                Search();
            }
        });

        lbBookname = new JLabel("Book Name");
        lbBookname.setBounds(30, 175, 80, 30);
        lbBookname.setFont(f2);
        lbBookname.setForeground(Color.BLACK);
        Issue.add(lbBookname);

        txtBookname = new JTextField();
        txtBookname.setBounds(120, 175, 150, 30);
        txtBookname.setFont(f3);
        txtBookname.setOpaque(false);
        txtBookname.setEditable(false);
        txtBookname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtBookname.setForeground(Color.BLACK);
        Issue.add(txtBookname);

        lbStdId = new JLabel("User Id");
        lbStdId.setBounds(30, 250, 80, 30);
        lbStdId.setFont(f2);
        lbStdId.setForeground(Color.BLACK);
        Issue.add(lbStdId);

        txtStdId = new JTextField();
        txtStdId.setBounds(120, 250, 150, 30);
        txtStdId.setFont(f3);
        txtStdId.setOpaque(false);
        txtStdId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtStdId.setForeground(Color.BLACK);
        Issue.add(txtStdId);

        lbIssDate = new JLabel("Issue Date");
        lbIssDate.setBounds(30, 325, 80, 30);
        lbIssDate.setFont(f2);
        lbIssDate.setForeground(Color.BLACK);
        Issue.add(lbIssDate);

        txtIssDate = new JTextField();
        txtIssDate.setBounds(120, 325, 150, 30);
        txtIssDate.setFont(f3);
        txtIssDate.setOpaque(false);
        txtIssDate.setEditable(false);
        txtIssDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtIssDate.setForeground(Color.BLACK);
        Issue.add(txtIssDate);
        txtIssDate.setText(IssDate);

        lbRetDate = new JLabel("Return Date");
        lbRetDate.setBounds(30, 400, 80, 30);
        lbRetDate.setFont(f2);
        lbRetDate.setForeground(Color.BLACK);
        Issue.add(lbRetDate);

        txtRetDate = new JTextField();
        txtRetDate.setBounds(120, 400, 150, 30);
        txtRetDate.setFont(f3);
        txtRetDate.setOpaque(false);
        txtRetDate.setEditable(false);
        txtRetDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtRetDate.setForeground(Color.BLACK);
        Issue.add(txtRetDate);
        txtRetDate.setText(RetDate);

        btConfirm = new JButton("Confirm");
        btConfirm.setBounds(50, 475, 100, 35);
        btConfirm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        btConfirm.setBackground(new Color(239, 228, 158, 225));
        btConfirm.setForeground(Color.BLACK);
        btConfirm.setFont(f5);
        Issue.add(btConfirm);
        btConfirm.addActionListener(this);

        btClear = new JButton("Clear");
        btClear.setBounds(200, 475, 100, 35);
        btClear.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        btClear.setBackground(new Color(239, 228, 158, 225));
        btClear.setForeground(Color.BLACK);
        btClear.setFont(f5);
        Issue.add(btClear);
        btClear.addActionListener(this);

    }

    public void Search() {

        String SearchId = txtBookid.getText();
        String sql = "select BookTitle from bookcatalog where BookId = '" + SearchId + "'";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                txtBookname.setText(rs.getString(1));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void Confirm() {

        String sql = "insert into issuedetails(BookId,UserId,IssueDate,ReturnDate,Status) values (?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, txtBookid.getText());
            pst.setString(2, txtStdId.getText());
            pst.setDate(3, DateIss);
            pst.setDate(4, DateRet);
            pst.setString(5, "Issued");
            pst.execute();
            JOptionPane.showMessageDialog(null, "Book Issued");
            UpdateQuan();
            cancelData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cancelData() {

        txtBookid.setText("");
        txtBookname.setText("");
        txtStdId.setText("");
    }

    public void CalculateDate() {

        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();
        IssDate = sdf.format(cal.getTime());        /// To set Date in JTextfield

        java.util.Date Idate = cal.getTime();
        DateIss = new java.sql.Date(Idate.getTime());       /// To set Date in Database 

        cal.add(Calendar.DATE, 15);
        RetDate = sdf.format(cal.getTime());

        java.util.Date Rdate = cal.getTime();
        DateRet = new java.sql.Date(Rdate.getTime());

    }

    public void UpdateQuan() {

        String bookId = txtBookid.getText();
        String sql4 = "select Quantity from bookcatalog where BookID = '" + bookId + "'";

        int temp;

        try {
            pst = con.prepareStatement(sql4);
            rs = pst.executeQuery();

            while (rs.next()) {
                temp = rs.getInt(1);

                temp = temp - 1;
                String sql5 = "update bookcatalog set Quantity = " + temp + " where BookId = '" + bookId + "'";

                pst1 = con.prepareStatement(sql5);
                pst1.executeUpdate();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btConfirm) {
            Confirm();

        }
        if (e.getSource() == btClear) {

            cancelData();
        }

    }

    public static void main(String[] args) {

        issuePage ip = new issuePage();
//            ip.setSize(1000, 750);
//            ip.setLocation(500, 200);
//            ip.setVisible(true);
    }

}

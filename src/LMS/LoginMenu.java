package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.sql.ResultSet;


public class LoginMenu extends JFrame implements ActionListener {

    JFrame frame=new JFrame();
    Ellipse2D a=new Ellipse2D.Double(0,0,500,250);


    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;
    AppAccount loggedinStaff;

    JButton Members;
    JButton Books;
    JButton Account;
    JButton Issue;
    JButton Wifi;
    JButton Report;
    JButton Complaint;

    JButton returnbutton;
    LoginMenu()
    {
        loggedinStaff=new AppAccount();

        Members= new JButton();
        Members.setBounds(250,20,500,50);

        Members.setText("Manage Library Members");
        Members.setFont(new Font("Segoe Print",Font.BOLD,30));
        Members.setBackground(new Color(0x234A59));
        Members.setForeground(new Color(0xffffff));
        Members.addActionListener(this);


        Books= new JButton();
        Books.setBounds(250,80,500,50);
        Books.setText("Search and Manage Books");
        Books.setFont(new Font("Segoe Print",Font.BOLD,30));
        Books.setBackground(new Color(0x234A59));
        Books.setForeground(new Color(0xffffff));
        Books.addActionListener(this);

        Account= new JButton();
        Account.setBounds(250,140,500,50);
        Account.setText("Edit Accounts");
        Account.setFont(new Font("Segoe Print",Font.BOLD,30));
        Account.setBackground(new Color(0x234A59));
        Account.setForeground(new Color(0xffffff));
        Account.addActionListener(this);

        Issue= new JButton();
        Issue.setBounds(250,200,500,50);
        Issue.setText("Issue A book To member");
        Issue.setFont(new Font("Segoe Print",Font.BOLD,30));
        Issue.setBackground(new Color(0x234A59));
        Issue.setForeground(new Color(0xffffff));
        Issue.addActionListener(this);

        Wifi= new JButton();
        Wifi.setBounds(250,260,500,50);
        Wifi.setText("Manage Wifi Functionalities ");
        Wifi.setFont(new Font("Segoe Print",Font.BOLD,30));
        Wifi.setBackground(new Color(0x234A59));
        Wifi.setForeground(new Color(0xffffff));
        Wifi.addActionListener(this);

        Report= new JButton();
        Report.setBounds(250,320,500,50);
        Report.setText("Application Report ");
        Report.setFont(new Font("Segoe Print",Font.BOLD,30));
        Report.setBackground(new Color(0x234A59));
        Report.setForeground(new Color(0xffffff));
        Report.addActionListener(this);

        Complaint= new JButton();
        Complaint.setBounds(250,380,500,50);
        Complaint.setText("Complaint Details ");
        Complaint.setFont(new Font("Segoe Print",Font.BOLD,30));
        Complaint.setBackground(new Color(0x234A59));
        Complaint.setForeground(new Color(0xffffff));
        Complaint.addActionListener(this);

        returnbutton = new JButton();
        returnbutton.setBounds(0,0,100,100);
        returnbutton.setText("<- SIGNOUT");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,20));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);




        try {
            ResultSet rs =db.GetStaffData(loggedinStaff.getStaff_id());
            rs.next();
            currentstaff=new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

        }


        JLabel label=new JLabel();
        label.setText("WELCOME "+currentstaff.getName().toUpperCase());
        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);


        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
        top.add(label);


        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Members);
        optionspanel.add(Books);
        optionspanel.add(Account);
        optionspanel.add(Issue);
        optionspanel.add(Wifi);
        optionspanel.add(Report);
        optionspanel.add(Complaint);


        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(Color.WHITE);
        end.setLayout(new FlowLayout());
        end.setBackground(new Color(0x182C33));
        end.add(returnbutton);



        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x1D0C14));
        frame.add(top);
        frame.add(optionspanel);
        frame.add(end);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnbutton)
        {
            frame.dispose();
            currentstaff.clear();
            loggedinStaff.clear();
            new ProjectFrame();
        }
        else if(e.getSource()==Members)
        {
            if(!db.GetPermDbView(loggedinStaff.getPermission_id()))
                JOptionPane.showMessageDialog(null,"Sorry ! You dont have permission to view this.");
            else {
                frame.dispose();
                new ManageMembers();
            }
        }
        else if(e.getSource()==Books)
        {
            if(!db.GetPermDbView(loggedinStaff.getPermission_id()))
                JOptionPane.showMessageDialog(null,"Sorry ! You dont have permission to view this.");
            else {
                frame.dispose();
                new ManageBooks();
            }
        }
        else if(e.getSource()==Account)
        {
            frame.dispose();
            new ManageAccount();
        }
        else if(e.getSource()==Issue)
        {
            if(!db.GetPermDbView(loggedinStaff.getPermission_id()))
                JOptionPane.showMessageDialog(null,"Sorry ! You dont have permission to view this.");
            else {
                frame.dispose();
                new IssueBook();
            }
        }
        else if(e.getSource()==Wifi)
        {
            frame.dispose();
            new WifiMenu();
        }
        else if(e.getSource()==Report)
        {
            frame.dispose();
            new ManageReport();
        }
        else if(e.getSource()==Complaint)
        {
            frame.dispose();
            new ManageCompaint();
        }

    }
}

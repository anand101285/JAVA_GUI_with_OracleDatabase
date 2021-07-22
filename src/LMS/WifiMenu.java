package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class WifiMenu implements ActionListener {


    JFrame frame=new JFrame();
    AppAccount loggedinstaff=new AppAccount();
    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;
    AppAccount loggedinStaff;

    JButton EditCurrent;
    JButton managewifibutton;

    JButton returnbutton;

    WifiMenu()
    {


        loggedinStaff=new AppAccount();

        EditCurrent = new JButton();
        EditCurrent.setBounds(0,0,100,400);
        EditCurrent.setText("EDIT YOUR WIFI SETTINGS");
        EditCurrent.setFont(new Font("Segoe Print",Font.BOLD,30));
        EditCurrent.setBackground(new Color(0x234A59));
        EditCurrent.setForeground(new Color(0xffffff));
        EditCurrent.addActionListener(this);

        managewifibutton = new JButton();
        managewifibutton.setBounds(0,0,100,400);
        managewifibutton.setText("SHOW DETAILS OF ALL WIFI ACCOUNTS");
        managewifibutton.setFont(new Font("Segoe Print",Font.BOLD,30));
        managewifibutton.setBackground(new Color(0x234A59));
        managewifibutton.setForeground(new Color(0xffffff));
        managewifibutton.addActionListener(this);

        returnbutton = new JButton();
        returnbutton.setBounds(0,0,100,100);
        returnbutton.setText("<- Return");
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
        label.setText("WIFI ACCOUNT SETTINGS ");
        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);



        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(new GridLayout(9,1,0,10));
        optionspanel.add(EditCurrent);
        optionspanel.add(managewifibutton);


        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(new FlowLayout());
        end.add(returnbutton);



        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.add(top);
        frame.add(optionspanel);
        frame.add(end);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new LoginMenu();
        }
        else if(e.getSource()== EditCurrent)
        {
            frame.dispose();
            new CurrentWifiEdit();
        }
        else if(e.getSource()== managewifibutton)
        {
                               if(!db.GetPermDbView(loggedinstaff.getPermission_id())) {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
                return;
            }
            frame.dispose();
            new ManageWifi();
        }

    }
}

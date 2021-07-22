package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMember implements ActionListener {

    JFrame frame=new JFrame();
    JButton insertbutton;
    JButton returnbutton;
    JTextField getName;
    JTextField getMobile;
    JTextField getEmail;

    JTextField getcity;
    JTextField gethouse;
    JTextField getSector;
    JTextField getStreet;

    DatabaseQueryGetter db=new DatabaseQueryGetter();

    NewMember(){

        JLabel label=new JLabel();

        label.setText("New Member Insert");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
        top.add(label);

        JLabel Name=new JLabel();
        Name.setText("Name : ");
        Name.setSize(100,20);
        Name.setLocation(0,0);
        Name.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Name.setForeground(new Color(0xffffff));


        JLabel Mobile=new JLabel();
        Mobile.setText("Mobile No : ");
        Mobile.setSize(140,20);
        Mobile.setLocation(300,0);
        Mobile.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Mobile.setForeground(new Color(0xffffff));


        JLabel Email=new JLabel();
        Email.setText("Email :");
        Email.setSize(100,20);
        Email.setLocation(0,40);
        Email.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Email.setForeground(new Color(0xffffff));


        JLabel Address=new JLabel();
        Address.setText("Address Details:");
        Address.setSize(500,30);
        Address.setLocation(300,60);
        Address.setFont(new Font("Segoe Print",Font.BOLD,25));
        Address.setForeground(new Color(0xffffff));


        JLabel City=new JLabel();
        City.setText("City:");
        City.setSize(100,20);
        City.setLocation(0,100);
        City.setFont(new Font("Segoe Print",Font.ITALIC,20));
        City.setForeground(new Color(0xffffff));


        JLabel HouseNo=new JLabel();
        HouseNo.setText("House no:");
        HouseNo.setSize(100,20);
        HouseNo.setLocation(200,100);
        HouseNo.setFont(new Font("Segoe Print",Font.ITALIC,20));
        HouseNo.setForeground(new Color(0xffffff));


        JLabel Sector=new JLabel();
        Sector.setText("Sector: ");
        Sector.setSize(100,20);
        Sector.setLocation(500,100);
        Sector.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Sector.setForeground(new Color(0xffffff));


        JLabel Street=new JLabel();
        Street.setText("Street no:");
        Street.setSize(100,20);
        Street.setLocation(0,130);
        Street.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Street.setForeground(new Color(0xffffff));



        getName=new JTextField();
        getName.setSize(150,20);
        getName.setLocation(100,0);

        getEmail = new JTextField();
        getEmail.setSize(150,20);
        getEmail.setLocation(100,40);

        getMobile = new JTextField();
        getMobile.setSize(150,20);
        getMobile.setLocation(450,0);

        getcity = new JTextField();
        getcity.setSize(90,20);
        getcity.setLocation(100,100);

        gethouse = new JTextField();
        gethouse.setSize(150,20);
        gethouse.setLocation(300,100);


        getSector = new JTextField();
        getSector.setSize(150,20);
        getSector.setLocation(600,100);

        getStreet = new JTextField();
        getStreet.setSize(90,20);
        getStreet.setLocation(100,130);


        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,200);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Name);
        optionspanel.add(getName);
        optionspanel.add(Email);
        optionspanel.add(getEmail);
        optionspanel.add(Mobile);
        optionspanel.add(getMobile);
        optionspanel.add(Address);
        optionspanel.add(City);
        optionspanel.add(getcity);
        optionspanel.add(HouseNo);
        optionspanel.add(gethouse);
        optionspanel.add(Sector);
        optionspanel.add(getSector);
        optionspanel.add(Street);
        optionspanel.add(getStreet);




        insertbutton =new JButton();
        insertbutton.setBounds(450,21,100,30);
        insertbutton.setText("INSERT");
        insertbutton.setBackground(new Color(0x234A59));
        insertbutton.setForeground(new Color(0xffffff));
        insertbutton.addActionListener(this);

        returnbutton =new JButton();
        returnbutton.setBounds(5,21,100,30);
        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);


        JPanel end=new JPanel();
        end.setBounds(0,300,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(insertbutton);
        end.add(returnbutton);


        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,400);
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
            if(e.getSource()==insertbutton)
            {
                try {
                    ResultSet rs=db.AddNewAddress("address_id",getcity.getText(),gethouse.getText(),getSector.getText(),Integer.parseInt(getStreet.getText()));
                    if(rs.next())
                    {
                        ResultSet mrs=db.AddNewMember("Member_id",getEmail.getText(),getName.getText(),getMobile.getText(),rs.getInt(1));
                        if(mrs.next()) {
                            JOptionPane.showMessageDialog(null, "New Member Inserted Successfully with id=" + mrs.getInt(1));
                            frame.dispose();
                            new LoginMenu();
                        }

                        else
                            JOptionPane.showMessageDialog(null,"Error: Unsuccessfull");
                    }
                } catch (Exception
 throwables) {
                    JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

                }
            }
            else if(e.getSource()==returnbutton)
            {
                frame.dispose();
                new ManageMembers();
            }


    }
}

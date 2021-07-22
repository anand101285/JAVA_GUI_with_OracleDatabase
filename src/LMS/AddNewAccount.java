package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddNewAccount implements ActionListener {

    JFrame frame=new JFrame();
    JButton insertbutton;
    JButton returnbutton;
    JTextField getLoginId;
    JTextField getPassword;
    JTextField getStaffName;
    JCheckBox db_view;
    JCheckBox db_edit;
    JCheckBox db_delete;

    JTextField getcity;
    JTextField gethouse;
    JTextField getSector;
    JTextField getStreet;

    DatabaseQueryGetter db=new DatabaseQueryGetter();

    AddNewAccount()
    {

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
        Name.setText("Login ID : ");
        Name.setSize(120,20);
        Name.setLocation(0,0);
        Name.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Name.setForeground(new Color(0xffffff));


        JLabel Mobile=new JLabel();
        Mobile.setText("Password : ");
        Mobile.setSize(140,20);
        Mobile.setLocation(300,0);
        Mobile.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Mobile.setForeground(new Color(0xffffff));


        JLabel Email=new JLabel();
        Email.setText("Staff Name :");
        Email.setSize(140,20);
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


        JLabel Permission=new JLabel();
        Permission.setText("Permission Details");
        Permission.setSize(500,30);
        Permission.setLocation(300,200);
        Permission.setFont(new Font("Segoe Print",Font.BOLD,25));
        Permission.setForeground(new Color(0xffffff));


        db_view=new JCheckBox();
        db_view.setText("Database View");
        db_view.setFocusable(false);
        db_view.setSize(300,30);
        db_view.setLocation(30,235);
        db_view.setFont(new Font("Segoe Print",Font.BOLD,15));
        db_view.setBackground(new Color(0x142328));
        db_view.setForeground(new Color(0xffffff));



        db_edit=new JCheckBox();
        db_edit.setText("Database Edit");
        db_edit.setFocusable(false);
        db_edit.setSize(300,30);
        db_edit.setLocation(330,235);
        db_edit.setFont(new Font("Segoe Print",Font.BOLD,15));
        db_edit.setBackground(new Color(0x142328));
        db_edit.setForeground(new Color(0xffffff));


        db_delete=new JCheckBox();
        db_delete.setText("Database Delete");
        db_delete.setFocusable(false);
        db_delete.setSize(300,30);
        db_delete.setLocation(630,235);
        db_delete.setFont(new Font("Segoe Print",Font.BOLD,15));
        db_delete.setBackground(new Color(0x142328));
        db_delete.setForeground(new Color(0xffffff));



        getLoginId =new JTextField();
        getLoginId.setSize(150,20);
        getLoginId.setLocation(122,0);

        getStaffName = new JTextField();
        getStaffName.setSize(150,20);
        getStaffName.setLocation(135,40);

        getPassword = new JTextField();
        getPassword.setSize(150,20);
        getPassword.setLocation(450,0);

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
        optionspanel.setBounds(0,100,1000,300);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Name);
        optionspanel.add(getLoginId);
        optionspanel.add(Email);
        optionspanel.add(getStaffName);
        optionspanel.add(Mobile);
        optionspanel.add(getPassword);
        optionspanel.add(Address);
        optionspanel.add(City);
        optionspanel.add(getcity);
        optionspanel.add(HouseNo);
        optionspanel.add(gethouse);
        optionspanel.add(Sector);
        optionspanel.add(getSector);
        optionspanel.add(Street);
        optionspanel.add(getStreet);
        optionspanel.add(Permission);
        optionspanel.add(db_edit);
        optionspanel.add(db_delete);
        optionspanel.add(db_view);



        insertbutton =new JButton();
        insertbutton.setBounds(450,21,100,30);
        insertbutton.setText("INSERT");
        insertbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
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
        end.setBounds(0,400,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(insertbutton);
        end.add(returnbutton);


        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.add(top);
        frame.add(optionspanel);
        frame.add(end);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==insertbutton)
        {
            try {
                ResultSet rs=db.AddNewAddress("address_id",getcity.getText(),gethouse.getText(),getSector.getText(),Integer.parseInt(getStreet.getText()));
                if(rs.next())
                {
                    ResultSet mrs=db.AddNewStaff("Staff_id",getStaffName.getText(),rs.getInt(1));
                    if(mrs.next()) {

                        ResultSet prs=db.GetSpecificPermission(db_edit.isSelected(),db_delete.isSelected(),db_view.isSelected());
                        if(prs.next()) {
                            ResultSet Ars = db.AddNewAppAccount("Login_id", getLoginId.getText(), getPassword.getText(), mrs.getInt(1),prs.getInt(1));
                            if (Ars.next()) {
                                JOptionPane.showMessageDialog(null, "New Staff Inserted Successfully with Account id=" + Ars.getString(1));
                                frame.dispose();
                                new ManageAccount();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error: While Adding New Account ");
                            }
                        }

                    }

                }
            } catch (Exception
 throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }
        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new ManageAccount();
        }


    }
}

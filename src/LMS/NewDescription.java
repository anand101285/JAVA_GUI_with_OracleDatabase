package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class NewDescription implements ActionListener {

    JFrame frame=new JFrame();
    JButton insertbutton;
    JButton returnbutton;

    JTextField description;

    Staff currentstaff=new Staff();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    int memberid=0;

    NewDescription(int mem_id)
    {
        memberid=mem_id;
        JLabel label=new JLabel();

        label.setText("ENTER DESCRIPTION FOR COMPLAINT");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
        top.add(label);


        JLabel Description=new JLabel();
        Description.setText("Description :");
        Description.setSize(250,20);
        Description.setLocation(0,20);
        Description.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Description.setForeground(new Color(0xffffff));


        description = new JTextField();
        description.setSize(600, 180);
        description.setLocation(240,20);



        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,200);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Description);
        optionspanel.add(description);




        insertbutton =new JButton();
        insertbutton.setBounds(450,21,100,30);
        insertbutton.setText("ADD");
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
                ResultSet rs=db.AddNewComplaint(memberid,currentstaff.getStaff_id(),description.getText());
                if(rs.next())
                {
                    JOptionPane.showMessageDialog(null,"New Complaint has been filed with id="+rs.getInt(1));
                }


            } catch (Exception
                    throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }
        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new ManageCompaint();
        }


    }
}

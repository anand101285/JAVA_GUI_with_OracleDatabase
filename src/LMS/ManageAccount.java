package LMS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManageAccount implements ActionListener {


    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;
    AppAccount loggedinstaff=new AppAccount();

    JButton EditCurrent;
    JButton AddNew;

    JButton returnbutton;
    ManageAccount()
    {




        EditCurrent = new JButton();
        EditCurrent.setBounds(0,0,100,400);
        EditCurrent.setText("Edit Current Account");
        EditCurrent.setFont(new Font("Segoe Print",Font.BOLD,30));
        EditCurrent.setBackground(new Color(0x234A59));
        EditCurrent.setForeground(new Color(0xffffff));
        EditCurrent.addActionListener(this);

        AddNew = new JButton();
        AddNew.setBounds(0,0,100,400);
        AddNew.setText("Add New Account");
        AddNew.setFont(new Font("Segoe Print",Font.BOLD,30));
        AddNew.setBackground(new Color(0x234A59));
        AddNew.setForeground(new Color(0xffffff));
        AddNew.addActionListener(this);

        returnbutton = new JButton();
        returnbutton.setBounds(0,0,100,100);
        returnbutton.setText("<- Return");
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,20));
        returnbutton.addActionListener(this);

        try {
            ResultSet rs =db.GetStaffData(loggedinstaff.getStaff_id());
            rs.next();
            currentstaff=new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

        }



        JLabel label=new JLabel();
        label.setText(" ACCOUNT SETTINGS ");
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
        optionspanel.add(AddNew);


        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x142328));
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new LoginMenu();
        }
        else if(e.getSource()== EditCurrent)
        {
            frame.dispose();
            new AccountEdit();
        }
        else if(e.getSource()== AddNew)
        {
            if(!db.GetPermDbEdit(loggedinstaff.getPermission_id())) {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
                return;
            }
            frame.dispose();
            new AddNewAccount();
        }

    }
}

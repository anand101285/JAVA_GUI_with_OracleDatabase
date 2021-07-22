package LMS;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ProjectFrame extends JFrame implements ActionListener {

    DatabaseQueryGetter db;

    JButton loginbutton;
    JFrame frame=new JFrame();
    JTextField usernamefield;
    JTextField passwordfield;
    ProjectFrame()
    {

        usernamefield=new JTextField();
        usernamefield.setSize(250,25);
        usernamefield.setLocation(153,15);

        JLabel usernamelabel=new JLabel("USERNAME: ");
        usernamelabel.setFont(new Font("Segoe Print",Font.ITALIC,15));
        usernamelabel.setLocation(4,15);
        usernamelabel.setForeground(new Color(0xFFFFFFF));
        usernamelabel.setSize(150,34);


        passwordfield=new JTextField();
        passwordfield.setSize(250,25);
        passwordfield.setLocation(153,58);


        JLabel passwordlabel=new JLabel("PASSWORD: ");
        passwordlabel.setBounds(10,50,100,50);
        passwordlabel.setFont(new Font("Segoe Print",Font.ITALIC,15));
        passwordlabel.setForeground(new Color(0xFFFFFFF));
        passwordlabel.setLocation(4,55);
        passwordlabel.setSize(150,34);



        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/lmslogo.png")); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back



        Border border=BorderFactory.createLineBorder(Color.BLACK,2);

        JLabel label=new JLabel();
        label.setText("LIBRARY MANAGEMENT SYSTEM");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Domine",Font.BOLD,20));
        label.setLocation(0,0);
        label.setForeground(new Color(0xffffff));
        label.setIcon(imageIcon);
        label.setIconTextGap(20);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setBorder(border);
        label.setSize(500,95);
        label.setOpaque(true);




        label.setBackground(new Color(0x09181D));



        loginbutton=new JButton();
        loginbutton.setBounds(220,250,70,30);
        loginbutton.setText("LOGIN");
        loginbutton.setHorizontalAlignment(JButton.CENTER);
        loginbutton.setBackground(new Color(0x234A59));
        loginbutton.setForeground(new Color(0xffffff));
        loginbutton.addActionListener(this);
        loginbutton.setFocusable(true);

        JPanel loginform=new JPanel();
        loginform.setBackground(new Color(0x142328));
        loginform.setBounds(0,95,500,300);
        loginform.setLayout(null);

        loginform.add(usernamelabel);
        loginform.add(usernamefield);
        loginform.add(passwordlabel);
        loginform.add(passwordfield);
        loginform.add(loginbutton);




        frame.setVisible(true);
        frame.setTitle("Library Management System");
        frame.setSize(500,430);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));

        frame.add(label);
        frame.add(loginform);

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==loginbutton)
        {
            db = new DatabaseQueryGetter();
            String username=usernamefield.getText();
            String password=passwordfield.getText();


            try {
                ResultSet rs = db.GetLoginData(username);

                if(rs.next())
                {
                    if(rs.getString(2).equals(password))
                    {
                        ResultSet rs_staff=db.GetStaffData(rs.getInt(3));
                        AppAccount app=new AppAccount(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                        if(rs_staff.next())
                        {
                            Staff currentstaff=new Staff(rs_staff.getInt(1),rs_staff.getString(2),rs_staff.getString(3),rs_staff.getInt(4));
                        }

                        frame.dispose();
                        LoginMenu loggedIn=new LoginMenu();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Sorry Wrong Password ");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Sorry Wrong Username ");
                }
            } catch (Exception
 throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }


        }

    }
}

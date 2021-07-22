package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageReport implements ActionListener {


    JFrame frame=new JFrame();
    JButton returnbutton;


    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff current=new Staff();

    ArrayList<ArrayList<String>> row_sup=new ArrayList<>();

    ManageReport()
    {

        JLabel label=new JLabel();

        label.setText("LIBRARY RECORD COUNT");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
        top.add(label);

        JLabel TotalBooks=new JLabel();
        TotalBooks.setText("Total Books : ");
        TotalBooks.setSize(150,20);
        TotalBooks.setLocation(0,30);
        TotalBooks.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        TotalBooks.setForeground(new Color(0xffffff));


        JLabel BooksIssued=new JLabel();
        BooksIssued.setText("Books Issued : ");
        BooksIssued.setSize(150,20);
        BooksIssued.setLocation(300,30);
        BooksIssued.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        BooksIssued.setForeground(new Color(0xffffff));


        JLabel Members=new JLabel();
        Members.setText("Total Members :");
        Members.setSize(190,20);
        Members.setLocation(0,70);
        Members.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Members.setForeground(new Color(0xffffff));


        JLabel Staff=new JLabel();
        Staff.setText("Total Staff :");
        Staff.setSize(150,20);
        Staff.setLocation(300,70);
        Staff.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Staff.setForeground(new Color(0xffffff));


        JLabel Publisher=new JLabel();
        Publisher.setText("Total Publisher :");
        Publisher.setSize(170,20);
        Publisher.setLocation(550,70);
        Publisher.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Publisher.setForeground(new Color(0xffffff));


//        JLabel Address=new JLabel();
//        Address.setText("Writer Address Details :");
//        Address.setSize(500,30);
//        Address.setLocation(300,70);
//        Address.setFont(new Font("Segoe Print",Font.BOLD,25));

        JLabel Supplier=new JLabel();
        Supplier.setText("Total Suppliers : ");
        Supplier.setSize(190,20);
        Supplier.setLocation(0,150);
        Supplier.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Supplier.setForeground(new Color(0xffffff));


        JLabel Writer=new JLabel();
        Writer.setText("Total Writers : ");
        Writer.setSize(170,20);
        Writer.setLocation(0,190);
        Writer.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Writer.setForeground(new Color(0xffffff));


        JLabel Wifi=new JLabel();
        Wifi.setText("Members having Wifi : ");
        Wifi.setSize(250,30);
        Wifi.setLocation(360,180);
        Wifi.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Wifi.setForeground(new Color(0xffffff));


        JLabel Date=new JLabel();
        Date.setText("Today's Date : ");
        Date.setSize(160,25);
        Date.setLocation(0,260);
        Date.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Date.setForeground(new Color(0xffffff));

        JLabel Role=new JLabel();
        Role.setText("Your Role : ");
        Role.setSize(160,25);
        Role.setLocation(0,304);
        Role.setFont(new Font("Segoe Print",Font.ITALIC+Font.BOLD,20));
        Role.setForeground(new Color(0xffffff));


//        JLabel Permission=new JLabel();
//        Permission.setText("Writer Details :");
//        Permission.setSize(500,30);
//        Permission.setLocation(300,195);
//        Permission.setFont(new Font("Segoe Print",Font.BOLD,25));



        ResultSet book_r=db.ReflexiveSelectQuery("select count(*) from book");
        ResultSet Issue_r=db.ReflexiveSelectQuery("select count(*) from Issue");
        ResultSet member_r=db.ReflexiveSelectQuery("select count(*) from Member");
        ResultSet staff_r=db.ReflexiveSelectQuery("select count(*) from Staff");
        ResultSet publisher_r=db.ReflexiveSelectQuery("select count(*) from Publisher");
        ResultSet supplier_r=db.ReflexiveSelectQuery("select count(*) from Supplier");
        ResultSet writer_r=db.ReflexiveSelectQuery("select count(*) from Writer");
        ResultSet wifi_r=db.ReflexiveSelectQuery("select count(*) from Wifi");
        ResultSet role_r=db.ReflexiveSelectQuery("select Description from role natural join staff where staff_id="+current.getStaff_id());


        JLabel BookNum=new JLabel();
        JLabel IssueNum=new JLabel();
        JLabel MemeberNum=new JLabel();
        JLabel StaffNum=new JLabel();
        JLabel PublisherNum=new JLabel();
        JLabel SupplierNum=new JLabel();
        JLabel WriterNum=new JLabel();
        JLabel WifiNum=new JLabel();
        JLabel Datetoday=new JLabel();
        JTextArea StaffRole=new JTextArea();

        try
        {
            book_r.next();
            Issue_r.next();
            member_r.next();
            staff_r.next();
            publisher_r.next();
            supplier_r.next();
            writer_r.next();
            wifi_r.next();
            role_r.next();


            BookNum.setText(Integer.toString(book_r.getInt(1)));
            BookNum.setSize(160,20);
            BookNum.setLocation(170,30);
            BookNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            BookNum.setForeground(new Color(0xccffcc));

            StaffRole.setText(role_r.getString(1));
            StaffRole.setSize(790,200);
            StaffRole.setLocation(170,300);
            StaffRole.setFont(new Font("Segoe Print",Font.BOLD,20));
            StaffRole.setForeground(new Color(0xffffff));
            StaffRole.setBackground(new Color(0x142328));
            StaffRole.setLineWrap(true);
            StaffRole.setEditable(false);



            IssueNum.setText(Integer.toString(Issue_r.getInt(1)));
            IssueNum.setSize(160,20);
            IssueNum.setLocation(470,30);
            IssueNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            IssueNum.setForeground(new Color(0xccffcc));


            MemeberNum.setText(Integer.toString(member_r.getInt(1)));
            MemeberNum.setSize(160,20);
            MemeberNum.setLocation(170,70);
            MemeberNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            MemeberNum.setForeground(new Color(0xccffcc));


            StaffNum.setText(Integer.toString(staff_r.getInt(1)));
            StaffNum.setSize(160,20);
            StaffNum.setLocation(470,70);
            StaffNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            StaffNum.setForeground(new Color(0xccffcc));

            PublisherNum.setText(Integer.toString(publisher_r.getInt(1)));
            PublisherNum.setSize(160,20);
            PublisherNum.setLocation(780,70);
            PublisherNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            PublisherNum.setForeground(new Color(0xccffcc));

            SupplierNum.setText(Integer.toString(supplier_r.getInt(1)));
            SupplierNum.setSize(160,20);
            SupplierNum.setLocation(170,150);
            SupplierNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            SupplierNum.setForeground(new Color(0xccffcc));

            WriterNum.setText(Integer.toString(writer_r.getInt(1)));
            WriterNum.setSize(160,20);
            WriterNum.setLocation(170,190);
            WriterNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            WriterNum.setForeground(new Color(0xccffcc));

            WifiNum.setText(Integer.toString(wifi_r.getInt(1)));
            WifiNum.setSize(160,20);
            WifiNum.setLocation(589,185);
            WifiNum.setFont(new Font("Segoe Print",Font.BOLD,20));
            WifiNum.setForeground(new Color(0xccffcc));

            Datetoday.setText(java.time.LocalDate.now().toString());
            Datetoday.setSize(160,20);
            Datetoday.setLocation(170,260);
            Datetoday.setFont(new Font("Segoe Print",Font.BOLD,20));
            Datetoday.setForeground(new Color(0xccffcc));



        }
        catch (Exception e)
        {

        }





        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(TotalBooks);
        optionspanel.add(Members);
        optionspanel.add(BooksIssued);
        optionspanel.add(Supplier);
        optionspanel.add(Writer);
        optionspanel.add(Wifi);
        optionspanel.add(Date);
        optionspanel.add(Staff);
        optionspanel.add(Publisher);
        optionspanel.add(BookNum);
        optionspanel.add(MemeberNum);
        optionspanel.add(IssueNum);
        optionspanel.add(SupplierNum);
        optionspanel.add(WriterNum);
        optionspanel.add(WifiNum);
        optionspanel.add(StaffNum);
        optionspanel.add(PublisherNum);
        optionspanel.add(Datetoday);
        optionspanel.add(Role);
        optionspanel.add(StaffRole);



        returnbutton =new JButton();
        returnbutton.setBounds(5,21,100,30);
        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);


        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
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

    }
}

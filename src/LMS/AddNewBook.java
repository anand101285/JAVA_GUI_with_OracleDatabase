package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AddNewBook implements ActionListener {

    JFrame frame=new JFrame();
    JButton insertbutton;
    JButton returnbutton;
    JTextField getTitle;
    JTextField getISBN;
    JTextField getPrice;
    JComboBox getGenre;
    JComboBox getSupplier;
    JTextField getEdition;

    JTextField getWriterName;
    JTextField getWriterAge;
    JTextField getPName;
    JTextField getPCity;
    JTextField getPHouseno;
    JTextField getPSector;
    JTextField getPStreet;

    JTextField getcity;
    JTextField gethouse;
    JTextField getSector;
    JTextField getStreet;

    DatabaseQueryGetter db=new DatabaseQueryGetter();

    ArrayList<ArrayList<String>> row_sup=new ArrayList<>();

    AddNewBook(){


        JLabel label=new JLabel();

        label.setText("New BOOK Insert".toUpperCase());

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        JLabel Name=new JLabel();
        Name.setText("Title : ");
        Name.setSize(100,20);
        Name.setLocation(0,0);
        Name.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Name.setForeground(new Color(0xffffff));


        JLabel Mobile=new JLabel();
        Mobile.setText("ISBN : ");
        Mobile.setSize(140,20);
        Mobile.setLocation(300,0);
        Mobile.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Mobile.setForeground(new Color(0xffffff));


        JLabel Email=new JLabel();
        Email.setText("Price :");
        Email.setSize(100,20);
        Email.setLocation(0,40);
        Email.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Email.setForeground(new Color(0xffffff));


        JLabel genre=new JLabel();
        genre.setText("Genre :");
        genre.setSize(100,20);
        genre.setLocation(300,40);
        genre.setFont(new Font("Segoe Print",Font.ITALIC,20));
        genre.setForeground(new Color(0xffffff));



        JLabel edition=new JLabel();
        edition.setText("Edition :");
        edition.setSize(100,20);
        edition.setLocation(550,40);
        edition.setFont(new Font("Segoe Print",Font.ITALIC,20));
        edition.setForeground(new Color(0xffffff));


        JLabel Address=new JLabel();
        Address.setText("Writer Address Details :");
        Address.setSize(500,30);
        Address.setLocation(300,70);
        Address.setFont(new Font("Segoe Print",Font.BOLD,25));
        Address.setForeground(new Color(0xffffff));


        JLabel City=new JLabel();
        City.setText("City:");
        City.setSize(100,20);
        City.setLocation(0,120);
        City.setFont(new Font("Segoe Print",Font.ITALIC,20));
        City.setForeground(new Color(0xffffff));


        JLabel HouseNo=new JLabel();
        HouseNo.setText("House no:");
        HouseNo.setSize(100,20);
        HouseNo.setLocation(200,120);
        HouseNo.setFont(new Font("Segoe Print",Font.ITALIC,20));
        HouseNo.setForeground(new Color(0xffffff));


        JLabel Sector=new JLabel();
        Sector.setText("Sector: ");
        Sector.setSize(100,20);
        Sector.setLocation(500,120);
        Sector.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Sector.setForeground(new Color(0xffffff));


        JLabel Street=new JLabel();
        Street.setText("Street no:");
        Street.setSize(100,20);
        Street.setLocation(0,150);
        Street.setFont(new Font("Segoe Print",Font.ITALIC,20));
        Street.setForeground(new Color(0xffffff));


        JLabel Permission=new JLabel();
        Permission.setText("Writer Details :");
        Permission.setSize(500,30);
        Permission.setLocation(300,195);
        Permission.setFont(new Font("Segoe Print",Font.BOLD,25));
        Permission.setForeground(new Color(0xffffff));


        JLabel wName=new JLabel();
        wName.setText("Name :");
        wName.setSize(100,20);
        wName.setLocation(0,250);
        wName.setFont(new Font("Segoe Print",Font.ITALIC,20));
        wName.setForeground(new Color(0xffffff));


        JLabel wAge=new JLabel();
        wAge.setText("Age :");
        wAge.setSize(100,20);
        wAge.setLocation(500,250);
        wAge.setFont(new Font("Segoe Print",Font.ITALIC,20));
        wAge.setForeground(new Color(0xffffff));


        JLabel PublisherDet=new JLabel();
        PublisherDet.setText("Publisher Details :");
        PublisherDet.setSize(500,30);
        PublisherDet.setLocation(300,280);
        PublisherDet.setFont(new Font("Segoe Print",Font.BOLD,25));
        PublisherDet.setForeground(new Color(0xffffff));


        JLabel pName=new JLabel();
        pName.setText("Name :");
        pName.setSize(500,30);
        pName.setLocation(0,330);
        pName.setFont(new Font("Segoe Print",Font.ITALIC,25));
        pName.setForeground(new Color(0xffffff));



        JLabel p_City=new JLabel();
        p_City.setText("City:");
        p_City.setSize(100,20);
        p_City.setLocation(300,330);
        p_City.setFont(new Font("Segoe Print",Font.ITALIC,20));
        p_City.setForeground(new Color(0xffffff));


        JLabel p_HouseNo=new JLabel();
        p_HouseNo.setText("House no:");
        p_HouseNo.setSize(100,20);
        p_HouseNo.setLocation(600,330);
        p_HouseNo.setFont(new Font("Segoe Print",Font.ITALIC,20));
        p_HouseNo.setForeground(new Color(0xffffff));


        JLabel p_Sector=new JLabel();
        p_Sector.setText("Sector: ");
        p_Sector.setSize(100,20);
        p_Sector.setLocation(0,390);
        p_Sector.setFont(new Font("Segoe Print",Font.ITALIC,20));
        p_Sector.setForeground(new Color(0xffffff));


        JLabel p_Street=new JLabel();
        p_Street.setText("Street no:");
        p_Street.setSize(100,20);
        p_Street.setLocation(300,390);
        p_Street.setFont(new Font("Segoe Print",Font.ITALIC,20));
        p_Street.setForeground(new Color(0xffffff));


        JLabel supplier_d=new JLabel();
        supplier_d.setText("Supplier:");
        supplier_d.setSize(150,25);
        supplier_d.setLocation(200,440);
        supplier_d.setFont(new Font("Segoe Print",Font.BOLD,20));
        supplier_d.setForeground(new Color(0xffffff));



        getTitle =new JTextField();
        getTitle.setSize(150,20);
        getTitle.setLocation(100,0);

        String[] genre_options={"Historical","Romance","Fantasy","Adventure","Thriller","Drama"};
        getGenre =new JComboBox(genre_options);
        getGenre.setSize(100,20);
        getGenre.setLocation(400,40);

        ResultSet s_result=db.GetAllSupplier();

            try {
                    int s=0;
                    while(s_result.next()) {
                        row_sup.add(new ArrayList<>());
                        row_sup.get(s).add(s_result.getString(1));
                        row_sup.get(s).add(s_result.getString(2));
                        s++;
                    }
            } catch (Exception
 throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }


        String[] suppliers=new String[row_sup.size()];
        for(int i=0;i<row_sup.size();i++)
        {
            for(int j=0;j<row_sup.size();j++)
            {
                suppliers[i]=row_sup.get(i).get(1);
            }
        }

        getSupplier =new JComboBox(suppliers);
        getSupplier.setSize(160,20);
        getSupplier.setLocation(330,440);


        getPrice = new JTextField();
        getPrice.setSize(150,20);
        getPrice.setLocation(100,40);

        getEdition= new JTextField();
        getEdition.setSize(50,20);
        getEdition.setLocation(650,40);

        getISBN = new JTextField();
        getISBN.setSize(150,20);
        getISBN.setLocation(400,0);

        getcity = new JTextField();
        getcity.setSize(90,20);
        getcity.setLocation(100,120);

        gethouse = new JTextField();
        gethouse.setSize(150,20);
        gethouse.setLocation(300,120);


        getSector = new JTextField();
        getSector.setSize(150,20);
        getSector.setLocation(600,120);

        getStreet = new JTextField();
        getStreet.setSize(90,20);
        getStreet.setLocation(100,150);

        getWriterName= new JTextField();
        getWriterName.setSize(100,20);
        getWriterName.setLocation(80,250);


        getWriterAge= new JTextField();
        getWriterAge.setSize(50,20);
        getWriterAge.setLocation(560,250);

        getPName= new JTextField();
        getPName.setSize(90,20);
        getPName.setLocation(100,330);

        getPCity= new JTextField();
        getPCity.setSize(90,20);
        getPCity.setLocation(350,330);

        getPHouseno= new JTextField();
        getPHouseno.setSize(90,20);
        getPHouseno.setLocation(700,330);

        getPSector= new JTextField();
        getPSector.setSize(90,20);
        getPSector.setLocation(100,390);

        getPStreet= new JTextField();
        getPStreet.setSize(50,20);
        getPStreet.setLocation(390,390);



        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Name);
        optionspanel.add(getTitle);
        optionspanel.add(Email);
        optionspanel.add(getPrice);
        optionspanel.add(Mobile);
        optionspanel.add(getISBN);
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
        optionspanel.add(genre);
        optionspanel.add(getGenre);
        optionspanel.add(edition);
        optionspanel.add(getEdition);
        optionspanel.add(wName);
        optionspanel.add(getWriterName);
        optionspanel.add(wAge);
        optionspanel.add(getWriterAge);
        optionspanel.add(PublisherDet);
        optionspanel.add(pName);
        optionspanel.add(getPName);
        optionspanel.add(p_City);
        optionspanel.add(getPCity);
        optionspanel.add(p_HouseNo);
        optionspanel.add(getPHouseno);
        optionspanel.add(p_Sector);
        optionspanel.add(getPSector);
        optionspanel.add(p_Street);
        optionspanel.add(getPStreet);
        optionspanel.add(supplier_d);
        optionspanel.add(getSupplier);


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
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(insertbutton);
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
        if(e.getSource()==insertbutton)
        {
            int writer_address;
            int publisher_address;
            try {
                ResultSet writer_address_rs=db.AddNewAddress("address_id",getcity.getText(),gethouse.getText(),getSector.getText(),Integer.parseInt(getStreet.getText()));
                if(writer_address_rs.next())
                {
                    writer_address=writer_address_rs.getInt(1);
                    ResultSet publisher_address_rs=db.AddNewAddress("address_id",getPCity.getText(),getPHouseno.getText(),getPSector.getText(),Integer.parseInt(getPStreet.getText()));
                    if(publisher_address_rs.next())
                    {
                        publisher_address=publisher_address_rs.getInt(1);
                        ResultSet prs=db.AddNewPublisher("publisher_id", getPName.getText(),publisher_address);
                        ResultSet wrs=db.AddNewWriter("wid",getWriterName.getText(),Integer.parseInt(getWriterAge.getText()),writer_address);

                        if(prs.next() && wrs.next()) {
                            int sid=0;
                            for (int i = 0; i < row_sup.size(); i++)         //getting supplier id slected supplier
                            {
                                if(row_sup.get(i).get(1).equals(getSupplier.getSelectedItem().toString()))
                                    sid=Integer.parseInt(row_sup.get(i).get(0));
                            }
                            ResultSet brs= db.AddNewBook("ISBN",getISBN.getText(),Integer.parseInt(getPrice.getText()),getGenre.getSelectedItem().toString(),getTitle.getText(),Integer.parseInt(getEdition.getText()),wrs.getInt(1),sid,prs.getInt(1));
                            if(brs.next())
                            {
                                JOptionPane.showMessageDialog(null,"Book details were successfully inserted");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Error: inserting error. something is not right");
                            }
                        }


                    }
                }
            } catch (Exception throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }

        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new ManageBooks();
        }

    }
}
package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManageBooks implements ActionListener {
    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;

    JTable book_table;

    JButton returnbutton;
    JButton insertbutton;


    JButton ViewBook;
    JButton ViewWriter;
    JButton ViewPublisher;

    ResultSet rs;

    AppAccount loggedinstaff=new AppAccount();


    ManageBooks()
    {
        currentstaff=new Staff();

        ArrayList<ArrayList<String>> row=new ArrayList<>();;
        try {
            rs =db.GetAllBookWritersupplier();

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(rs.getString(1));
                row.get(s).add(rs.getString(2));
                row.get(s).add(rs.getString(3));
                row.get(s).add(rs.getString(4));
                row.get(s).add(rs.getString(5));
                row.get(s).add(rs.getString(6));
                s++;
            }


        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

        }
        System.out.println(row.toString());


                Object[][] rows=null;
        if(row.size()>0)
            rows=new Object[row.size()][row.get(0).size()];

        for(int i=0;i<row.size();i++)
        {
            for(int j=0;j<row.get(i).size();j++)
            {
                rows[i][j]=row.get(i).get(j);
            }
        }

        JLabel label=new JLabel();

        label.setText("LIBRARY BOOK DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"ISBN","TITLE","NAME","ORG_NAME","PRICE","YEAR"};

        DefaultTableModel defmodel= new DefaultTableModel(rows,column_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        book_table =new JTable(defmodel);
        book_table.getTableHeader().setBackground(new Color(0x2a4422));
        book_table.getTableHeader().setForeground(new Color(0xffffff));


        JScrollPane scrollPane=new JScrollPane(book_table);

        scrollPane.setVisible(true);


        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(new BorderLayout());
        optionspanel.add(scrollPane,BorderLayout.CENTER);



        returnbutton = new JButton();
        returnbutton.setBounds(2,0,100,30);

        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);




        insertbutton =new JButton();
        insertbutton.setBounds(790,0,200,30);
        insertbutton.setText("INSERT NEW BOOK");
        insertbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        insertbutton.setBackground(new Color(0x234A59));
        insertbutton.setForeground(new Color(0xffffff));
        insertbutton.addActionListener(this);


        ViewBook =new JButton();
        ViewBook.setBounds(360,40,200,30);
        ViewBook.setText("MORE BOOK DETAILS");
        ViewBook.setFont(new Font("Segoe Print",Font.BOLD,10));
        ViewBook.setBackground(new Color(0x234A59));
        ViewBook.setForeground(new Color(0xffffff));
        ViewBook.addActionListener(this);

        ViewPublisher =new JButton();
        ViewPublisher.setBounds(155,40,200,30);
        ViewPublisher.setText("MORE PUBLISHER DETAILS");
        ViewPublisher.setFont(new Font("Segoe Print",Font.BOLD,10));
        ViewPublisher.setBackground(new Color(0x234A59));
        ViewPublisher.setForeground(new Color(0xffffff));
        ViewPublisher.addActionListener(this);

        ViewWriter =new JButton();
        ViewWriter.setBounds(565,40,200,30);
        ViewWriter.setText("MORE WRITER DETAILS");
        ViewWriter.setFont(new Font("Segoe Print",Font.BOLD,10));
        ViewWriter.setBackground(new Color(0x234A59));
        ViewWriter.setForeground(new Color(0xffffff));
        ViewWriter.addActionListener(this);


        JPanel end=new JPanel();
        end.setBounds(0,600,1000,150);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(returnbutton);

        end.add(insertbutton);
        end.add(ViewBook);
        end.add(ViewPublisher);
        end.add(ViewWriter);



        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,750);
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

        if(e.getSource()== insertbutton)
        {
            if(db.GetPermDbEdit(loggedinstaff.getPermission_id())) {
                frame.dispose();
                new AddNewBook();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
            }
        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new LoginMenu();
        }
        else if(e.getSource()==ViewBook)
        {
            frame.dispose();
            new ViewBook();
        }
        else if(e.getSource()== ViewWriter)
        {
            frame.dispose();
            new ViewWriter();
        }
        else if(e.getSource()==ViewPublisher)
        {
            frame.dispose();
            new ViewPublisher();
        }

    }
}

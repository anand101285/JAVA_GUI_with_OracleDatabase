package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ViewPublisher implements ActionListener {

    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;

    JTable book_table;

    JButton returnbutton;
    JButton editbutton;
    AppAccount loggedinstaff=new AppAccount();

    ResultSet rs;
    ViewPublisher(){


        currentstaff=new Staff();
//        ArrayList<String> data=new ArrayList<>();
        ArrayList<ArrayList<String>> row=new ArrayList<>();
        try {
            rs =db.GetAllPublisher();

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(Integer.toString(rs.getInt(1)));
                row.get(s).add(rs.getString(2));
                row.get(s).add(rs.getString(3));
                row.get(s).add(rs.getString(4));
                row.get(s).add(rs.getString(5));
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

        label.setText("BOOK PUBLISHER DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"PUBLISHER_ID","NAME","TITLE","ISBN","CITY"};

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




        editbutton =new JButton();
        editbutton.setBounds(790,0,200,30);
        editbutton.setText("EDIT PUBLISHER DETAILS  ");
        editbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        editbutton.setBackground(new Color(0x234A59));
        editbutton.setForeground(new Color(0xffffff));
        editbutton.addActionListener(this);




        JPanel end=new JPanel();
        end.setBounds(0,600,1000,150);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(returnbutton);
        end.add(editbutton);



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
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==editbutton)
        {
                               if(!db.GetPermDbEdit(loggedinstaff.getPermission_id())) {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
                return;
            }
            int col= book_table.getSelectedColumn();
            int row= book_table.getSelectedRow();

            if(row!=-1 && col!=-1) {
                Object id = book_table.getValueAt(row, 0);
                String s = id.toString();
                System.out.println(s);
                frame.dispose();
                new EditPublisher(Integer.parseInt(s));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please select one of the row.");
            }
        }

        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new ManageBooks();
        }


    }


}

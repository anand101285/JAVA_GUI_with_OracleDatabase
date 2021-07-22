package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewIssue implements ActionListener {
    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;

    JTable member_table;
    JTable book_table;

    JButton returnbutton;

    JButton issuenow;

    ResultSet rs;
    ResultSet rs2;

    NewIssue()
    {
        currentstaff=new Staff();
//        ArrayList<String> data=new ArrayList<>();;
        ArrayList<ArrayList<String>> row=new ArrayList<>();;
        try {
            rs =db.GetAllMembers();

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(Integer.toString(rs.getInt(1)));
                row.get(s).add(rs.getString(2));
                row.get(s).add(rs.getString(3));
                row.get(s).add(rs.getString(4));
                row.get(s).add(rs.getString(5));
                row.get(s).add(rs.getString(6));
                row.get(s).add(rs.getString(7));
                row.get(s).add(Integer.toString(rs.getInt(8)));
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

        label.setText("LIBRARY MEMBER DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"ID","Mobile Number","Name","Email","City","House no","Sector","Street"};

        DefaultTableModel defmodel= new DefaultTableModel(rows,column_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
                member_table=new JTable(defmodel);
        member_table.getTableHeader().setBackground(new Color(0x2a4422));
        member_table.getTableHeader().setForeground(new Color(0xffffff));


        JScrollPane scrollPane=new JScrollPane(member_table);

        scrollPane.setVisible(true);


        ArrayList<ArrayList<String>> row2=new ArrayList<>();;
        try {
            rs2 =db.GetAllBookWritersupplier();

            int s=0;
            while(rs2.next()) {
                row2.add(new ArrayList<>());
                row2.get(s).add(rs2.getString(1));
                row2.get(s).add(rs2.getString(2));
                row2.get(s).add(rs2.getString(3));
                row2.get(s).add(rs2.getString(4));
                row2.get(s).add(rs2.getString(5));
                row2.get(s).add(rs2.getString(6));
                s++;
            }


        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

        }



        Object[][] rows2=new Object[row2.size()][row2.get(0).size()];
        for(int i=0;i<row2.size();i++)
        {
            for(int j=0;j<row2.get(i).size();j++)
            {
                rows2[i][j]=row2.get(i).get(j);
            }
        }

        String column_name2[]={"ISBN","TITLE","NAME","ORG_NAME","PRICE","YEAR"};

        DefaultTableModel defmodel2= new DefaultTableModel(rows2,column_name2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        book_table=new JTable(defmodel2);
        book_table.getTableHeader().setBackground(new Color(0x2a4422));
        book_table.getTableHeader().setForeground(new Color(0xffffff));



        JScrollPane scrollPane2=new JScrollPane(book_table);

        scrollPane2.setVisible(true);

        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,250);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(new BorderLayout());
        optionspanel.add(scrollPane,BorderLayout.CENTER);


        JPanel optionspanel2=new JPanel();
        optionspanel2.setBounds(0,350,1000,250);
        optionspanel2.setBackground(Color.ORANGE);
        optionspanel2.setLayout(new BorderLayout());
        optionspanel2.add(scrollPane2,BorderLayout.CENTER);



        returnbutton = new JButton();
        returnbutton.setBounds(2,0,100,30);

        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);

        issuenow =new JButton();
        issuenow.setBounds(400,0,100,30);
        issuenow.setText("ISSUE");
        issuenow.setFont(new Font("Segoe Print",Font.BOLD,10));
        issuenow.setBackground(new Color(0x234A59));
        issuenow.setForeground(new Color(0xffffff));
        issuenow.addActionListener(this);





        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(returnbutton);
        end.add(issuenow);



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
        frame.add(optionspanel2);
        frame.add(end);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if(e.getSource()== issuenow)
            {
                int col=member_table.getSelectedColumn();
                int row=member_table.getSelectedRow();
                int row2=book_table.getSelectedRow();
                int col2=book_table.getSelectedColumn();

                if(row!=-1 && col!=-1 && row2!=-1 && col2!=-1) {
                    if(col2!=-1 && row2!=-1 ) {

                        System.out.println("Book selected "+book_table.getValueAt(row2,0));
                        System.out.println("Member selected "+member_table.getValueAt(row,0));
                        int error=db.AddNewIssue(Integer.parseInt(member_table.getValueAt(row,0).toString()),book_table.getValueAt(row2,0).toString());
                        if(error>0)
                        {
                            JOptionPane.showMessageDialog(null,"Sucessfully Issued book to the member");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Error");
                        }

                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please select one of the row.");
                }
            }

            else if(e.getSource()==returnbutton)
            {
                frame.dispose();
                new IssueBook();
            }


    }
}

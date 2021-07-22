package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManageWifi implements ActionListener {
    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;

    JTable member_table;

    JButton returnbutton;
    JButton removebutton;
    JButton editbutton;

    ArrayList<ArrayList<String>> row;
    ResultSet rs;

    ManageWifi()
    {
        currentstaff=new Staff();
//        ArrayList<String> data=new ArrayList<>();;
        ArrayList<ArrayList<String>> row=new ArrayList<>();;
        try {
            rs =db.GetAllWifi();

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(rs.getString(1));
                row.get(s).add(rs.getString(2));
                row.get(s).add(rs.getString(3));
                row.get(s).add(rs.getString(4));
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

        label.setText("MEMBER WIFI DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"WIFI_USERNAME","PASSWORD","STAFF NAME","MEMBER NAME"};

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

        editbutton=new JButton();
        editbutton.setBounds(400,0,100,30);
        editbutton.setText("EDIT");
        editbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        editbutton.setBackground(new Color(0x234A59));
        editbutton.setForeground(new Color(0xffffff));editbutton.addActionListener(this);


        removebutton=new JButton();
        removebutton.setBounds(790,0,200,30);
        removebutton.setText("INSERT NEW MEMBER  ");
        removebutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        removebutton.setBackground(new Color(0x234A59));
        removebutton.setForeground(new Color(0xffffff));removebutton.addActionListener(this);



        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(returnbutton);
        end.add(editbutton);
        end.add(removebutton);



        frame.setVisible(true);
        AppAccount app=new AppAccount();
        frame.setTitle("ManageWifi");
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

        if(e.getSource()==editbutton)
        {
            int col=member_table.getSelectedColumn();
            int row=member_table.getSelectedRow();

            if(row!=-1 && col!=-1) {
                Object id = member_table.getValueAt(row, 0);
                String s = id.toString();
                System.out.println(s);
                frame.dispose();
                new EditWifi(s);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please select one of the row.");
            }
        }
        else if(e.getSource()==removebutton)
        {
            frame.dispose();
            new NewMember();
        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new WifiMenu();
        }

    }
}

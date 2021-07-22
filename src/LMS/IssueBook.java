package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueBook implements ActionListener {

    JFrame frame=new JFrame();

    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;
    JTable member_table;

    JButton returnbutton;
    JButton removebutton;
    JButton addissuebutton;
    AppAccount loggedinstaff=new AppAccount();
    ResultSet rs;

    IssueBook()
    {
        currentstaff=new Staff();

        ArrayList<ArrayList<String>> row=new ArrayList<>();;
        try {
            rs =db.GetAllIssuedBooks();

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(Integer.toString(rs.getInt(1)));
                row.get(s).add(rs.getString(2));
                row.get(s).add(rs.getString(3));
                row.get(s).add(rs.getDate(4).toString());
                row.get(s).add(rs.getDate(5).toString());
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

        label.setText("LIBRARY BOOKS ISSUED TO MEMBER DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"member_id","title","member.name","issue_date","return_date"};

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

        addissuebutton =new JButton();
        addissuebutton.setBounds(400,0,200,30);
        addissuebutton.setText("ISSUE BOOK TO MEMBER");
        addissuebutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        addissuebutton.setBackground(new Color(0x234A59));
        addissuebutton.setForeground(new Color(0xffffff));
        addissuebutton.addActionListener(this);


        removebutton=new JButton();
        removebutton.setBounds(790,0,200,30);
        removebutton.setText("REMOVE");
        removebutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        removebutton.setBackground(new Color(0x234A59));
        removebutton.setForeground(new Color(0xffffff));removebutton.addActionListener(this);



        JPanel end=new JPanel();
        end.setBounds(0,600,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(returnbutton);
        end.add(addissuebutton);
        end.add(removebutton);



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
        if(e.getSource()== addissuebutton)
        {
            frame.dispose();
            new NewIssue();
        }
        else if(e.getSource()==removebutton)
        {
                               if(!db.GetPermDbDelete(loggedinstaff.getPermission_id())) {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
                return;
            }
            int col=member_table.getSelectedColumn();
            int row=member_table.getSelectedRow();

            if(row!=-1 && col!=-1) {
                Object id = member_table.getValueAt(row, 0);
                String s = id.toString();
                System.out.println(s);
                frame.dispose();
                ResultSet error=db.DeleteSpecificBookIssued(Integer.parseInt(s));
                try {
                    if(error.next())
                    {
                        JOptionPane.showMessageDialog(null,"Issue Record Successfully deleted");
                        frame.dispose();
                        new IssueBook();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                } catch (Exception
 throwables) {
                    JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

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
            new LoginMenu();
        }

    }
}

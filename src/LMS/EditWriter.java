package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditWriter implements ActionListener {
    JFrame frame=new JFrame();
    int writerid;
    DatabaseQueryGetter db=new DatabaseQueryGetter();
    Staff currentstaff;
    JButton removebutton;
    JButton editbutton;
    JButton returnbutton;
    AppAccount loggedinstaff=new AppAccount();
    JTable member_table;
    JComboBox comboBox;
    JTextField texttochange;

    ResultSet rs;


    ArrayList<ArrayList<String>> row;

    EditWriter(int wrid)
    {
        writerid =wrid;
        currentstaff=new Staff();

        row=new ArrayList<>();
        try {
            rs =db.GetSpecificWriter(wrid);

            int s=0;
            while(rs.next()) {
                row.add(new ArrayList<>());
                row.get(s).add(Integer.toString(rs.getInt(1)));
                row.get(s).add(rs.getString(2));
                row.get(s).add(Integer.toString(rs.getInt(3)));
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
        label.setText("EDITING BOOK WRITER DETAILS");

        label.setFont(new Font("Domine",Font.ROMAN_BASELINE,40));
        label.setForeground(new Color(0xffffff));label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
top.add(label);

        String column_name[]={"WID","NAME","AGE","ISBN","TITLE"};
        String sel_column_name[]={"NAME","AGE"};


        comboBox=new JComboBox(sel_column_name);
        comboBox.addActionListener(this);
        comboBox.setBounds(0,0,100,20);
        comboBox.setVisible(true);

        texttochange=new JTextField();
        texttochange.setBounds(0,21,100,20);

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
        optionspanel.setBounds(0,100,1000,200);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(new BorderLayout());
        optionspanel.add(scrollPane,BorderLayout.CENTER);


        removebutton=new JButton();
        removebutton.setBounds(500,0,100,30);
        removebutton.setText("REMOVE");
        removebutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        removebutton.setBackground(new Color(0x234A59));
        removebutton.setForeground(new Color(0xffffff));removebutton.addActionListener(this);

        editbutton=new JButton();
        editbutton.setBounds(101,21,100,30);
        editbutton.setText("EDIT");
        editbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        editbutton.setBackground(new Color(0x234A59));
        editbutton.setForeground(new Color(0xffffff));editbutton.addActionListener(this);

        returnbutton=new JButton();
        returnbutton.setBounds(770,21,100,30);
        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Segoe Print",Font.BOLD,10));
        returnbutton.setBackground(new Color(0x234A59));
        returnbutton.setForeground(new Color(0xffffff));
        returnbutton.addActionListener(this);


        JPanel end=new JPanel();
        end.setBounds(0,300,1000,100);
        end.setBackground(new Color(0x182C33));
        end.setLayout(null);
        end.add(removebutton);
        end.add(comboBox);
        end.add(texttochange);
        end.add(editbutton);
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
        if(e.getSource()==removebutton)
        {
            if(!db.GetPermDbDelete(loggedinstaff.getPermission_id())) {
                JOptionPane.showMessageDialog(null, "Sorry ! You dont have permission to view this.");
                return;
            }
            int error=db.DeleteSpecificWriter(writerid);
            try {
                if(error>0)
                {
                    JOptionPane.showMessageDialog(null,"Writer Record Deleted Successfully");
                    frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Error: Writer Record not deleted");
                }

            } catch (Exception throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }


        }
        else if (e.getSource()==editbutton)
        {

            try {
                System.out.println(row.get(0).get(0));
                int rsnew = db.UpdateSpecificWriter(comboBox.getSelectedItem().toString(),texttochange.getText(),Integer.parseInt(row.get(0).get(0)));
                System.out.println("updation done");
                if (rsnew>0) {
                    JOptionPane.showMessageDialog(null,"Writer Updated Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"ERROR:  Writer updation unsuccessful");
                }
            }
            catch (Exception throwables) {
                JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

            }

        }
        else if(e.getSource()==returnbutton)
        {
            frame.dispose();
            new ViewWriter();
        }

    }
}

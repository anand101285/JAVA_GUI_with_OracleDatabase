package LMS;

import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseQueryGetter {

    Connection con;
    DatabaseQueryGetter(){
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","lmsadmin","admin123"
            );
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
    }

    public ResultSet GetLoginData(String username) {
        ResultSet rs=null;
        Statement stm= null;
        try {
            stm = con.createStatement();
            rs=stm.executeQuery("Select * from App_Account where Login_id='"+username+"'");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }

        return rs;
    }
    public ResultSet GetStaffData(int login_id) {
        Statement smt= null;
        ResultSet rs=null;
        try {
            smt = con.createStatement();
            rs= smt.executeQuery("Select * from Staff where staff_id='"+login_id+"'");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }
        return rs;
    }

    public ResultSet GetAllMembers()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Select member_id,mobile_no,name,email,city,house_no,sector,street from Member natural join address");
            
        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetAllWifi()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select wifi_username,password,staff.name,member.name from wifi left outer join staff using (wifi_username) left outer join member using (wifi_username)");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }



    public ResultSet GetAllIssuedBooks()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select member_id,title,member.name,issue_date,return_date from (select * from issue,book where book_isbn=book.isbn) natural join member");

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");

}
        return rs;
    }

    public ResultSet GetSpecificPermission(boolean edit,boolean remove,boolean view)
    {
        ResultSet rs=null;

        int e=0;
        int v=0;
        int r=0;
        if(edit)
            e=1;
        if(remove)
            r=1;
        if(view)
            v=1;
        try {
            PreparedStatement smt=con.prepareStatement("select permission_id from permission where db_edit=? and db_view=? and db_remove=?");
            smt.setInt(1,e);
            smt.setInt(2,v);
            smt.setInt(3,r);
            smt.executeUpdate();
            rs = smt.getResultSet();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public boolean GetPermDbEdit(int permission_id)
    {
        ResultSet rs=null;
        int dbedit=0;
        try {
            PreparedStatement smt=con.prepareStatement("select db_edit from permission where permission_id=?");
            smt.setInt(1,permission_id);
            smt.executeUpdate();
            rs = smt.getResultSet();
            if(rs.next())
                dbedit=rs.getInt(1);
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        if(dbedit==1)
            return true;
        return false;
    }

    public boolean GetPermDbView(int permission_id)
    {
        ResultSet rs=null;
        int dbview=0;
        try {
            PreparedStatement smt=con.prepareStatement("select db_view from permission where permission_id=?");
            smt.setInt(1,permission_id);
            smt.executeUpdate();
            rs = smt.getResultSet();
            if(rs.next())
                dbview=rs.getInt(1);
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        if(dbview==1)
            return true;
        return false;
    }

    public boolean GetPermDbDelete(int permission_id)
    {
        ResultSet rs=null;
        int dbremove=0;
        try {
            PreparedStatement smt=con.prepareStatement("select db_remove from permission where permission_id=?");
            smt.setInt(1,permission_id);
            smt.executeUpdate();
            rs = smt.getResultSet();
            if(rs.next())
                dbremove=rs.getInt(1);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        if(dbremove==1)
            return true;
        return false;
    }




    public ResultSet GetAllBookWritersupplier()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select ISBN,book.title,writer.name,supplier.org_name,book.price,book.year from book natural join writer natural join supplier");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetAllBook()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select ISBN,title,Price,genre,edition,year,date_supplied from book");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetAllWriter()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select writer.wid,writer.name,writer.age,book.isbn,book.title from writer, book where book.wid=writer.wid");

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetAllComplaint()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select complaint_id,complaint.description from complaint,staff,member group by complaint_id,description,staff.staff_id,member.member_id having(staff.staff_id = complaint.staff_id and member.member_id = complaint.member_id)");

        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }
        return rs;
    }

    public ResultSet GetAllPublisher()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select publisher_id,publisher.name,title,isbn,address.city from publisher natural join book natural join address");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetAllSupplier()
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select distinct sid,org_name from supplier\n");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet ReflexiveSelectQuery(String query)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery(query);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }


    public ResultSet GetSpecificMember(int member_id)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Select member_id,mobile_no,name,email,city,house_no,sector,street FROM MEMBER natural join address WHERE MEMBER_ID="+member_id);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificWifiMember(String wifi_username)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("select wifi_username,password,staff.name,member.name from wifi left outer join staff using (wifi_username) left outer join member using (wifi_username) where wifi_username='"+wifi_username+"'");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificLoginData(int staff_id)
    {
        ResultSet rs=null;

        try {
            Statement smt= con.createStatement();
            rs = smt.executeQuery("select * from app_account natural join staff where staff_id="+staff_id);


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificWifiData(int staff_id)
    {
        ResultSet rs=null;

        try {
            Statement smt= con.createStatement();
            rs = smt.executeQuery("select * from staff natural join wifi where staff_id="+staff_id);


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificBook(String isbn)
    {
        ResultSet rs=null;

        try {
            PreparedStatement smt=con.prepareStatement("select ISBN,PRICE,GENRE,TITLE,EDITION,YEAR,date_supplied from book where ISBN=?");
            smt.setString(1,isbn);
            smt.executeUpdate();
            rs =smt.getResultSet();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificWriter(int wid)
    {
        ResultSet rs=null;

        try {
            PreparedStatement smt=con.prepareStatement("select writer.wid,writer.name,writer.age,book.isbn,book.title from writer, book where book.wid=writer.wid and writer.wid=?");
            smt.setInt(1,wid);
            smt.executeUpdate();
            rs =smt.getResultSet();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet GetSpecificPublisher(int pubid)
    {
        ResultSet rs=null;

        try {
            PreparedStatement smt=con.prepareStatement("select * from (select publisher_id,publisher.name,title,isbn,address.city from publisher natural join book natural join address) where publisher_id=?");
            smt.setInt(1,pubid);
            smt.executeUpdate();
            rs =smt.getResultSet();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public int UpdateSpecificMember(String column,String value,int member_id)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Update member Set "+column+"=? where member_id=?");
            smt.setString(1,value);
            smt.setInt(2,member_id);
            rs=smt.executeUpdate();
            System.out.println("after query");


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public int UpdateSpecificAccount(String column,String value,String login_id)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Update app_account Set "+column+"=? where login_id=?");
            smt.setString(1,value);
            smt.setString(2,login_id);
            rs=smt.executeUpdate();
            System.out.println("after query");

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }
    public int UpdateSpecificStaff(String column,String value,int Staff_id)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Update Staff Set "+column+"=? where Staff_id=?");
            smt.setString(1,value);
            smt.setInt(2,Staff_id);
            rs=smt.executeUpdate();
            System.out.println("after query");


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public int UpdateSpecificWifi(String column,String value,String wifi_username)
    {
        int rs=0;

        try {

            PreparedStatement smt=con.prepareStatement("Update Wifi Set "+column+"=? where Wifi_username=?");
            smt.setString(1,value);
            smt.setString(2,wifi_username);
            rs=smt.executeUpdate();
            System.out.println("after query");


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }
    public int UpdateSpecificAddress(String column,String value,int address_id)
    {
        int rs=0;
        System.out.println(value);
//
        try {

            PreparedStatement smt=con.prepareStatement("Update address Set "+column+"=? where address_id=?");

            smt.setString(1,value);
            smt.setInt(2,address_id);
//            System.out.println(s);
            rs=smt.executeUpdate();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public int UpdateSpecificBook(String column,String value,String isbn)
    {
        int rs=0;
        System.out.println(value);
//
        try {

            PreparedStatement smt=con.prepareStatement("Update book Set "+column+"=? where isbn=?");

            if(column.toLowerCase().equals("date_supplied"))
                smt.setDate(1,java.sql.Date.valueOf(value));
            else
                smt.setString(1,value);
            smt.setString(2,isbn);
//            System.out.println(s);
            rs=smt.executeUpdate();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public int UpdateSpecificWriter(String column,String value,int wid)
    {
        int rs=0;
        System.out.println(value);
//
        try {

            PreparedStatement smt=con.prepareStatement("Update Writer Set "+column+"=? where wid=?");

            smt.setString(1,value);
            smt.setInt(2,wid);
//            System.out.println(s);
            rs=smt.executeUpdate();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public int UpdateSpecificPublisher(String column,String value,int pubid)
    {
        int rs=0;
        System.out.println(value);
//
        try {

            PreparedStatement smt=con.prepareStatement("Update Writer Set "+column+"=? where publisher_id=?");

            smt.setString(1,value);
            smt.setInt(2,pubid);
//            System.out.println(s);
            rs=smt.executeUpdate();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        System.out.println("before return");

        return rs;
    }

    public ResultSet AddNewAddress(String Columngetting,String city, String house_no,String sector,int street)
    {
        System.out.println("Inserting address");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String address_add="Insert into address(city,house_no,sector,street) values(?,?,?,?)";
            System.out.println(address_add);
            smt = con.prepareStatement(address_add,new String[]{Columngetting});
            smt.setString(1,city);
            smt.setString(2,house_no);
            smt.setString(3,sector);
            smt.setInt(4,street);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public ResultSet AddNewMember(String Columngetting,String email,String name,String mobile_no,int address_id)
    {
        System.out.println("Inserting member");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String address_add="Insert into member(email,name,mobile_no,address_id) values(?,?,?,?)";
            System.out.println(address_add);
            smt = con.prepareStatement(address_add,new String[]{Columngetting});
            smt.setString(1,email);
            smt.setString(2,name);
            smt.setString(3,mobile_no);
            smt.setInt(4,address_id);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }


    public ResultSet AddNewStaff(String Columngetting,String name,int address_id)
    {
        System.out.println("Inserting member");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String StaffAdd="Insert into Staff(name,address_id) values(?,?)";
            System.out.println(StaffAdd);
            smt = con.prepareStatement(StaffAdd,new String[]{Columngetting});
            smt.setString(1,name);
            smt.setInt(2,address_id);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public int AddNewIssue(int memberid,String book_isbn)
    {
        System.out.println("Issuing book to user");
        int rs=0;
        PreparedStatement smt= null;
        try {

            smt = con.prepareStatement("insert into issue(member_id,book_isbn,issue_date,Return_Date) values(?,?,?,?)");
            smt.setInt(1,memberid);
            smt.setString(2,book_isbn);
            smt.setDate(3,java.sql.Date.valueOf(java.time.LocalDate.now()));
            smt.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(15)));

            rs = smt.executeUpdate();

            System.out.println("getting address_done");
        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public ResultSet AddNewAppAccount(String Columngetting,String account_id,String pass,int staff_id,int permission)
    {
        System.out.println("Inserting account");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String StaffAdd="Insert into App_Account(Login_id,Password,Staff_id,permission_id) values(?,?,?,?)";
            System.out.println(StaffAdd);
            smt = con.prepareStatement(StaffAdd,new String[]{Columngetting});
            smt.setString(1, account_id);
            smt.setString(2, pass);
            smt.setInt(3,staff_id);
            smt.setInt(4,permission);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public ResultSet AddNewPublisher(String Columngetting,String name, int address_id)
    {
        System.out.println("Inserting member");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String publisher_add="insert into publisher(name,address_id) values(?,?)";
            System.out.println(publisher_add);
            smt = con.prepareStatement(publisher_add,new String[]{Columngetting});
            smt.setString(1,name);
            smt.setInt(2,address_id);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public ResultSet AddNewWriter(String Columngetting,String name,int age, int address_id)
    {
        System.out.println("Inserting member");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String writer_add="insert into writer(Name,Age,Address_id) values(?,?,?)";
            System.out.println(writer_add);
            smt = con.prepareStatement(writer_add,new String[]{Columngetting});
            smt.setString(1,name);
            smt.setInt(2,age);
            smt.setInt(3,address_id);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}

        return rs;

    }

    public ResultSet AddNewBook(String Columngetting,String ISBN,int price,String genre,String title,int edition,int wid,int sid,int pubid)
    {
        System.out.println("Inserting member");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String writer_add="insert into book(ISBN,price,genre,title,edition,wid,sid,publisher_id,year,date_supplied) values(?,?,?,?,?,?,?,?,?,?)";
            System.out.println(writer_add);
            smt = con.prepareStatement(writer_add,new String[]{Columngetting});
            smt.setString(1,ISBN);
            smt.setInt(2,price);
            smt.setString(3,genre);
            smt.setString(4,title);
            smt.setInt(5,edition);
            smt.setInt(6,wid);
            smt.setInt(7,sid);
            smt.setInt(8,pubid);
            smt.setString(9,Integer.toString(java.time.LocalDate.now().getYear()));
            smt.setDate(10,java.sql.Date.valueOf(java.time.LocalDate.now()));
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }

        return rs;

    }

    public ResultSet AddNewComplaint(int memberid,int stadffid, String description)
    {
        System.out.println("Inserting complaint");
        ResultSet rs=null;

        PreparedStatement smt= null;
        try {

            String writer_add="insert into complaint(description,staff_id,member_id) values(?,?,?)";
            System.out.println(writer_add);
            smt = con.prepareStatement(writer_add,new String[]{"Complaint_id"});
            smt.setString(1,description);
            smt.setInt(2,stadffid);
            smt.setInt(3,memberid);
            smt.executeUpdate();
            System.out.println("insert done");
            rs =smt.getGeneratedKeys();

            System.out.println("getting address_done");
        } catch (Exception throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }

        return rs;

    }


    public ResultSet DeleteSpecificMember(int member_id)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Delete from member where member_id="+member_id);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet DeleteSpecificComplaint(int complaint_id)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Delete from Complaint where complaint_id="+complaint_id);

        } catch (Exception
                throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
        }
        return rs;
    }

    public ResultSet DeleteSpecificMember(String wifi_username)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Delete from wifi where wifi_username="+wifi_username);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }


    public ResultSet DeleteSpecificBookIssued(int member_id)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Delete from Issue where member_id="+member_id);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public ResultSet DeleteSpecificStaff(int staff_id)
    {
        ResultSet rs=null;

        try {
            Statement smt=con.createStatement();
            rs=smt.executeQuery("Delete from Staff where Staff_id="+staff_id);

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }
    public int DeleteSpecificWifi(String wifi_username)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Delete from Wifi where wifi_username=?");
            smt.setString(1,wifi_username);
            rs=smt.executeUpdate();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }


    public int DeleteSpecificBook(String isbn)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Delete from book where book.isbn=?");
            smt.setString(1,isbn);
            rs=smt.executeUpdate();

            System.out.println("Deleteion completed");


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public int DeleteSpecificWriter(int wid)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Delete from writer where wid=?");
            smt.setInt(1,wid);
            rs =smt.executeUpdate();
            smt.getResultSet();

        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }

    public int DeleteSpecificPublisher(int pubid)
    {
        int rs=0;

        try {
            PreparedStatement smt=con.prepareStatement("Delete from publisher where publisher_id=?");
            smt.setInt(1,pubid);
            rs =smt.executeUpdate();


        } catch (Exception
 throwables) {
            JOptionPane.showMessageDialog(null,"Error:500 Internal Server Error");
}
        return rs;
    }



}

package LMS;

import java.sql.Connection;
import java.sql.DriverManager;

public class RoughCode {

    public void TestCode()
    {
        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","lmsadmin","admin123"
            );

            String[] genre={"","Historical","Romance","Historical","Fantasy","Adventure"};
            String[] title={"","To Kill a Mockingbird","A Star is Born","Outlander","The saviour's champion","Treasure Island"};
            String[] writer={"","Harper Lee","Alexander Edwards"," Diana Gabaldon","Jenna Moreci","Robert Louis Stevenson"};
            int[] year={0,1960,2018,1991,2018,1883};


//            Statement smt=con.createStatement();



//            for(int i=1;i<title.length;i++) {
//                PreparedStatement stmt = con.prepareStatement("Insert into WRITER(Name,Age,Address_ID) values(?,?,?)");
//                //writer insert
//                stmt.setString(1, writer[i]);
//                stmt.setInt(2, (int) (Math.random() * (50 + 18 + 1) + 18));
//                stmt.setInt(3, (int) (Math.random() * (10 + 0 + 1) + 0));
//                stmt.execute();
//            }

            System.out.println("Enter into loop");
//            for(int i=1;i<6;i++) {
//
//                String stmt = "Insert into Book(ISBN,PRICE,GENRE,TITLE,EDITION,WRITER_WID,SUPPLIER_SID,PUBLISHER_ID,YEAR,DATE_SUPPLIED) values('978-3-16-148410-"+Integer.toString(i-1)+"',"+"'"+(int)(Math.random()*(10000-2000+1)+2000)+"'"+",'"+genre[i]+"','"+title[i]+"',"+(int)(Math.random()*(11-2+1)+2)+","+i+","+(int)(Math.random()*(3-1+1)+1)+","+(int)(Math.random()*(5-1+1)+1)+","+year[i]+","+"TO_DATE("+(int)(Math.random()*(2020-2000+1)+2000)+"-"+(int)(Math.random()*(13-1+1)+1)+"-"+(int)(Math.random()*(31-1+1)+1)+",'yyyy-mm-dd'))";
//
//
//                //publisher insert
////                stmt.setString(1,"978-3-16-148410-"+Integer.toString(i-1));
////                stmt.setInt(2,(int)(Math.random()*(10000-2000+1)+2000));
////                stmt.setString(3,genre[i]);
////                stmt.setString(4,title[i]);
////                stmt.setInt(5,(int)(Math.random()*(11-2+1)+2));
////                stmt.setInt(6,i);
////                stmt.setInt(7,(int)(Math.random()*(4-1+1)+1));
////                stmt.setInt(8,(int)(Math.random()*(5-1+1)+1));
////                stmt.setString(9,Integer.toString(year[i]));
////                stmt.setDate(10,java.sql.Date.valueOf("2013-09-04"));
////                stmt.execute();
//                System.out.println(stmt);
//                System.out.println("test");
//            }
//            for(int i=0;i<9;i++) {
//                PreparedStatement stmt= con.prepareStatement("Insert into address(City,House_no,Sector,street) values(?,?,?,?)");
//                stmt.setString(1,city[(int)(Math.random()*(2+0+1)+0)]);
//                stmt.setString(2,Integer.toString((int)(Math.random()*(100+30+1)+30)));
//                stmt.setString(3,sector[(int)(Math.random()*(2+0+1)+0)]+"-"+Integer.toString((int)(Math.random()*(10+5+1)+5))+"/1");
//                stmt.setInt(4,(int)(Math.random()*(100+1+1)+1));
//                stmt.execute();
//            }

//            ResultSet rs=smt.executeQuery("select * from address;");
//
//            while(rs.next())
//            {
//                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)
//                );
//            }
            con.close();


        } catch (Exception e) {
            e.printStackTrace();


        }

    }

}

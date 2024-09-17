import java.sql.*;

public class DatabaseManager {
    private String url ="jdbc:mysql://localhost:3306/hotel_db";
    private String userName ="root";
    private String password ="khan4533";
    Connection con;
    Statement sta;
    ResultSet rs;


    public void driverLoad(){
        try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              System.out.print("Driver Loaded");
           } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void connection(){
        String selectQuery ="select * from reservation";
        try {
              con  = DriverManager.getConnection(url,userName,password);
              System.out.print("Mysql is Connected");
              sta = con.createStatement();
              rs = sta.executeQuery(selectQuery);
              while (rs.next()){
                  int id = rs.getInt("rv_id");
                  String guest_name = rs.getString("guest_name");
                  String room_number = rs.getString("room_number");
                  String contact_number = rs.getString("contact_number");
                  Date rv_date = rs.getDate("reservation_date");

                  System.out.print("\n======================================");
                  System.out.print("\nID = "+id);
                  System.out.print("\nName = "+guest_name);
                  System.out.print("\nRoom Number = "+room_number);
                  System.out.print("\nContact Number = "+contact_number);
                  System.out.print("\nDate = "+rv_date);
              }
              rs.close();
              sta.close();
              con.close();
           } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

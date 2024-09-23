import java.sql.*;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private  static HotelReservation hr;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hotel_db";
        String userName = "root";
        String password = "khan4533";
        Connection con;
        DatabaseManager db = new DatabaseManager();
        db.driverLoad();
       hr = new HotelReservation();
//      db.connection();
        try {
            con = DriverManager.getConnection(url, userName, password);
            while (true) {
                System.out.print("\nHOTEL MANAGEMENT SYSTEM");
                Scanner scan = new Scanner(System.in);
                System.out.print("\n1: Reserve a room");
                System.out.print("\n2: View Reservetion");
                System.out.print("\n3: Get Room Number");
                System.out.print("\n4: Update Reservetions");
                System.out.print("\n5: Delete Reservetions");
                System.out.print("\n0: Exit.");
                System.out.print("\n Chose an Option = ");

                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        db.reseverRoom(con, scan);
                        break;
                    case 2:
                        db.viewReservation(con);
                        break;
                    case 3:
                        db.getRoomNumber(con,scan);
//                        System.out.print("\nGet Room Number");
                        break;
                    case 4:
                        db.updateReservatin(con,scan);
//                        System.out.print("\nUpdate Reservetions");
                        break;
                    case 5:
                        db.deleteReservation(con,scan);
//                        System.out.print("\nDeletion Reservation");
                        break;
                    case 0:
                        db.exit();
                        break;
                    default:
                        System.out.print("\nNot avalible in list");
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
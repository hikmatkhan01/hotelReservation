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
                        reseverRoom(con, scan);
                        break;
                    case 2:
                        viewReservation(con);
                        break;
                    case 3:
                        getRoomNumber(con,scan);
//                        System.out.print("\nGet Room Number");
                        break;
                    case 4:
                        updateReservatin(con,scan);
//                        System.out.print("\nUpdate Reservetions");
                        break;
                    case 5:
                        deleteReservation(con,scan);
//                        System.out.print("\nDeletion Reservation");
                        break;
                    case 0:
                        exit();
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

    private static void reseverRoom(Connection con, Scanner scan) {

        System.out.print("Enter Guest Name : ");
          hr.setGuest_name(scan.next());
        System.out.print("\nEnter Room Number : ");
          hr.setRoom_num(String.valueOf(scan.nextInt()));
        System.out.print("\nEnter Contact Number : ");
          hr.setContact_number(scan.next());
        String insertQuer = "INSERT INTO reservation (guest_name, room_number, contact_number) VALUES ('" + hr.getGuest_name() + "', '" + hr.getRoom_num() + "', '" + hr.getContact_number() + "');";
        try {
            Statement sta = con.createStatement();
            int rowAffect = sta.executeUpdate(insertQuer);
            if (rowAffect > 0) {
                System.out.print("Successfuly Inserted!");
            } else {
                System.out.print("Not Inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void viewReservation(Connection con) {
        String selectQuery = "select * from reservation";
        try (Statement sta = con.createStatement();
             ResultSet rs = sta.executeQuery(selectQuery)) {
            System.out.println("Current Reservations: ");
            System.out.println("+----------------+----------+--------------+-------------------+--------------------+");
            System.out.println("| Reservation ID | Guest    | Room Number  | Contact Number    | Reservation Date   |");
            System.out.println("+----------------+----------+--------------+-------------------+--------------------+");
            while (rs.next()) {
//                int rv_id = rs.getInt("rv_id");
                hr.setReservation_id(rs.getInt("rv_id"));

                hr.setGuest_name(rs.getString("guest_name"));

                hr.setRoom_num(rs.getString("room_number"));

                hr.setContact_number(rs.getString("contact_number"));
                //Date rv_date = rs.getDate("reservation_date");

                hr.setReservation_date(String.valueOf(rs.getDate("reservation_date")));
                System.out.printf("| %-15d | %-10s | %-12s | %-17s | %-18s |\n",
                        hr.getReservation_id(), hr.getGuest_name(), hr.getRoom_num(), hr.getContact_number(),hr.getReservation_date());
            }
            rs.close();
            sta.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void getRoomNumber(Connection con, Scanner scan){
        System.out.print("Enter Reservation ID: ");
//        int reservationId = scan.nextInt();
        hr.setReservation_id(scan.nextInt());
        System.out.print("Enter guest name: ");
//        String guestName = scan.next();
        hr.setGuest_name(scan.next());
        String getRoom = "SELECT room_number FROM reservation WHERE rv_id = " + hr.getReservation_id() +
                " AND guest_name = '" + hr.getGuest_name() + "'";
        try (Statement sta = con.createStatement()){
            ResultSet rs = sta.executeQuery(getRoom);
             if(rs.next()){
                 int roomNumber = rs.getInt("room_number");
                 System.out.print("Room number for Reservation ID " +hr.getReservation_id()+ " and Guest "
                 +hr.getGuest_name() + " is: " +hr.getRoom_num());
             }else {
                 System.out.print("Reservation not found for the given ID and guest name.");
             }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    private static void updateReservatin(Connection con, Scanner scan){
        try {
               System.out.print("Enter reservation ID to update: ");
              //int rv_id = scan.nextInt();
               hr.setReservation_id(scan.nextInt());
               scan.nextLine();
               if(!reservationExist(con, hr.getReservation_id())){
                   System.out.println("Reservation not found for the given ID.");
                   return;
               }
               System.out.println("Enter new guest name");
//               String newGuestName = scan.nextLine();
               hr.setGuest_name(scan.nextLine());
               System.out.print("Enter new room number");
//               int newRoomNumber = scan.nextInt();
               hr.setRoom_num(String.valueOf(scan.nextInt()));
               System.out.print("Enter new contact number: ");
//               String newContactNumber = scan.next();
               hr.setContact_number(scan.next());
               String updateQuery = "update reservation set guest_name = '"+hr.getGuest_name()+"', " +
                       "room_number = "+hr.getRoom_num()+", "+"contact_number ='"+hr.getContact_number()+"' " +
                        " WHERE rv_id = " +hr.getReservation_id();
               try (Statement sta = con.createStatement()){
                    int rowAffects = sta.executeUpdate(updateQuery);
                    if(rowAffects>0){
                        System.out.println("Reservation Updated successfuly!");
                    }else {
                        System.out.println("Reservation update failed.");
                    }
               }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    private static void deleteReservation(Connection con, Scanner scan){
        try {
            System.out.print("Enter  reservation  ID to delete: ");
//            int rv_id = scan.nextInt();
            hr.setReservation_id(scan.nextInt());
            if(!reservationExist(con,hr.getReservation_id())){
                System.out.println("Reservation not found for the given ID.");
                return;
            }
            String delteQuery = "Delete from reservation WHERE rv_id = "+hr.getReservation_id();
            try (Statement sta = con.createStatement()){
              int  affectRows = sta.executeUpdate(delteQuery);

              if(affectRows>0){
                  System.out.println("Record Deleted by");
              }else {
                  System.out.print("Not Deleted!!!");
              }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }


    }
    private static boolean reservationExist(Connection con,int rv_id){
        try {
            String chekReservation ="select rv_id from reservation WHERE rv_id = "+rv_id;
            try (Statement sta = con.createStatement()){
                ResultSet rs = sta.executeQuery(chekReservation);
                return  rs.next();//if there is a result,the reservation exists
            }
        }catch (SQLException e){
            System.out.print(e.getMessage());

        }
        return false;
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while (i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Thank You For Using Hotel Reservation System!!!");
    }
}
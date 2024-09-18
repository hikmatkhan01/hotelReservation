public class HotelReservation {
    private int reservation_id;
    private String guest_name;
    private String room_num;
    private String contact_number;
    private String reservation_date;

    public HotelReservation(){

    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    @Override
    public String toString() {
        return "HotelReservation{" +
                "reservation_id=" + reservation_id +
                ", guest_name='" + guest_name + '\'' +
                ", room_num='" + room_num + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", reservation_date='" + reservation_date + '\'' +
                '}';
    }
}

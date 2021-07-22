package LMS;

public class Staff {

    private static int Staff_id;
    private static String name;
    private static String wifi_username;
    private static int address_id;

    public Staff(int staff_id, String name, String wifi_username, int address_id) {
        Staff_id = staff_id;
        this.name = name;
        this.wifi_username = wifi_username;
        this.address_id = address_id;
    }

    Staff()
    {}
    public int getStaff_id() {
        return Staff_id;
    }

    public void setStaff_id(int staff_id) {
        Staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWifi_username() {
        return wifi_username;
    }

    public void setWifi_username(String wifi_username) {
        this.wifi_username = wifi_username;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
    public void clear()
    {
        Staff_id=0;
        name="";
        wifi_username="";
        address_id=0;
    }
}

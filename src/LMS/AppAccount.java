package LMS;

public class AppAccount {

    private static String LOGIN_ID;
    private static String password;
    private static int Staff_id;
    private static int Permission_id;

    public AppAccount(String LOGIN_ID, String password, int staff_id, int permission_id) {
        this.LOGIN_ID = LOGIN_ID;
        this.password = password;
        Staff_id = staff_id;
        Permission_id = permission_id;
    }

    AppAccount()
    {}
    public String getLOGIN_ID() {
        return LOGIN_ID;
    }

    public void setLOGIN_ID(String LOGIN_ID) {
        this.LOGIN_ID = LOGIN_ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStaff_id() {
        return Staff_id;
    }

    public void setStaff_id(int staff_id) {
        Staff_id = staff_id;
    }

    public int getPermission_id() {
        return Permission_id;
    }

    public void setPermission_id(int permission_id) {
        Permission_id = permission_id;
    }

    public void clear()
    {
        LOGIN_ID="";
        password="";
        Staff_id=0;
        Permission_id=0;
    }

}

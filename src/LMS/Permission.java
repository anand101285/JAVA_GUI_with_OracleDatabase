package LMS;

public class Permission {
    private int permission_id;
    private boolean DB_view;
    private boolean DB_edit;
    private boolean DB_remove;


    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public boolean isDB_view() {
        return DB_view;
    }

    public void setDB_view(boolean DB_view) {
        this.DB_view = DB_view;
    }

    public boolean isDB_edit() {
        return DB_edit;
    }

    public void setDB_edit(boolean DB_edit) {
        this.DB_edit = DB_edit;
    }

    public boolean isDB_remove() {
        return DB_remove;
    }

    public void setDB_remove(boolean DB_remove) {
        this.DB_remove = DB_remove;
    }
}

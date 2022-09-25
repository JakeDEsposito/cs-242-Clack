import java.util.Date;

public abstract class ClackData {
    private String username;
    private int type;

    private Date date;

    public ClackData(String u, int t) {
        username=u;
        type=t;
        date=new Date();
    }

    public ClackData(int t) {
    }

    public ClackData() {
    }

    public int getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate(){
        return date;
    }

    public abstract void getData();
}
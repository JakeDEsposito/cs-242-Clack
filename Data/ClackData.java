import java.time.LocalDateTime;

public class ClackData {
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
    }

    public String getUsername() {
    }

    public Date getDate(){
    }

    public abstract getData();
}
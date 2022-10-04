import java.util.Date;

public abstract class ClackData {
    private String username;
    private int type;

    private Date date;

    public ClackData(String u, int t) {
        this.username=u;
        this.type=t;
        this.date=new Date();
    }

    public ClackData(int t) { this("Anon", t); }

    public ClackData() { this(1); }

    public int getType() { return type; }

    public String getUsername() {
        return username;
    }

    public Date getDate(){
        return date;
    }

    public abstract String getData();
}
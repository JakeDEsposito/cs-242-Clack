import java.util.Objects;

public class MessageClackData extends ClackData {
    private String message;

    public MessageClackData(String u, String m, int t) {
        super(u, t);
        this.message=m;
    }

    public MessageClackData() { super(); }

    @Override
    public String getData() { return message; }

    @Override
    public int hashCode() {
        return Objects.hash(message, getDate(), getType(), getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageClackData)) return false;
        MessageClackData that = (MessageClackData) o;
        return message.equals(that.message);
    }

    @Override
    public String toString() {
        return "MessageClackData{" +
                "message='" + message + '\'' +
                ", type=" + getType() +
                ", username='" + getUsername() + '\'' +
                ", date=" + getDate() +
                '}';
    }
}
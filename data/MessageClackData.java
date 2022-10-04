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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
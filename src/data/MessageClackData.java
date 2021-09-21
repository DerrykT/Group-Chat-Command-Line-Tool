package data;

public class MessageClackData extends ClackData{
    private String message;

    public MessageClackData( String userName, String message, int type ) {
        super ( userName, type );
        this.message = message;
    }

    public MessageClackData () {
        super(); //not sure if this is correct, maybe use MessageClackData instead??
    }

    public String getData() {
        return this.message;
    }


    //hasCode, equals, and toString are not finished
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

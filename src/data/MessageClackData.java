package data;

import java.text.SimpleDateFormat;

public class MessageClackData extends ClackData{
    private String message;

    public MessageClackData( String userName, String message, int type ) {
        super ( userName, type );
        this.message = message;
    }

    public MessageClackData () {
        super();
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
        return this.toString() == obj.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.message;
    }


}

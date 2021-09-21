package data;

import java.util.Date;

public class ClackData {
    private String userName;
    private int type;
    private Date date;

    public ClackData( String userName, int type ) {
        this.userName = userName;
        this.type = type;
        this.date = new Date(System.currentTimeMillis());
    }

    public ClackData( int type ) {
        new ClackData( "Anon", type );
    }

    public ClackData() {
        new ClackData( 0 );
    }

    public String getUserName() {
        return userName;
    }

    public int getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

//    public ClackData getData() {
//        return new ClackData( this.userName, this.type );
//    }

}

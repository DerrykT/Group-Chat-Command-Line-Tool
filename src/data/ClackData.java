package data;

import java.text.SimpleDateFormat;
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
        this( "Anon", type );
    }

    public ClackData() {
        this( 0 );
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

    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return "" + this.userName + "," + this.type + "," + formatter.format(this.date);
    }
}

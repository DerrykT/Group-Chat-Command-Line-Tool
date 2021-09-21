package data;

import java.text.SimpleDateFormat;

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    public FileClackData( String userName, String fileName, int type ) {
        super( userName, type );
        this.fileName = fileName;
        this.fileContents = null;
    }

    public FileClackData() {
        super();
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getData() {
        return this.fileContents;
    }

    public void readFileContents() {
        //No Code for Part 1
    }

    public void writeFileContents() {
        //No Code for Part 1
    }

    //Need to finish hashCode, equals, and toString
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

    }

    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return super.toString() + "," + this.fileName + "," + this.fileContents;
    }
}

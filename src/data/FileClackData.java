package data;

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
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

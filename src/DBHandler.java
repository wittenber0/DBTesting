import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by FMF 7 on 5/10/2017.
 */
public class DBHandler {

    private Connection connection;

    public DBHandler(Connection c){
        connection = c;
    }

    public DBHandler() throws Exception{
        open();
        createTables();
    }

    public void open() throws Exception{
        try{
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            connection = DriverManager.getConnection("jdbc:derby:db;create=true");
        }catch(SQLException e){
            System.out.print("Connection Failed");
            throw new Exception();
        }
        System.out.println("DB Registered and Connected");
    }

    /*public void findDB() throws Exception{
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("DB Exists");
        }catch (ClassNotFoundException e){
            open();
        }
    }*/

    public void createTables() throws Exception{
        Statement s = connection.createStatement();
        try {
            Boolean w = s.execute("CREATE TABLE Persons (\n" +
                    " PersonID int,\n" +
                    " LastName varchar(255),\n" +
                    " FirstName varchar(255),\n" +
                    " Address varchar(255),\n" +
                    " City varchar(255) \n" +
                    ")");
        }catch(Exception e){
            System.out.println("tables already set");
        }
        Boolean q = s.execute("INSERT INTO Persons VALUES (2, 'Wittenberg', 'Ryan', 'NH', 'Bedford')");

        String rowsQ = "SELECT COUNT(*) FROM Persons";
        ResultSet rowsR = s.executeQuery(rowsQ);

        if(rowsR.next()) {
            System.out.println("Number of rows in table Persons " + rowsR.getString(1));
        }


        String allPersonsSQL = "SELECT * FROM Persons";
        ResultSet allpersonsRS = s.executeQuery(allPersonsSQL);

        int rows = 0;
        ArrayList rowEntries = new ArrayList();

        FileWriter f = new FileWriter("test.csv");
        while (allpersonsRS.next()){
            String aRow = "";
            aRow += allpersonsRS.getString(1);
            aRow += allpersonsRS.getString(2);
            aRow += allpersonsRS.getString(3);
            aRow += allpersonsRS.getString(4);
            aRow += allpersonsRS.getString(5);

        }

        f.flush();
        f.close();
        System.out.println(rowEntries);

    }


}

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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

        //FileWriter f = new FileWriter("test2.csv");
        while (allpersonsRS.next()){
            String aRow = "";
            aRow += allpersonsRS.getString(1);
            aRow += ",";
            aRow += allpersonsRS.getString(2);
            aRow += ",";
            aRow += allpersonsRS.getString(3);
            aRow += ",";
            aRow += allpersonsRS.getString(4);
            aRow += ",";
            aRow += allpersonsRS.getString(5);
            aRow += "\n";
            //f.append(aRow);
        }

        Scanner scanner = new Scanner(new File("test.csv"));

        String ya = scanner.next();
        /*scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+"|");
        }*/
        scanner.close();

        System.out.println(ya);
        //f.flush();
        //f.close();

    }


}

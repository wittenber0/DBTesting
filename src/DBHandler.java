import java.sql.*;

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
        /*Boolean w = s.execute("CREATE TABLE Persons (\n" +
                " PersonID int,\n" +
                " LastName varchar(255),\n" +
                " FirstName varchar(255),\n" +
                " Address varchar(255),\n" +
                " City varchar(255) \n" +
                ")");*/
        //Boolean w = s.execute("INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (1, 'Wittenberg', 'Ryan', 'NH', 'Bedford')");
        String one = "SELECT * FROM Persons";
        ResultSet r = s.executeQuery(one);
        if(r.next()) {
            System.out.println(r.getString("LastName"));
        }
    }


}

package DB;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private final static String dbserver = "localhost";
    private final static int dbport = 3306;
    private final static String dbname = "travelagency";
    private final static String dbuser = "root";
    private final static String dbpass ="";
    private final static String url = "jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?useSSL=false";
    private static String query = "";
    private static DatabaseManager instance = null;
    private static Connection con = null;

    public DatabaseManager()
    {

    }

    public static DatabaseManager getInstance(){
        return instance;
    }

    private void verbinden(String url,String dbuser,String dbpass,String treiber)throws Exception{
        Class.forName(treiber);
        con = DriverManager.getConnection(url,dbuser,dbpass);

    }



    public static void verbinden() throws Exception{
          instance = new DatabaseManager();
          instance.verbinden(url,dbuser,dbpass,"com.mysql.jdbc.Driver");
     }

     public ResultSet ausfuehren(String query)throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.execute();

        return preparedStatement.getResultSet();
     }

    public ResultSet ausfuehren(PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }

    public ArrayList<FlightCustomer> getAllCustomer()throws SQLException{
        ArrayList<FlightCustomer> arrayList =  new ArrayList<>();

        query="select * from flightcustomer";
        ResultSet rs = ausfuehren(query);
        while (rs.next()){
            String passport_number = rs.getString(1);
            String name = rs.getString(2);
            String gender= rs.getString(3);
            String nationality = rs.getString(4);
            arrayList.add(new FlightCustomer(passport_number,name,gender,nationality));
        }
        return arrayList;
    }

    public void deleteCustomer(FlightCustomer customer)throws SQLException{
       query="update flightcustomer set name= ? , gender= = ? ,nationality= ? where passport_number = ? ";
        PreparedStatement preparedStatement = con.prepareStatement(query);

      preparedStatement.setString(4,customer.getPassportNumber());
      preparedStatement.setString(1,customer.getName());
      preparedStatement.setString(2,customer.getGender());
      preparedStatement.setString(3,customer.getNationality());

        ausfuehren(preparedStatement);

    }

    public void insertCustomer(FlightCustomer customer)throws SQLException{
        query="insert into flightcustomer VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);

        preparedStatement.setString(1,customer.getPassportNumber());
        preparedStatement.setString(2,customer.getName());
        preparedStatement.setString(3,customer.getGender());
        preparedStatement.setString(4,customer.getNationality());

        ausfuehren(preparedStatement);

    }

}

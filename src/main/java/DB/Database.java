package DB;


import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Database {
    private static Connection con = null;
    private static String dbHost = "localhost"; // Hostname
    private static String dbPort = "3306";      // Port -- Standard: 3306
    private static String dbName = "travelagency";   // Datenbankname
    private static String dbUser = "root";     // Datenbankuser
    private static String dbPass = "root";      // Datenbankpasswort


    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC Schnittstellen laden.

            // Verbindung zur JDBC-Datenbank herstellen.
            con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&" + "password=" + dbPass);
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
        } catch (SQLException e) {
            System.out.println("Verbindung nicht moglich");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private static Connection getInstance() {
        if (con == null)
            new Database();
        return con;
    }

    //Gebe Tabelle in die Konsole aus
    public void selectAllData() {
        con = getInstance();

        if (con != null) {
            // Abfrage-Statement erzeugen.
            Statement query;
            try {
                query = con.createStatement();

                // Tabelle anzeigen
                String sql =
                        "SELECT passport_number,name,gender,nationality FROM flightcustomer";
                ResultSet result = query.executeQuery(sql);

                // Ergebnisstabelle durchforsten
                int i = 1;
                while (result.next()) {
                    String passport_number = result.getString("passport_number");
                    String name = result.getString("name");
                    String gender = result.getString("gender");
                    String nationality = result.getString("nationality");
                    String info =

                            "Passport number [" + passport_number + "]\n" +
                                    "Name            [" + name + "]\n" +
                                    "Gender          [" + gender + "]\n" +
                                    "Nationality     [" + nationality + "]\n" +
                                    "Rows:           [" + i++ + "]\n";

                    System.out.println(info);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCustomer() {
        con = getInstance();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE from flightcustomer WHERE passport_number = ? AND name= ?";

        try {
            dbConnection = getInstance();
            preparedStatement = dbConnection.prepareStatement(deleteSQL);

            Scanner scanner = new Scanner(System.in);

            System.out.print("which passport_number would you like to delete? :  ");
            String passport_number = scanner.nextLine();
            preparedStatement.setString(1, passport_number);

            System.out.print("which customer would you like to delete? :  ");
            String name = scanner.nextLine();
            preparedStatement.setString(2, name);

            int rowsUpdated = preparedStatement.executeUpdate();


            System.out.print("\nYOUR INPUT:\n\n"
                    + "Passport number: " + "[" + passport_number + "] \n"
                    + "Name: " + "           [" + name + "]\n"
                    + "");

            if (rowsUpdated == 0) {
                System.out.println("\nCustomer is not exist or wrong input!! Please check your input and try again!\n");
            } else {
                System.out.println("An existing user was deleted!");
                System.out.println("Record is deleted!\n\n");
                selectAllData();
            }
            preparedStatement.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public void insertColumn() {
        con = getInstance();

        if (con != null) {
            // Abfrage-Statement erzeugen.
            Statement showData;
            try {

                String query = "insert into flightcustomer(passport_number,name,gender,nationality) VALUES(?,?,?,?)";

                while (true) {

                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Passport numner? : ");
                    String passport_number = scanner.nextLine();

                    System.out.print("your name? : ");
                    String name = scanner.nextLine();

                    System.out.print("gender? : ");
                    String gender = scanner.nextLine();

                    System.out.print("nationality? : ");
                    String nationality = scanner.nextLine();


                    PreparedStatement preparedStmt = con.prepareStatement(query);

                    preparedStmt.setString(1, passport_number);
                    preparedStmt.setString(2, name);
                    preparedStmt.setString(3, gender);
                    preparedStmt.setString(4, nationality);


                    Boolean rowsInserted = preparedStmt.execute();


                    System.out.print("\nYOUR INPUT:\n\n"
                            + "Passport number: " + "[" + passport_number + "] \n"
                            + "Name: " + "           [" + name + "]\n"
                            + "Gender: " + "         [" + gender + "] \n"
                            + "Nationality: " + "    [" + nationality + "]\n\n"
                            + "");

                    if (rowsInserted) {
                        System.out.println("An existing user was not inserted! Please check your input and try again!\n");
                    } else {
                        System.out.println("An existing user was inserted!");
                        selectAllData();
                    }
                    preparedStmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    public void updateFlightCustomer() {
        con = getInstance();

        if (con != null) {
            try {

                while (true) {

                    Scanner scanner = new Scanner(System.in);

                    String query = "UPDATE flightcustomer SET  name = ?, gender = ? , nationality = ?" +
                            " WHERE passport_number = ? ";

                    PreparedStatement preparedStmt = con.prepareStatement(query);


                    System.out.print("which passport_number would you like to change? :  ");
                    String passport_number = scanner.nextLine();
                    preparedStmt.setString(4, passport_number);

                    System.out.print("change name ? in:  ");
                    String name = scanner.nextLine();
                    preparedStmt.setString(1, name);

                    System.out.print("change gender ? in: ");
                    String gender = scanner.nextLine();
                    preparedStmt.setString(2, gender);

                    System.out.println("change nationality ? in: ");
                    String nationality = scanner.nextLine();
                    preparedStmt.setString(3, nationality);

                    int rowsUpdated = preparedStmt.executeUpdate();


                    System.out.print("\nYOUR INPUT:\n\n"
                            + "Passport number: " + "[" + passport_number + "] \n"
                            + "Name: " + "           [" + name + "]\n"
                            + "Gender: " + "         [" + gender + "] \n"
                            + "Nationality: " + "    [" + nationality + "]\n\n"
                            + "");

                    if (rowsUpdated == 0) {
                        System.out.println("An existing user was not updated! Please check your input and try again!\n");
                    } else {
                        System.out.println("An existing user was updated!");
                        selectAllData();
                    }
                    preparedStmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delCust(String i) {
        con = getInstance();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String deleteSQL = "DELETE from flightcustomer WHERE passport_number = ? ";

        while (true) {

            try {
                Scanner scanner = new Scanner(System.in);
                dbConnection = getInstance();
                preparedStatement = dbConnection.prepareStatement(deleteSQL);

                i = scanner.nextLine();
                preparedStatement.setString(1, i);
                int res = preparedStatement.executeUpdate();

                if (res == 0) {
                    System.out.println("Kunde entfernt!");
                    selectAllData();
                } else {
                    System.out.println("Kunde existiert noch!");
                }


            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    public void options() throws Exception {

        int k=10;

        do{

        Scanner scanner = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.println("OPTIONS");
        System.out.println("option 1 : insert FlightCustomer");
        System.out.println("option 2 : deleteFlightCustomer");
        System.out.println("option 3: updateFlightCustomer");
        System.out.println("option 4: quit\n\n");
        System.out.println("please put a number between 1 to 3: ");
        String i  = scanner.nextLine();
        switch ( i ) {
            case "1":
                this.insertColumn();
                break;

            case "2":
                System.out.println("Bitte geben Sie  die P-Nummer ein");
                String j = input.next();
                this.delCust(j);
                break;

            case "3":
                this.updateFlightCustomer();
                break;

           case "quit":
                break;

        default:
                System.out.println("Quit");
                break;

        }
        }while(k == 10);

    }

}

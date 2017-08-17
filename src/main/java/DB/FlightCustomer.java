package DB;

/**
 * Created by goekh on 12.08.2017.
 */
public class FlightCustomer {

private static String passportNumber;
private static String name;
private static String gender;
private static String nationality;

public FlightCustomer(String passportNumber,String name,String gender,String nationality){
    super();
     this.passportNumber = passportNumber;
     this.name=name;
     this.gender=gender;
     this.nationality=nationality;
}

public FlightCustomer(){}

public FlightCustomer(String passportNumber,String name){
    super();
    this.passportNumber=passportNumber;
    this.name=name;
}

public String getPassportNumber(){return passportNumber;}
public String getName(){return name;}
public String getGender(){return gender;}
public String getNationality(){return nationality;}

public void setPassportNumber(String passportNumber){this.passportNumber=passportNumber;}
public void setName(String name){this.name=name;}
public void setGender(String gender){this.gender=gender;}
public void setNationality(String nationality){this.nationality=nationality;}





}

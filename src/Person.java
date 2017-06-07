/**
 * Created by FMF 7 on 6/1/2017.
 */
public class Person {
    private int ID;
    private String firstName;
    private String lastName;
    private String state;
    private String city;


    public Person(String f, String l, String s, String c){
        firstName = f;
        lastName = l;
        state = s;
        city = c;
        ID = 123;
    }

    public boolean equals(Person p){
        if((p.getFirstName().equals(this.getFirstName())) &&
                (p.getLastName().equals(this.getLastName()))){
            return true;
        }else{
            return false;
        }
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}

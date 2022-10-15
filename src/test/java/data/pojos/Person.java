package data.pojos;

public class Person {
   protected String firstName;
    protected String lastName;
    protected String zipcode;

    public Person(String firstName, String lastName, String zipcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipcode = zipcode;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


}

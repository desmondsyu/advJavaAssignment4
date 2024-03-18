package business;

public class Person {

    // constants
    public static final int FNAME_SIZE = 20;
    public static final int LNAME_SIZE = 25;
    public static final int PHONE_SIZE = 10;

    // fields
    public String firstName, lastName, phone;
    public int age;

    // constructors
    public Person(String firstName, String lastName, String phone, int age) {

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhone(phone);
        this.setAge(age);
    }

    // getters and setters for each field
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName == null || firstName.length() > FNAME_SIZE) {
            throw new IllegalArgumentException("First name cannot exceed " + FNAME_SIZE + " characters.");
        }
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if(lastName == null || lastName.length() > LNAME_SIZE) {
            throw new IllegalArgumentException("Last name cannot exceed " + LNAME_SIZE + " characters.");
        }
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        if(phone == null || phone.length() > PHONE_SIZE) {
            throw new IllegalArgumentException("Phone number cannot exceed " + PHONE_SIZE + " characters.");
        }
        this.phone = phone;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

}
package business;

public class Person {
    // Fields
    private String firstName;
    private String lastName;
    private String phone;
    private int age;

    // Constants for max size of string fields
    public static final int MAX_FIRST_NAME_LENGTH = 20;
    public static final int MAX_LAST_NAME_LENGTH = 25;
    public static final int MAX_PHONE_LENGTH = 10;

    // Constructors
    public Person(String firstName, String lastName, String phone, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

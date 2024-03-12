package business;

public class Person {
	
	// constants
	public final int FNAME_SIZE = 20;
	public final int LNAME_SIZE = 25;
	public final int PHONE_SIZE = 10;
	
	// fields
	public String firstName, lastName, phone;
	public int age;
	
	// constructors
	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String phone, int age) {
		this.setFristName(firstName);
		this.setLastName(lastName);
		this.setPhone(phone);
		this.setAge(age);
	}
	
	// getters and setters for each field
	public String getFristName() {
		return firstName;
	}
	public void setFristName(String fristName) {
		this.firstName = fristName;
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

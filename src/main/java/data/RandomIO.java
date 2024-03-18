package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import business.Person;

public class RandomIO {
    private RandomAccessFile file;

    // Constructor to open the file
    public RandomIO(String filename) throws FileNotFoundException {
        try {
            file = new RandomAccessFile(filename, "rw");
        } catch (FileNotFoundException e) {
            // If file not found, create a new one
            try {
                file = new RandomAccessFile(filename, "rw");
            } catch (FileNotFoundException ex) {
                // Re-throw the exception if file still cannot be created
                throw new FileNotFoundException("File cannot be created: " + filename);
            }
        }
    }

    // Method to close the file
    public void close() throws IOException {
        if (file != null) {
            file.close();
        }
    }

    // Method to add a person to the binary file
    public void addPerson(Person person) throws IOException {
        long position = file.length();
        file.seek(position);

        // Write person data to the file
        writeString(person.getFirstName(), Person.FNAME_SIZE);
        writeString(person.getLastName(), Person.LNAME_SIZE);
        writeString(person.getPhone(), Person.PHONE_SIZE);
        file.writeInt(person.getAge());
    }

    // Method to find a person based on record number and return person
    public Person findPerson(int recordNumber) throws IOException {
        long position = recordNumber * (Person.FNAME_SIZE + Person.LNAME_SIZE + Person.PHONE_SIZE + 4);
        if (position >= file.length() || recordNumber < 0) {
            throw new IllegalArgumentException("Record number out of bounds: " + recordNumber);
        }

        file.seek(position);

        // Read person data from the file
        String firstName = readString(Person.FNAME_SIZE);
        String lastName = readString(Person.LNAME_SIZE);
        String phone = readString(Person.PHONE_SIZE);
        int age = file.readInt();

        return new Person(firstName, lastName, phone, age);
    }

    // Method to return all persons from the file
    public List<Person> getAllPersons() throws IOException {
        List<Person> persons = new ArrayList<>();
        file.seek(0);

        while (file.getFilePointer() < file.length()) {
            String firstName = readString(Person.FNAME_SIZE);
            String lastName = readString(Person.LNAME_SIZE);
            String phone = readString(Person.PHONE_SIZE);
            int age = file.readInt();

            persons.add(new Person(firstName, lastName, phone, age));
        }

        return persons;
    }

    // Method to write a string to the file with fixed size
    private void writeString(String s, int size) throws IOException {
        if (s != null) {
            file.writeBytes(s);
            int remaining = size - s.length();
            for (int i = 0; i < remaining; i++) {
                file.writeByte(0);
            }
        } else {
            for (int i = 0; i < size; i++) {
                file.writeByte(0);
            }
        }
    }

    // Method to read a string of fixed size from the file
    private String readString(int size) throws IOException {
        byte[] buffer = new byte[size];
        file.readFully(buffer);
        return new String(buffer).trim();
    }
}
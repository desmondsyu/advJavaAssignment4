package data;

import java.io.*;

import business.Person;

public class RandomIO {

    private static final String FILE_NAME = "persons.dat"; // Binary file name

    // Method to add person to the binary file
    public static void addPerson(Person person) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            long fileLength = file.length();
            file.seek(fileLength); // Move file pointer to end

            // Write person data to file
            writeString(file, person.getFirstName(), Person.FNAME_SIZE);
            writeString(file, person.getLastName(), Person.LNAME_SIZE);
            writeInt(file, person.getAge());
            writeString(file, person.getPhone(), Person.PHONE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to find a person based on record number and return person
    public static Person findPerson(int recordNumber) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            long position = (recordNumber - 1) * calculateRecordSize(); // Calculate position in file
            if (position < 0 || position >= file.length()) {
                System.out.println("Record not found.");
                return null;
            }
            file.seek(position); // Move file pointer to the position

            // Read person data from file
            String firstName = readString(file, Person.FNAME_SIZE);
            String lastName = readString(file, Person.LNAME_SIZE);
            int age = file.readInt();
            String phone = readString(file, Person.PHONE_SIZE);

            return new Person(firstName, lastName, phone, age);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to write a string to the file with fixed length
    private static void writeString(RandomAccessFile file, String str, int maxLength) throws IOException {
        byte[] bytes = new byte[maxLength];
        byte[] strBytes = str.getBytes();
        int length = Math.min(strBytes.length, maxLength);
        System.arraycopy(strBytes, 0, bytes, 0, length);
        file.write(bytes);
    }

    // Helper method to write an integer to the file
    private static void writeInt(RandomAccessFile file, int value) throws IOException {
        file.writeInt(value);
    }

    // Helper method to read a string from the file with fixed length
    private static String readString(RandomAccessFile file, int maxLength) throws IOException {
        byte[] bytes = new byte[maxLength];
        file.readFully(bytes);
        return new String(bytes).trim();
    }

    // Helper method to calculate the size of each record
    private static int calculateRecordSize() {
        return Person.FNAME_SIZE +
               Person.LNAME_SIZE +
               Integer.BYTES +
               Person.PHONE_SIZE;
    }
}

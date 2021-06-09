/**
 * MySolution.java
 * @author Mogammad Faeedh Daniels
 * Student number: 219174288
 */
package za.ac.cput.adpassignment3;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class ReadStakeholderSer {

    ObjectInputStream input;
    Object stHolder;

    public ArrayList<Customer> customerList() throws IOException {
        ArrayList<Customer> customer = new ArrayList<>();

        try {
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));

            while (true) {
                stHolder = input.readObject();

                if (stHolder instanceof Customer) {
                    customer.add((Customer) stHolder);
                }
                
                Collections.sort(customer, (Customer c1, Customer c2) -> c1.getStHolderId().compareTo(c2.getStHolderId()));

            }
        } catch (EOFException eofe) {
        } catch (ClassNotFoundException | IOException ioe) {
        } finally {
            input.close();
        }

        return customer;
    }

    public int ageCalculate(String dob) {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        LocalDate Customerdob = LocalDate.parse(dob);
        int customerdobYear = Customerdob.getYear();

        int age = currentYear - customerdobYear;

        return age;
    }

    public String reformatDob(String dob) {
        DateTimeFormatter reformat = DateTimeFormatter.ofPattern("dd MMM yyyy");

        LocalDate Customerdob = LocalDate.parse(dob);
        return Customerdob.format(reformat);

    }

    public int canRent() throws IOException {
        int canRent = 0;

        for (int i = 0; i < customerList().size(); i++) {

            if (customerList().get(i).getCanRent()) {
                canRent += 1;
            }
        }

        return canRent;
    }

    public int cannotRent() throws IOException {
        int cannotRent = 0;

        for (int i = 0; i < customerList().size(); i++) {

            if (!customerList().get(i).getCanRent()) {
                cannotRent += 1;
            }
        }

        return cannotRent;
    }

    public void writeToCustomerFile() {
        try {
            FileWriter fw = new FileWriter("customerOutFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println("*** customerOutFile.txt created ***");

            bw.write(String.format("=================== CUSTOMERS ====================="));
            bw.write(String.format("\n%s\t%-10s\t%-10s\t%-10s\t%-10s\n", "ID", "Name", "Surname", "Date of Birth", "Age"));
            bw.write(String.format("===================================================\n"));

            for (int i = 0; i < customerList().size(); i++) {

                bw.write(String.format("%s\t%-10s\t%-10s\t%-15s\t%-10s\n", customerList().get(i).getStHolderId(), customerList().get(i).getFirstName(), customerList().get(i).getSurName(), reformatDob(customerList().get(i).getDateOfBirth()), ageCalculate(customerList().get(i).getDateOfBirth())));

            }
            bw.write(String.format("\nNumber of customers who can rent: %d", canRent()));
            bw.write(String.format("\nNumber of customers who cannot rent: %d", cannotRent()));
            bw.close();
            System.out.println("*** customerOutFile.txt closed ***\n");

        } catch (IOException ioe) {
        }
    }

    public ArrayList<Supplier> supplierList() throws IOException {
        ArrayList<Supplier> supplier = new ArrayList<>();

        try {
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));

            while (true) {
                stHolder = input.readObject();

                if (stHolder instanceof Supplier) {
                    supplier.add((Supplier) stHolder);

                }
                
                Collections.sort(supplier, (Supplier s1, Supplier s2) -> s1.getName().compareTo(s2.getName()));
            }
        } catch (EOFException eofe) {
        } catch (ClassNotFoundException | IOException ioe) {
        } finally {
            input.close();
        }

        return supplier;
    }

    public void writeToSupplierFile() {
        try {
            FileWriter fw = new FileWriter("supplierOutFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println("*** supplierOutFile.txt created ***");

            bw.write(String.format("=================== SUPPLIERS ============================="));
            bw.write(String.format("\n%s\t%-20s\t%-10s\t%-10s\n", "ID", "Name", "Prod Type", "Description"));
            bw.write(String.format("===========================================================\n"));

            for (int i = 0; i < supplierList().size(); i++) {

                bw.write(String.format("%s\t%-20s\t%-10s\t%-10s\n", supplierList().get(i).getStHolderId(), supplierList().get(i).getName(), supplierList().get(i).getProductType(), supplierList().get(i).getProductDescription()));

            }
            bw.close();
            System.out.println("*** supplierOutFile.txt closed ***");

        } catch (IOException ioe) {
        }
    }

    public static void main(String args[]) {
        ReadStakeholderSer obj = new ReadStakeholderSer();
        obj.writeToCustomerFile();
        obj.writeToSupplierFile();
    }

}

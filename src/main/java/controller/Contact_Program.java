/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controller;

import Library.Check_Valid;
import java.util.ArrayList;
import java.util.List;
import model.Contact;
import view.Menu;

public class Contact_Program extends Menu<String> {
    private static final String[] MENU_CHOICES = {"Add a Contact", "Display all Contacts", "Delete a Contact", "Exit"};
    private static int currentId = 1; // Biến để tự động tạo ID
    private final Check_Valid validation;
    private final List<Contact> contacts;

    public Contact_Program() {
        super("Contact Program", MENU_CHOICES);
        validation = new Check_Valid();
        contacts = new ArrayList<>();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> createContact();
            case 2 -> printAllContacts();
            case 3 -> deleteContact();
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid choice. Try Again!!");
        }
    }

public void createContact() {
    System.out.print("Enter full name: ");
    String fullName = validation.checkInputString();

    // Tách fullName thành firstName và lastName
    String[] nameParts = fullName.split(" ", 2);
    String firstName = nameParts[0];
    String lastName = (nameParts.length > 1) ? nameParts[1] : "";

    System.out.print("Enter group: ");
    String group = validation.checkInputString();
    System.out.print("Enter address: ");
    String address = validation.checkInputString();
    System.out.print("Enter phone: ");
    String phone = validation.checkInputPhone();

    int contactId = currentId++;
    Contact newContact = new Contact(contactId, fullName, group, address, phone, firstName, lastName);
    contacts.add(newContact);

    System.err.println("Contact added successfully.");
}


    public void printAllContacts() {
        System.out.printf("%-5s%-25s%-20s%-20s%-20s%-20s%-20s\n", "Id", "Name", "First name", "Last name", "Group", "Address", "Phone");
        for (Contact contact : contacts) {
            System.out.printf("%-5d%-25s%-20s%-20s%-20s%-20s%-20s\n",
                    contact.getContactId(), contact.getFullName(), contact.getFirstName(),
                    contact.getLastName(), contact.getGroup(), contact.getAddress(), contact.getPhone());
        }
    }

    public void deleteContact() {
        System.out.print("Enter id: ");
        int idToDelete = validation.checkInputInt();
        Contact contactToDelete = getContactById(idToDelete);
        if (contactToDelete == null) {
            System.err.println("Contact not found.");
            return;
        }
        contacts.remove(contactToDelete);
        System.err.println("Contact deleted successfully.");
    }

    public Contact getContactById(int idToDelete) {
        for (Contact contact : contacts) {
            if (contact.getContactId() == idToDelete) {
                return contact;
            }
        }
        return null;
    }
}



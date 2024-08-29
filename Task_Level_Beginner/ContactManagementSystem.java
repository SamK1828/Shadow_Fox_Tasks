import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) throws IllegalArgumentException {
        this.name = name;
        setPhoneNumber(phoneNumber); // Validate phone number
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits and numeric.");
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}"); // Checks if phoneNumber is exactly 10 digits
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

public class ContactManagementSystem {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    private static void addContact(Scanner scanner) {
        try {
            System.out.print("Enter contact name: ");
            String name = scanner.nextLine();
            System.out.print("Enter contact phone number (10 digits): ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter contact email address: ");
            String email = scanner.nextLine();

            Contact newContact = new Contact(name, phoneNumber, email);
            contacts.add(newContact);
            System.out.println("Contact added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding contact: " + e.getMessage());
        }
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("Contact List:");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private static void updateContact(Scanner scanner) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available to update.");
            return;
        }

        viewContacts();
        System.out.print("Enter the contact number to update: ");
        String phoneNumberToUpdate = scanner.nextLine(); // Read as string

        Contact contactToUpdate = null;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumberToUpdate)) {
                contactToUpdate = contact;
                break;
            }
        }

        if (contactToUpdate != null) {
            System.out.print("Enter new name (" + contactToUpdate.getName() + "): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new phone number (" + contactToUpdate.getPhoneNumber() + "): ");
            String newPhoneNumber = scanner.nextLine();
            System.out.print("Enter new email (" + contactToUpdate.getEmail() + "): ");
            String newEmail = scanner.nextLine();

            try {
                contactToUpdate.setName(newName.isEmpty() ? contactToUpdate.getName() : newName);
                contactToUpdate
                        .setPhoneNumber(newPhoneNumber.isEmpty() ? contactToUpdate.getPhoneNumber() : newPhoneNumber);
                contactToUpdate.setEmail(newEmail.isEmpty() ? contactToUpdate.getEmail() : newEmail);
                System.out.println("Contact updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating contact: " + e.getMessage());
            }
        } else {
            System.out.println("Contact with the given phone number not found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available to delete.");
            return;
        }
    
        viewContacts();
        System.out.print("Enter the contact number to delete: ");
        String phoneNumberToDelete = scanner.nextLine();  // Read as string
    
        boolean removed = contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumberToDelete));
    
        if (removed) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact with the given phone number not found.");
        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==========Contact Management System==========\n");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Contact Management System........");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid one.");
            }
            System.out.println();
        }

        scanner.close();
    }
}

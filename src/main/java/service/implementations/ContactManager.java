package service.implementations;

import data.Contact;
import data.ContactDatabase;
import service.Input;
import service.Manager;

import java.util.List;

public class ContactManager implements Manager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";



    private final ContactDatabase db;

    private final Input input;

    public ContactManager(Input input) {
        db = new ContactDatabase();
        this.input = input;
    }

    @Override
    public void removeContact(String name) {
        if(searchContacts(name) == null){
            System.out.format(ANSI_RED + "%nContact %s not found.%n%n" + ANSI_RESET, name);
            return;
        }
        db.removeContact(name);
        System.out.format(ANSI_BLUE + "%nContact %s has been deleted.%n%n" + ANSI_RESET, name);
    }

    @Override
    public void printMenu() {
        System.out.format(ANSI_BLUE + """
                1 - View contacts.
                2 - Add a new contact.
                3 - Search a contact by name.
                4 - Delete an existing contact.
                5 - exit%n
                """ + ANSI_RESET);
    }

    @Override
    public void doChoice(int choice){
        switch (choice) {
            case 1 -> getContacts();
            case 2 -> addContact();
            case 3 -> searchForContact();
            case 4 -> deleteContact();
        }
    }

    private void deleteContact() {
        String name = input.getString(ANSI_YELLOW + "Who do you want to delete? " + ANSI_RESET);
        removeContact(name);
    }

    private void searchForContact() {
        String name = input.getString(ANSI_YELLOW + "Who are you looking for? " + ANSI_RESET);
        if(searchContacts(name) == null){
            System.out.format(ANSI_RED + "%nContact %s not found.%n%n" + ANSI_RESET, name);
            return;
        }
        Contact contact = searchContacts(name);
        System.out.format(ANSI_BLUE + "%n%s%n%n" + ANSI_RESET, contact);
    }

    @Override
    public void addContact() {
        String name = input.getString(ANSI_YELLOW + "Enter the name of the contact: " + ANSI_RESET);
        String phoneNumber;
        boolean isValidPhoneNumber;
        do {
            phoneNumber = input.getString(ANSI_YELLOW + "Enter the 10-digit phone number of the contact [digits only]: " + ANSI_RESET);
            isValidPhoneNumber = isValidPhoneNumber(phoneNumber);
            if (!isValidPhoneNumber) {
                System.out.format(ANSI_RED + "%nInvalid phone number. Please try again.%n%n" + ANSI_RESET);
            }
        } while (!isValidPhoneNumber);
        Contact contact = new Contact(name, phoneNumber);
        if (db.getContact(name) != null){
            String prompt = String.format(ANSI_RED + "There is already a contact named %s. Would you like to overwrite it? [y/n] " + ANSI_RESET, name);
            if (input.yesNo(prompt)) {
                db.editContact(contact);
                System.out.format(ANSI_BLUE + "%nContact %s has been updated.%n" + ANSI_RESET, contact.getName());
                return;
            }
        }
        db.addContact(contact);
        System.out.format(ANSI_BLUE + "%nContact added!%n%n" + ANSI_RESET);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.matches("[0-9]+");
    }

    @Override
    public void getContacts() {
        if (db.getAllContacts().isEmpty()){
            System.out.format(ANSI_RED + "%nNo contacts found%n%n" + ANSI_RESET);
            return;
        }
        System.out.println();
        db.getAllContacts().forEach(System.out::println);
        System.out.println();
    }

    @Override
    public Contact searchContacts(String name) {
        return db.getContact(name);
    }

    public void writeToFile(){
        List<Contact> contacts = db.getAllContacts();
        contacts.forEach(contact -> db.writeContact(contact.getName() + "," + contact.getPhoneNumber()));
    }
}

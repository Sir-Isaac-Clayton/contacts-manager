package service.implementations;

import data.Contact;
import data.ContactDatabase;
import service.Input;
import service.Manager;

import java.util.List;

public class ContactManager implements Manager {

    private final ContactDatabase db;

    private final Input input;

    public ContactManager(Input input) {
        db = new ContactDatabase();
        this.input = input;
    }

    @Override
    public void removeContact(String name) {
        if(searchContacts(name) == null){
            System.out.format("%nContact %s not found.%n%n", name);
            return;
        }
        db.removeContact(name);
        System.out.format("%nContact %s has been deleted.%n%n", name);
    }

    @Override
    public void printMenu() {
        System.out.println("1 - View contacts.");
        System.out.println("2 - Add a new contact.");
        System.out.println("3 - Search a contact by name.");
        System.out.println("4 - Delete an existing contact.");
        System.out.println("5 - exit");
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
        String name = input.getString("Who do you want to delete? ");
        removeContact(name);
    }

    private void searchForContact() {
        String name = input.getString("Who are you looking for? ");
        Contact contact = searchContacts(name);
        System.out.format("%n%s%n%n", contact);
    }

    @Override
    public void addContact() {
        String name = input.getString("Enter the name of the contact: ");
        String phoneNumber = input.getString("Enter the phone number of the contact: ");
        Contact contact = new Contact(name, phoneNumber);
        if (db.getContact(name) != null){
            String prompt = String.format("There is already a contact named %s. Would you like to overwrite it? [y/n] ", name);
            if (input.yesNo(prompt)) {
                db.editContact(contact);
                System.out.format("%nContact %s has been updated.%n", contact.getName());
                return;
            }
        }
        db.addContact(contact);
        System.out.format("%nContact added!%n%n");
    }

    @Override
    public void getContacts() {
        if (db.getAllContacts().isEmpty()){
            System.out.format("%nNo contacts found%n%n");
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

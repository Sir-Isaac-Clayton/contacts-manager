package service.implementations;

import data.Contact;
import data.ContactDatabase;
import service.Input;
import service.Manager;

import java.util.List;

public class ContactManager implements Manager {

    private ContactDatabase db;

    private Input input;

    public ContactManager(Input input) {
        db = new ContactDatabase();
        this.input = input;
    }

    @Override
    public void run() {

    }

    @Override
    public void removeContact(String name) {
        db.removeContact(name);
    }

    @Override
    public void printMenu() {
        System.out.println("what would you like to do?\n");

        System.out.println("1 - View contacts.");
        System.out.println("2 - Add a new contact.");
        System.out.println("3 - Search a contact by name.");
        System.out.println("4 - Delete an existing contact.");
        System.out.println("5 - exit");
    }

    @Override
    public void doChoice(int choice){
        switch (choice) {
            case 1 -> showAllContacts();
            case 2 -> addNewContact();
            case 3 -> searchForContact();
            case 4 -> deleteContact();
        }
    }

    private void deleteContact(String response) {
        String name = input.getString("Who do you want to delete?");
        db.removeContact(name);
    }

    private void searchForContact() {
    }

    private void addNewContact() {
        System.out.println("new contact");
    }

    private void showAllContacts() {
        System.out.println("all");
    }


    @Override
    public void addContact(Contact contact) {

    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public Contact searchContacts(String name) {
        return db.getContact(name);
    }
}

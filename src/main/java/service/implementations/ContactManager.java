package service.implementations;

import data.Contact;
import data.ContactDatabase;
import service.Input;
import service.Manager;

import java.util.List;

public class ContactManager implements Manager {

    private ContactDatabase db;

    private Input input;

    public ContactManager() {
        db = new ContactDatabase();
    }

    @Override
    public void run() {

    }

    @Override
    public void removeContact(Contact contact) {

    }

    @Override
    public void printMenu() {

    }


    @Override
    public void addContact(Contact contact) {
        db.addContact(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public void searchContacts(String name) {

    }
}

package service;

import data.Contact;

import java.util.List;

public interface Manager {
    public void run();
    public void printMenu();
    public void addContact(Contact contact);
    public List<Contact> getContacts();
    public void searchContacts(String name);
}

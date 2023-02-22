package service;

import data.Contact;

import java.util.List;

public interface Manager {
    public void run();
    public void printMenu();
    public void addContact(Contact contact);
    public List<Contact> getContacts();
    public Contact searchContacts(String name);
    public void removeContact(String name);
    public void doChoice(int choice);
}

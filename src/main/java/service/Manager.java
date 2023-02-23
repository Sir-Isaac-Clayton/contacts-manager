package service;

import data.Contact;

public interface Manager {
    void printMenu();
    void addContact();
    void getContacts();
    Contact searchContacts(String name);
    void removeContact(String name);
    void doChoice(int choice);
    void writeToFile();
}

package data;

import service.implementations.ContactWriter;
import service.implementations.IContactWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabase implements IContactWriter {
    List<Contact> contacts;
    IContactWriter writer;

    public ContactDatabase() {
        this.contacts = new ArrayList<>();
        importContacts();
        writer = new ContactWriter();
    }

    public ContactDatabase(IContactWriter writer) {
        this.writer = writer;
    }

    private void importContacts() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("contacts.txt"));
            int counter = 0;
            for (String line : lines) {
                String[] arr = line.split(",");
                counter += 1;
                contacts.add(new Contact(arr[0], arr[1]));
            }
//            System.out.format("%d imported into database", counter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void addAllContacts(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        for (Contact contact : contacts) {
            if (name.equals(contact.getName())) {
                contacts.remove(contact);
                break;
            }
        }
    }

    public void editContact(Contact contact) {
        for (Contact c : contacts) {
            if (contact.getName().equals(c.getName())) {
                c.setPhoneNumber(contact.getPhoneNumber());
                break;
            }
        }
    }

    public Contact getContact(String name) {
        Contact result = null;
        for (Contact contact : contacts) {
            if (name.equals(contact.getName())) {
                result = contact;
                break;
            }
        }
        return result;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public void writeContact(String name, String number) {

    }
}

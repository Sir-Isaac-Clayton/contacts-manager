package data;

import service.IContactWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabase implements IContactWriter {
    List<Contact> contacts;
    IContactWriter writer;

    public ContactDatabase(IContactWriter writer) {
        this.contacts = new ArrayList<>();
        this.writer = writer;
        importContacts();
    }

    @Override
    public void setPath(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.setPath(path);
    }

    private void importContacts() {

        try {
            Path path = Paths.get("contacts.txt");
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                   String [] arr = line.split(",");
                   contacts.add(new Contact(arr[0], arr[1]));
                }
            }
            setPath(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        for (Contact contact : contacts) {
            if(name.equalsIgnoreCase(contact.getName())) {
                contacts.remove(contact);
                break;
            }
        }
    }

    public void editContact(Contact contact) {
        for (Contact c : contacts) {
            if(contact.getName().equals(c.getName())) {
                c.setPhoneNumber(contact.getPhoneNumber());
                break;
            }
        }
    }

    public Contact getContact(String name) {
        Contact result = null;
        for (Contact contact : contacts) {
            if(name.equalsIgnoreCase(contact.getName())) {
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
    public void writeToFile(List<Contact> contacts) {
        writer.writeToFile(contacts);
    }

    public int size() {
        return contacts.size();
    }

}

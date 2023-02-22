package data;

import java.util.ArrayList;
import java.util.List;

public class ContactDatabase {
    List<Contact> contacts;

    public ContactDatabase() {
        this.contacts = new ArrayList<>();
    }

    public void addAllContacts(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact getContact(String name) {
        Contact result = null;
        for (Contact contact : contacts) {
            if(name.equals(contact.getName())) {
                result = contact;
                break;
            }
        }
        return result;
    }
}

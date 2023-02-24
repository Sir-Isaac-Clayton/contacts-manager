package service;

import data.Contact;

import java.nio.file.Path;
import java.util.List;

public interface IContactWriter {
    void writeToFile(List<Contact> contacts);
    void setPath(Path path);

}

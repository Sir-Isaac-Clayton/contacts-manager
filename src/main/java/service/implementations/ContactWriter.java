package service.implementations;

import data.Contact;
import service.IContactWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ContactWriter implements IContactWriter {

    Path path;
    @Override
    public void writeToFile(List<Contact> contacts) {
        if (contacts.size() > 0) {
            String data = contacts.stream()
                    .map(c -> String.join(",", List.of(c.getName(),c.getPhoneNumber())) + "\n")
                    .collect(Collectors.joining());
            try {
                Files.writeString(path, data, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.format("Error writing contact '{%s}' to file: %s%n", contacts, e.getMessage());
            }
        }
    }

    @Override
    public void setPath(Path path) {
        try {
            this.path = path != null ? path : Paths.get("contacts.txt");
            Files.createFile(this.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

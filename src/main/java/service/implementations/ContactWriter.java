package service.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class ContactWriter implements IContactWriter {
    @Override
    public void writeContact(String contact) {
        try {
            Path path = Paths.get("contacts.txt");
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, Arrays.asList(contact), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.format("Error writing contact '{%s}' to file: %s%n", contact, e.getMessage());
        }
    }
}

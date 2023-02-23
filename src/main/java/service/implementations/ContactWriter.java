package service.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class ContactWriter implements IContactWriter {
    @Override
    public void writeContact(String name, String number) {
        try {
            Files.write(Paths.get("contacts.txt"), Arrays.asList(name, number), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.format("Error writing contact '%s' to file: %s%n", name, e.getMessage());
        }
    }
}

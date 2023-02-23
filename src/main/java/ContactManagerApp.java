import service.Input;
import service.Manager;
import service.implementations.ContactManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ContactManagerApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";



    public static String importTitle() {
        StringBuilder sb = new StringBuilder();
        try {
            Path path = Paths.get("src/main/resources/title.txt");
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    sb.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String title = importTitle();
        try(Input input = new Input()) {
            Manager manager = new ContactManager(input);
            System.out.format(ANSI_YELLOW + """
                    
                    
                    %s
                    
                    """ + ANSI_RESET, title);

            while(true){

                manager.printMenu();

                int choice = input.getInteger(1, 5, ANSI_YELLOW + "Please make a numeric selection: " + ANSI_RESET);

                manager.doChoice(choice);

                if (choice == 5){
                    System.out.format(ANSI_YELLOW + "%nGoodbye!" + ANSI_RESET);
                    manager.writeToFile();
                    break;
                }

            }
        }
    }
}

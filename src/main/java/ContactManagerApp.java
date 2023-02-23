import service.Input;
import service.Manager;
import service.implementations.ContactManager;


public class ContactManagerApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";




    public static void main(String[] args) {
        try(Input input = new Input()) {
            Manager manager = new ContactManager(input);
            System.out.format(ANSI_YELLOW + "Contact Manager Database%n%n" + ANSI_RESET);
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

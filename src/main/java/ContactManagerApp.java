import service.Input;
import service.Manager;
import service.implementations.ContactManager;


public class ContactManagerApp {


    public static void main(String[] args) {
        try(Input input = new Input()) {
            Manager manager = new ContactManager(input);
            System.out.format("Contact Manager Database%n%n");
            while(true){

                manager.printMenu();

                int choice = input.getInteger(1, 5, "Please make a numeric selection: ");

                manager.doChoice(choice);

                if (choice == 5){
                    System.out.format("%nGoodbye!");
                    break;
                }

            }
        }
    }
}

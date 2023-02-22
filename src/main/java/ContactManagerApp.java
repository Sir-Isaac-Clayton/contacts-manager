import service.Input;
import service.Manager;
import service.implementations.ContactManager;


public class ContactManagerApp {


    public static void main(String[] args) {
        try(Input input = new Input()) {
            Manager manager = new ContactManager(input);
            while(true){

                manager.printMenu();

                int choice = input.getInteger(1, 5);

                manager.doChoice(choice);

                if (choice == 5){
                    break;
                }

            }
        }
    }
}

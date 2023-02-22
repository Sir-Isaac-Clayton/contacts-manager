import java.util.Scanner;

public class ContactManagerApp {
    public static int getUserInput(){
        Scanner Input = new Scanner(System.in);
        System.out.println("what would you like to do?\n");

        System.out.println("1 - View contacts.");
        System.out.println("2 - Add a new contact.");
        System.out.println("3 - Search a contact by name.");
        System.out.println("4 - Delete an existing contact.");
        System.out.println("5 - exit");
        return Input.nextInt();

    }

    private static void doChoice(int choice){
        switch (choice) {
//            case 1 -> getAllMovies();
//            case 2 -> getMoviesByCategory("animated");
//            case 3 -> getMoviesByCategory("drama");
//            case 4 -> getMoviesByCategory("horror");
        }
    }

    public static void main(String[] args) {


        while(true){
            int choice = getUserInput();

            doChoice(choice);

            if (choice == 5){
                break;
            }

        }
    }
}

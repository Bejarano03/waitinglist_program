import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WaitingListManager manager = new WaitingListManager();
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("6")) {
            System.out.println("\nWaiting List Manager Menu:");
            System.out.println("1. Add Person");
            System.out.println("2. Serve Person");
            System.out.println("3. Check if Person is in List");
            System.out.println("4. Waiting List Size");
            System.out.println("5. Display Waiting List");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter person's name: ");
                String nameToAdd = scanner.nextLine();
                manager.addPerson(nameToAdd);
            } else if (choice.equals("2")) {
                String servedPerson = manager.servePerson();
                System.out.println(servedPerson.equals("The waiting list is empty.") ? servedPerson : servedPerson + " has been served.");
            } else if (choice.equals("3")) {
                System.out.print("Enter person's name: ");
                String nameToCheck = scanner.nextLine();
                if (manager.isPersonInList(nameToCheck)) {
                    System.out.println(nameToCheck + " is in the waiting list.");
                } else {
                    System.out.println(nameToCheck + " is not in the waiting list.");
                }
            } else if (choice.equals("4")) {
                System.out.println("Waiting list size: " + manager.waitingListSize());
            } else if (choice.equals("5")) {
                manager.displayWaitingList();
            } else if (choice.equals("6")) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
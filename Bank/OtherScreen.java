package Bank;

public class OtherScreen {

}

public void other(User user, Scanner sc) {
    int amount = 0;
    boolean success = false;

    do {
        try {
            System.out.println();
            System.out.println("Enter the number you would like to withdraw: ");
            String input = sc.next();
            amount = Integer.parseInt(input);
            success = true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, try again!");
        }
    } while (!success);

    if ((amount <= user.getBalance()) && (amount >= 0)) {
        System.out.println("$" + amount + " has been withdrawn from your account");
        int newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        System.out.println("Current balance = $" + user.getBalance());
        menu(user, sc);
    } else {
        System.out.println("Invalid amount!");
        System.out.println("Press 1 to try again");
        System.out.println("Press 2 to return to withdraw menu");
        int choice = sc.nextInt();
        if (choice == 1) {
            other(user, sc);
        } else if (choice == 2) {
            System.out.println("Returning to withdraw menu");
            withDrawal(user, sc);
        } else {
            System.out.println("Invalid number entered, returning to main menu");
            menu(user, sc);
        }
    }
}

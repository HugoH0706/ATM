package Bank;

public class DepositScreen {

}

public void deposit(User user, Scanner sc) {
    boolean success = false;
    int amount = 0;

    do {
        try {
            System.out.println();
            System.out.println("Current balance: $" + user.getBalance());
            System.out.println("Enter amount you want to deposit: ");

            String input = sc.next();
            amount = Integer.parseInt(input);
            success = true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, try again!");
        }
    } while (!success);

    if (amount >= 0) {
        int newBalance = amount + user.getBalance();
        user.setBalance(newBalance);
        System.out.println("Transaction completed, current balance: $" + user.getBalance());
    } else {
        System.out.println("Invalid deposited amount");
    }
    menu(user, sc);
}

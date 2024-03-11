package Bank;

import javax.swing.*;

import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Database db = new Database();
        boolean exists = false;
        boolean correctPassword = false;
        String name;
        String password;

        do {
            System.out.println();
            System.out.println("Welcome to HH ATM!");
            System.out.println("Please enter you name to access your bank account: ");
            name = sc.nextLine();
            exists = db.userExists(name);
        } while (exists == false);

        do {
            System.out.println();
            System.out.println("Enter your password: ");
            password = sc.nextLine();
            correctPassword = db.checkPassword(name, password);
        } while (correctPassword == false);

        User user = db.getUser(name);

        BankApplication bank = new BankApplication();
        bank.menu(user, sc);

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

        if ((amount <= user.bank.getBalance()) && (amount >= 0)) {
            System.out.println("$" + amount + " has been withdrawn from your account");
            int newBalance = user.bank.getBalance() - amount;
            user.bank.setBalance(newBalance);
            System.out.println("Current balance = $" + user.bank.getBalance());
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

    public void withDrawal(User user, Scanner sc) {
        boolean success = false;
        int choice = 0;
        int newBalance;
        do {
            try {
                System.out.println();
                System.out.println("Current balance: $" + user.bank.getBalance());
                System.out.println("1: $20");
                System.out.println("2: $50");
                System.out.println("3: $100");
                System.out.println("4: Other");
                System.out.println("5: Exit to main menu");

                String input = sc.next();
                choice = Integer.parseInt(input);
                success = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!");
            }
        } while (!success);

        switch (choice) {
            case 1:
                if (20 <= user.bank.getBalance()) {
                    newBalance = user.bank.getBalance() - 20;
                    user.bank.setBalance(newBalance);
                    System.out.println("Transaction completed, new balance = $" + user.bank.getBalance());
                    menu(user, sc);
                } else {
                    System.out.println("Insufficient balance");
                    withDrawal(user, sc);
                }
                break;
            case 2:
                if (50 <= user.bank.getBalance()) {
                    newBalance = user.bank.getBalance() - 50;
                    user.bank.setBalance(newBalance);
                    System.out.println("Transaction completed, new balance = $" + user.bank.getBalance());
                    menu(user, sc);
                } else {
                    System.out.println("Insufficient balance");
                    withDrawal(user, sc);
                }
                break;
            case 3:
                if (100 <= user.bank.getBalance()) {
                    newBalance = user.bank.getBalance() - 100;
                    user.bank.setBalance(newBalance);
                    System.out.println("Transaction completed, new balance = $" + user.bank.getBalance());
                    menu(user, sc);
                } else {
                    System.out.println("Insufficient balance");
                    withDrawal(user, sc);
                }
                break;
            case 4:
                other(user, sc);
                break;
            case 5:
                System.out.println("Returning to main menu...");
                menu(user, sc);
                break;
            default:
                System.out.println("Invalid number entered, try again!");
                withDrawal(user, sc);
                break;
        }
    }

    public void deposit(User user, Scanner sc) {
        boolean success = false;
        int amount = 0;

        do {
            try {
                System.out.println();
                System.out.println("Current balance: $" + user.bank.getBalance());
                System.out.println("Enter amount you want to deposit: ");

                String input = sc.next();
                amount = Integer.parseInt(input);
                success = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!");
            }
        } while (!success);

        if (amount >= 0) {
            int newBalance = amount + user.bank.getBalance();
            user.bank.setBalance(newBalance);
            System.out.println("Transaction completed, current balance: $" + user.bank.getBalance());
        } else {
            System.out.println("Invalid deposited amount");
        }
        menu(user, sc);
    }

    public void menu(User user, Scanner sc) {
        int choice = 0;
        boolean success = false;

        do {
            try {
                System.out.println();
                System.out.println("Choose the action you want to do: ");
                System.out.println("1: Withdrawal");
                System.out.println("2: Deposit");
                System.out.println("3: Exit");

                String input = sc.next();
                choice = Integer.parseInt(input);
                success = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!");
            }
        } while (!success);

        switch (choice) {
            case 1:
                withDrawal(user, sc);
                break;
            case 2:
                deposit(user, sc);
                break;
            case 3:
                System.out.println("Shutting down...");
                sc.close();
                break;
            default:
                System.out.println("Invalid option entered, try again");
                menu(user, sc);
                break;
        }
    }

}
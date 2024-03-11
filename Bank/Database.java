package Bank;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Database {
    HashMap<Integer, User> database;

    public Database() {
        this.database = new HashMap<>();
        prefillDatabase();
    }

    public void addUser(int id, String name, String hashedPassword, String email, String address, BankAccount bank) {
        User newUser = new User(id, name, hashedPassword, email, address, bank);
        database.put(id, newUser);
    }

    public User getUser(String name) {
        for (User user : database.values()) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        System.out.println("User not found!");
        return null;
    }

    public boolean userExists(String name) {
        for (User user : database.values()) {
            if (user.name.equals(name)) {
                return true;
            }
        }
        System.out.println(name + " is not registered!");
        return false;
    }

    public boolean checkPassword(String name, String password) {
        User user = getUser(name);
        if (user.hashedPassword.equals(password)) {
            return true;
        }
        System.out.println("Invalid password, try again!");
        return false;
    }

    private void prefillDatabase() {
        addUser(1, "Babeth", "BabethLovesHorses123", "babethhakkenberg@gmail.com", "Zuidereiland 17",
                new BankAccount(3));
        addUser(2, "Hugo", "0000", "hugohakkenberg@gmail.com", "Zuidereiland 17", new BankAccount(500));
        addUser(3, "Angelique", "greatAngie", "groota@gmail.com", "Zuidereiland 17", new BankAccount(15000));
        addUser(4, "Merel", "merelB2307", "merelblom@gmail.com", "Amsterdamseweg 181B", new BankAccount(1250));
    }
}

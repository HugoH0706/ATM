package Bank;

import java.util.HashMap;

public class Database {
    HashMap<Integer, User> database;

    public Database() {
        this.database = new HashMap<>();
        prefillDatabase();
    }

    public void addUser(int id, String name, String hashedPassword, String email, String address, int balance) {
        User newUser = new User(id, name, hashedPassword, email, address, balance);
        database.put(id, newUser);
    }

    public User getUser(String name) {
        for (User user : database.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        System.out.println("User not found!");
        return null;
    }

    public boolean checkCredentials(String name, String password) {
        for (User user : database.values()) {
            if (user.getName().equals(name)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void prefillDatabase() {
        addUser(1, "Babeth", "0002", "babethhakkenberg@gmail.com", "Zuidereiland 1", 3);
        addUser(2, "Hugo", "0000", "hugohakkenberg@gmail.com", "Zuidereiland 1", 500);
        addUser(3, "Angelique", "0001", "groota@gmail.com", "Zuidereiland 1", 15000);
        addUser(4, "Merel", "0003", "merelb@gmail.com", "Amsterdamseweg 1", 1250);
    }
}

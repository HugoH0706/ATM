package Bank;

public class User {
    int id;
    String name;
    String hashedPassword;
    String email;
    String address;
    BankAccount bank;

    public User(int id, String name, String hashedPassword, String email, String address, BankAccount bank) {
        this.id = id;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.address = address;
        this.bank = bank;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public BankAccount getBalance() {
        return bank;
    }

}

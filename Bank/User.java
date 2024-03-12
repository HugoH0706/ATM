package Bank;

public class User {
    private int id;
    private String name;
    private String hashedPassword;
    private String email;
    private String address;
    private int balance;

    public User(int id, String name, String hashedPassword, String email, String address, int balance) {
        this.id = id;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.address = address;
        this.balance = balance;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}

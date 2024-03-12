package Bank;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JPanel {
    private JLabel welcomeLabel;
    private JLabel infoLabel;
    private JLabel nameLabel;
    private JLabel pinLabel;

    private JTextField nameField;
    private JPasswordField pinField;
    private JButton loginButton;
    private Database db;
    private TransactionScreen transactionScreen;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public LoginScreen(CardLayout cardLayout, JPanel cardPanel, Database db) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.db = db;
        transactionScreen = new TransactionScreen(cardLayout, cardPanel, db);
        cardPanel.add(transactionScreen, "Transaction");
        setLayout(new GridLayout(6, 2));

        welcomeLabel = new JLabel("Welcome to HH ATM!");
        infoLabel = new JLabel("Please enter you name and password to access your bank account: ");
        nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField();
        pinLabel = new JLabel("Enter Password:");
        pinField = new JPasswordField();
        loginButton = new JButton("Login");

        add(welcomeLabel);
        add(new JLabel());
        add(infoLabel);
        add(new JLabel());
        add(nameLabel);
        add(nameField);
        add(pinLabel);
        add(pinField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pin = new String(pinField.getPassword());

                if (validateUser(name, pin)) {
                    User user = db.getUser(name);
                    cardLayout.show(cardPanel, "Transaction");
                    transactionScreen.setVariables(name, user.getBalance());
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid credentials", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateUser(String name, String password) {
        return db.checkCredentials(name, password);
    }

}
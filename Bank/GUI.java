package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class GUI extends JFrame {
    private JTextField nameField;
    private JPasswordField pinField;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Map<String, String> userDatabase;

    public GUI() {
        super("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        initializeUserDatabase();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createLoginScreen(), "Login");
        cardPanel.add(createTransactionScreen(), "Transaction");

        add(cardPanel);

        cardLayout.show(cardPanel, "Login");
    }

    private JPanel createLoginScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField();
        JLabel pinLabel = new JLabel("Enter PIN:");
        pinField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pin = new String(pinField.getPassword());

                if (validateUser(name, pin)) {
                    // Switch to the transaction screen if the user is valid
                    cardLayout.show(cardPanel, "Transaction");
                } else {
                    // Display an error message if the user is not valid
                    JOptionPane.showMessageDialog(GUI.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(loginButton);

        return panel;
    }

    private JPanel createTransactionScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton withdrawalButton = new JButton("Withdrawal");
        JButton depositButton = new JButton("Deposit");
        JButton exitButton = new JButton("Exit");

        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction("Withdrawal");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction("Deposit");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        panel.add(withdrawalButton);
        panel.add(depositButton);
        panel.add(exitButton);

        return panel;
    }

    private void handleTransaction(String transactionType) {
        // Perform the transaction logic here
        // For simplicity, let's just display a message for demonstration purposes
        String message = "Transaction Type: " + transactionType;
        JOptionPane.showMessageDialog(this, message, "Transaction Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initializeUserDatabase() {
        // Simulated user database with name as key and PIN as value
        userDatabase = new HashMap<>();
        userDatabase.put("Hugo", "0000");
    }

    private boolean validateUser(String name, String pin) {
        // Validate user credentials against the simulated user database
        return userDatabase.containsKey(name) && userDatabase.get(name).equals(pin);
    }

}

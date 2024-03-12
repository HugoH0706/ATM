package Bank;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepositScreen extends JPanel {
    private JLabel menu;
    private String name;
    private JTextField amount;
    private JButton backButton;
    private JButton exitButton;
    private JButton confirmButton;
    private Database db;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public DepositScreen(CardLayout cardLayout, JPanel cardPanel, Database db) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.db = db;

        setLayout(new BorderLayout());

        menu = new JLabel("Enter the amount you want to deposit:");
        add(menu, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        amount = new JTextField();
        add(amount, BorderLayout.CENTER);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = amount.getText();
                if (validAmount(input)) {
                    int deposit = Integer.parseInt(input);
                    User user = db.getUser(name);
                    if (deposit >= 0) {
                        user.setBalance((user.getBalance() + deposit));
                        handleTransaction("Transaction completed");
                        cardLayout.show(cardPanel, "Transaction");
                    } else {
                        handleTransaction("Invalid amount entered, try again!");
                    }
                } else {
                    handleTransaction("Invalid input, try again!");
                }
            }
        });

        backButton = new JButton("Back");
        exitButton = new JButton("Exit");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Transaction");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(amount);
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private boolean validAmount(String amount) {
        try {
            Integer.parseInt(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    private void handleTransaction(String message) {
        JOptionPane.showMessageDialog(this, message, "Transaction Details",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
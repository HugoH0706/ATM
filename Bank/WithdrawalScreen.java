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
import javax.swing.SwingConstants;
import javax.xml.crypto.Data;

public class WithdrawalScreen extends JPanel {
    private JLabel menu;
    private String name;
    private JButton twentyButton;
    private JButton fiftyButton;
    private JButton hundredButton;
    private JButton otherButton;
    private JButton backButton;
    private JButton exitButton;
    private Database db;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public WithdrawalScreen(CardLayout cardLayout, JPanel cardPanel, Database db) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.db = db;
        setLayout(new BorderLayout());

        menu = new JLabel("Press the amount you want to withdraw!", SwingConstants.CENTER);
        add(menu, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        twentyButton = new JButton("$20");
        fiftyButton = new JButton("$50");
        hundredButton = new JButton("$100");
        otherButton = new JButton("Other");
        exitButton = new JButton("Exit");
        backButton = new JButton("Back");

        twentyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawl(20);
            }
        });

        fiftyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawl(50);
            }
        });

        hundredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawl(100);
            }
        });

        otherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cardLayout.show(cardPanel, "Other");
            }
        });

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

        buttonPanel.add(twentyButton);
        buttonPanel.add(fiftyButton);
        buttonPanel.add(hundredButton);
        buttonPanel.add(otherButton);
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

    }

    private void handleTransaction(String message) {
        JOptionPane.showMessageDialog(this, message, "Transaction Details",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void setName(String name) {
        this.name = name;
    }

    private void withdrawl(int amount) {
        User user = db.getUser(name);
        if (amount <= user.getBalance()) {
            user.setBalance((user.getBalance() - amount));
            handleTransaction("Transaction completed");
        } else {
            handleTransaction("Transaction failed");
        }
    }

}
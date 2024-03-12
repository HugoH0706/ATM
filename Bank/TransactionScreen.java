package Bank;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TransactionScreen extends JPanel {
    private JLabel menu;
    private String name;
    private int balance;
    private JButton withdrawalButton;
    private JButton depositButton;
    private JButton balanceButton;
    private JButton exitButton;
    private WithdrawalScreen withdrawalScreen;
    private BalanceScreen balanceScreen;
    private Database db;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public TransactionScreen(CardLayout cardLayout, JPanel cardPanel, Database db) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.db = db;

        withdrawalScreen = new WithdrawalScreen(cardLayout, cardPanel, db);
        cardPanel.add(withdrawalScreen, "Withdrawal");
        balanceScreen = new BalanceScreen(cardLayout, cardPanel, db);
        cardPanel.add(balanceScreen, "Balance");

        setLayout(new BorderLayout());

        menu = new JLabel("Welcome!", SwingConstants.CENTER);
        add(menu, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        withdrawalButton = new JButton("Withdrawal");
        depositButton = new JButton("Deposit");
        balanceButton = new JButton("Balance");
        exitButton = new JButton("Exit");

        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Withdrawal");
                withdrawalScreen.setName(name);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cardLayout.show(cardPanel, "Deposit");
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Balance");
                balanceScreen.setDisplay(name, balance);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(withdrawalButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void setVariables(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
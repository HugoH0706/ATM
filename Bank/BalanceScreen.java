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
import javax.swing.border.Border;

public class BalanceScreen extends JPanel {
    private JLabel menu;
    private JButton backButton;
    private JButton exitButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Database db;

    public BalanceScreen(CardLayout cardLayout, JPanel cardPanel, Database db) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.db = db;

        setLayout(new BorderLayout());

        menu = new JLabel("", SwingConstants.CENTER);
        add(menu, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
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

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void setDisplay(String name, int balance) {
        User user = db.getUser(name);
        menu.setText("Welcome " + name + ", your balance = $" + user.getBalance());
    }
}

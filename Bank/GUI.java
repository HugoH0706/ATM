package Bank;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Database db = new Database();
    private LoginScreen loginScreen;

    public GUI() {
        super("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginScreen = new LoginScreen(cardLayout, cardPanel, db);
        cardPanel.add(loginScreen, "Login");

        add(cardPanel);

        cardLayout.show(cardPanel, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}

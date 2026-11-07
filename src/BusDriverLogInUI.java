import javax.swing.*;
import java.awt.*;

public class BusDriverLogInUI {

    private final BusDriverLogIn busDriverLogIn;

    public BusDriverLogInUI(BusDriverLogIn busDriverLogIn) {
        this.busDriverLogIn = busDriverLogIn;

        // Get components using getters
        JLabel lblUniversityLogo = busDriverLogIn.getLblUniversityLogo();
        JPanel usernamePanel = busDriverLogIn.getUsernamePanel();
        JPanel passwordPanel = busDriverLogIn.getPasswordPanel();
        JTextField txtDriverUsername = busDriverLogIn.getTxtDriverUsername();
        JPasswordField txtDriverPassword = busDriverLogIn.getTxtDriverPassword();
        JButton btnDriverLogIn = busDriverLogIn.getBtnDriverLogIn();
        JPanel loginCardPanel = busDriverLogIn.getLoginCardPanel();
        JPanel goldLinePanel = busDriverLogIn.getGoldLinePanel();

        // University logo
        ImageIcon logoIcon = getIcon("eu-logo.jpg", 35, 35);

        lblUniversityLogo.setIcon(logoIcon);

        lblUniversityLogo.setText(
                "<html><center>" +
                        "<b>Enverga University</b><br>" +
                        "<font color='#888888' size='2'>SCHOOL BUS SEAT RESERVATION</font>" +
                        "</center></html>"
        );

        lblUniversityLogo.setHorizontalAlignment(SwingConstants.CENTER);

        lblUniversityLogo.setForeground(new Color(128, 0, 32));

        lblUniversityLogo.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        // username icon
        JLabel userLabel = new JLabel(
                getIcon("us.png", 25, 25)
        );

        JLabel passwordLabel = new JLabel(
                getIcon("password_icon.png", 25, 25)
        );

        // USERNAME PANEL
        usernamePanel.removeAll();
        usernamePanel.setLayout(new BorderLayout(12, 0));
        usernamePanel.setBackground(Color.WHITE);

        usernamePanel.add(userLabel, BorderLayout.WEST);
        usernamePanel.add(txtDriverUsername, BorderLayout.CENTER);

        usernamePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        BorderFactory.createEmptyBorder(5, 12, 5, 12)
                )
        );

        // PASSWORD PANEL
        passwordPanel.removeAll();
        passwordPanel.setLayout(new BorderLayout(12, 0));
        passwordPanel.setBackground(Color.WHITE);

        passwordPanel.add(passwordLabel, BorderLayout.WEST);
        passwordPanel.add(txtDriverPassword, BorderLayout.CENTER);

        passwordPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        BorderFactory.createEmptyBorder(8, 16, 8, 16)
                )
        );

        // Make username and password boxes smaller
        Dimension inputSize = new Dimension(0, 15);

        usernamePanel.setPreferredSize(inputSize);
        usernamePanel.setMinimumSize(new Dimension(0, 15));
        usernamePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 15)
        );

        passwordPanel.setPreferredSize(inputSize);
        passwordPanel.setMinimumSize(new Dimension(0, 15));
        passwordPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 15)
        );

        // USERNAME TEXT FIELD
        txtDriverUsername.setEditable(true);
        txtDriverUsername.setEnabled(true);
        txtDriverUsername.setFocusable(true);
        txtDriverUsername.setBackground(Color.WHITE);
        txtDriverUsername.setForeground(Color.DARK_GRAY);
        txtDriverUsername.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtDriverUsername.setBorder(null);

        // PASSWORD TEXT FIELD
        txtDriverPassword.setEditable(true);
        txtDriverPassword.setEnabled(true);
        txtDriverPassword.setFocusable(true);
        txtDriverPassword.setBackground(Color.WHITE);
        txtDriverPassword.setForeground(Color.DARK_GRAY);
        txtDriverPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtDriverPassword.setBorder(null);

        // LOGIN BUTTON
        btnDriverLogIn.setBackground(new Color(128, 0, 32));
        btnDriverLogIn.setForeground(Color.WHITE);
        btnDriverLogIn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );
        btnDriverLogIn.setPreferredSize(new Dimension(0, 65));
        btnDriverLogIn.setBorderPainted(false);
        btnDriverLogIn.setOpaque(false);
        btnDriverLogIn.setContentAreaFilled(false);

        loginCardPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        // GOLD LINE
        goldLinePanel.setBackground(new Color(212, 175, 55));
        goldLinePanel.setOpaque(true);

        goldLinePanel.setPreferredSize(new Dimension(0, 2));
        goldLinePanel.setMinimumSize(new Dimension(0, 2));
        goldLinePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 2)
        );

        // CUSTOMIZE UI
        customizeLoginUI();

        usernamePanel.revalidate();
        usernamePanel.repaint();

        passwordPanel.revalidate();
        passwordPanel.repaint();

        // Password placeholder
        addPasswordPlaceholder(
                txtDriverPassword,
                "Enter your password"
        );
    }

    private ImageIcon getIcon(String filename, int width, int height) {

        java.net.URL imageURL =
                getClass().getResource("/images/" + filename);

        if (imageURL == null) {
            System.out.println("IMAGE NOT FOUND: " + filename);
            return null;
        }

        ImageIcon original = new ImageIcon(imageURL);

        Image scaledImage = original.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(scaledImage);
    }

    private void customizeLoginUI() {

        Color burgundy = new Color(128, 0, 32);
        Color gold = new Color(212, 175, 55);
        Color warmWhite = new Color(247, 245, 242);

        // Get components using getters
        JPanel driverLoginPanel = busDriverLogIn.getDriverLoginPanel();
        JLabel lblTitle = busDriverLogIn.getLblTitle();
        JLabel lblUniversityLogo = busDriverLogIn.getLblUniversityLogo();
        JLabel lblWelcome = busDriverLogIn.getLblWelcome();
        JButton btnDriverLogIn = busDriverLogIn.getBtnDriverLogIn();
        JButton btnDriverLogReturn = busDriverLogIn.getBtnDriverLogReturn();

        // Main background
        driverLoginPanel.setBackground(warmWhite);

        // Title
        lblTitle.setForeground(burgundy);
        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblUniversityLogo.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        lblWelcome.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        lblWelcome.setForeground(Color.GRAY);

        // Login button
        btnDriverLogIn.setBackground(burgundy);
        btnDriverLogIn.setForeground(Color.WHITE);

        btnDriverLogIn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btnDriverLogIn.setPreferredSize(
                new Dimension(0, 50)
        );

        btnDriverLogIn.setFocusPainted(false);
        btnDriverLogIn.setBorderPainted(false);
        btnDriverLogIn.setOpaque(false);
        btnDriverLogIn.setContentAreaFilled(false);

        // Sign up button

        // Return button
        btnDriverLogReturn.setBackground(warmWhite);
    }

    private void addPasswordPlaceholder(
            JPasswordField field,
            String placeholder) {

        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.DARK_GRAY);
                    field.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                if (field.getPassword().length == 0) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }
}
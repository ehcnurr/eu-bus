import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BusDriverLogIn {
    private JTextField txtDriverUsername;
    private JPasswordField txtDriverPassword;
    private JButton btnDriverLogIn;
    private JButton btnDriverLogReturn;
    private JPanel driverLoginPanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel loginCardPanel;
    private JLabel lblTitle;
    private JLabel lblWelcome;
    private JLabel lblUniversityLogo;
    private JPanel goldLinePanel;

    public BusDriverLogIn() {
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

        //username icon
        JLabel userLabel = new JLabel(
                getIcon("us.png", 25, 25)
        );

        JLabel passwordLabel = new JLabel(
                getIcon("password_icon.png", 25, 25)
        );

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
        usernamePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));

        passwordPanel.setPreferredSize(inputSize);
        passwordPanel.setMinimumSize(new Dimension(0, 15));
        passwordPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));

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
        // Change main panel layout
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

        // Username placeholder
// Password placeholder
        addPasswordPlaceholder(txtDriverPassword, "Enter your password");

        btnDriverLogReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("MAIN");
                    Main form = new Main();

                    JPanel panel = form.getMainPanel();
                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getMainPanel returned null. Check Main.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(driverLoginPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnDriverLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtDriverUsername.getText().trim();
                String inputPassword = txtDriverPassword.getText().trim();

                String query = """
                        SELECT * FROM drivers
                        WHERE username = ? AND password = ?
                        """;

                try (
                    Connection con = DatabaseConnection.getConnection();
                    PreparedStatement stmt = con.prepareStatement(query);
                ) {

                    stmt.setString(1, inputUsername);
                    stmt.setString(2, inputPassword);

                    ResultSet rs = stmt.executeQuery();

                    if (!(rs.next())) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid username or password.",
                                "ERROR",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String username = rs.getString("username");
                    int selectedBusNum = rs.getInt("selected_bus");


                    JOptionPane.showMessageDialog(null, "Login Successful!");

                    // if username already has a selected bus, it will open the BusStatus panel instead of BusDetails

                    if (selectedBusNum > 0) {
                        try {
                            JFrame frame = new JFrame("BUS STATUS");
                            BusStatus form = new BusStatus(selectedBusNum, username);

                            JPanel panel = form.getBusStatusPanel();

                            if (panel == null) {
                                JOptionPane.showMessageDialog(null,
                                        "Error: getDriverLoginPanel returned null. Check BusStatus.java!",
                                        "Null Panel Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            frame.setContentPane(panel);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setSize(490, 720);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);

                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(driverLoginPanel);
                            if (currentFrame != null) {
                                currentFrame.dispose();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            JFrame frame = new JFrame("SELECT A BUS");
                            BusDetails form = new BusDetails(username);

                            JPanel panel = form.getBusDetailsPanel();

                            if (panel == null) {
                                JOptionPane.showMessageDialog(null,
                                        "Error: getStudentSignUpPanel returned null. Check BusDetails.java!",
                                        "Null Panel Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            frame.setContentPane(panel);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setSize(490, 720);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);

                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(driverLoginPanel);
                            if (currentFrame != null) {
                                currentFrame.dispose();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                }
            }
        });
    }

    public JPanel getDriverLoginPanel() {
        return driverLoginPanel;
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
            JPasswordField field, String placeholder) {

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnDriverLogIn = new RoundedButton("LOG IN");
        btnDriverLogReturn = new RoundedButton("RETURN");
        loginCardPanel = new RoundedPanel();

        txtDriverUsername =
                new PlaceholderTextField("Enter your name");
    }
}

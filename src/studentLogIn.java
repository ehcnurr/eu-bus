import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class studentLogIn {
    private JTextField txtStudentUsername;
    private JPasswordField txtStudentPassword;
    private JButton btnStudentLogIn;
    private JButton btnStudentLogReturn;
    private JPanel studentLoginPanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JLabel lblTitle;
    private JPanel loginCardPanel;
    private JLabel lblSignUpLink;
    private JLabel lblWelcome;
    private JPanel goldLinePanel;
    private JLabel lblUniversityLogo;

    public studentLogIn() {

        // UNIVERSITY LOGO
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

        // USERNAME PANEL
        usernamePanel.removeAll();
        usernamePanel.setLayout(new BorderLayout(12, 0));
        usernamePanel.setBackground(Color.WHITE);

        usernamePanel.add(userLabel, BorderLayout.WEST);
        usernamePanel.add(txtStudentUsername, BorderLayout.CENTER);

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
        passwordPanel.add(txtStudentPassword, BorderLayout.CENTER);

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
        txtStudentUsername.setEditable(true);
        txtStudentUsername.setEnabled(true);
        txtStudentUsername.setFocusable(true);
        txtStudentUsername.setBackground(Color.WHITE);
        txtStudentUsername.setForeground(Color.DARK_GRAY);
        txtStudentUsername.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtStudentUsername.setBorder(null);

        // PASSWORD TEXT FIELD
        txtStudentPassword.setEditable(true);
        txtStudentPassword.setEnabled(true);
        txtStudentPassword.setFocusable(true);
        txtStudentPassword.setBackground(Color.WHITE);
        txtStudentPassword.setForeground(Color.DARK_GRAY);
        txtStudentPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtStudentPassword.setBorder(null);

        // LOGIN BUTTON
        btnStudentLogIn.setBackground(new Color(128, 0, 32));
        btnStudentLogIn.setForeground(Color.WHITE);
        btnStudentLogIn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );
        btnStudentLogIn.setPreferredSize(new Dimension(0, 65));
        btnStudentLogIn.setBorderPainted(false);
        btnStudentLogIn.setOpaque(false);
        btnStudentLogIn.setContentAreaFilled(false);

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

        customizeSignUpLink();
        // Username placeholder
// Password placeholder
        addPasswordPlaceholder(txtStudentPassword, "Enter your password");

        btnStudentLogReturn.addActionListener(new ActionListener() {
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

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(studentLoginPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnStudentLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtStudentUsername.getText().trim();
                String inputPassword = txtStudentPassword.getText().trim();

                String query = """
                        SELECT * FROM students_or_staff
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

                    JOptionPane.showMessageDialog(null, "Login Successful!");

                    try {
                        JFrame frame = new JFrame("AVAILABLE BUSES");
                        BusesAvailable form = new BusesAvailable();

                        JPanel panel = form.getAvailableBusPanel();
                        if (panel == null) {
                            JOptionPane.showMessageDialog(null,
                                    "Error: getAvailableBusPanel returned null. Check BusesAvailable.java!",
                                    "Null Panel Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        frame.setContentPane(panel);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(490, 720);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(studentLoginPanel);
                        if (currentFrame != null) {
                            currentFrame.dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });
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
        studentLoginPanel.setBackground(warmWhite);

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
        btnStudentLogIn.setBackground(burgundy);
        btnStudentLogIn.setForeground(Color.WHITE);

        btnStudentLogIn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btnStudentLogIn.setPreferredSize(
                new Dimension(0, 50)
        );

        btnStudentLogIn.setFocusPainted(false);
        btnStudentLogIn.setBorderPainted(false);
        btnStudentLogIn.setOpaque(false);
        btnStudentLogIn.setContentAreaFilled(false);

        // Sign up button

        // Return button
        btnStudentLogReturn.setBackground(warmWhite);
    }


    public JPanel getStudentLoginPanel() {
        return studentLoginPanel;
    }

    private void customizeSignUpLink() {

        lblSignUpLink.setText(
                "<html>Don't have an account? " +
                        "<font color='#800020'><u><b>Sign Up</b></u></font>" +
                        "</html>"
        );

        lblSignUpLink.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        lblSignUpLink.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        openSignUpPage();
                    }
                }
        );
    }

    private void openSignUpPage() {

        JFrame frame = new JFrame(
                "STUDENT / STAFF ACCOUNT SIGN UP"
        );

        StudentSignUp form = new StudentSignUp();

        JPanel panel = form.getStudentSignUpPanel();

        if (panel == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to open Sign Up page."
            );
            return;
        }

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(490, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFrame currentFrame =
                (JFrame) SwingUtilities.getWindowAncestor(
                        studentLoginPanel
                );

        if (currentFrame != null) {
            currentFrame.dispose();
        }
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
        btnStudentLogIn = new RoundedButton("LOG IN");
        btnStudentLogReturn = new RoundedButton("RETURN");
        loginCardPanel = new RoundedPanel();


        txtStudentUsername =
                new PlaceholderTextField("Enter your name");
    }
}

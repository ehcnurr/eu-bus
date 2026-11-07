
import javax.swing.*;
import java.awt.*;

public class StudentLogInUI {

    private final studentLogIn form;

    public StudentLogInUI(studentLogIn form) {
        this.form = form;

        customizeUniversityLogo();
        customizeUsernamePanel();
        customizePasswordPanel();
        customizeLoginButton();
        customizeGoldLine();
        customizeLoginUI();
        customizeSignUpLink();

        addPasswordPlaceholder(
                form.getTxtStudentPassword(),
                "Enter your password"
        );
    }

    // UNIVERSITY LOGO
    private void customizeUniversityLogo() {

        JLabel lblUniversityLogo = form.getLblUniversityLogo();

        ImageIcon logoIcon = getIcon("eu-logo.jpg", 35, 35);

        lblUniversityLogo.setIcon(logoIcon);

        lblUniversityLogo.setText(
                "<html><center>" +
                        "<b>Enverga University</b><br>" +
                        "<font color='#888888' size='2'>" +
                        "SCHOOL BUS SEAT RESERVATION" +
                        "</font></center></html>"
        );

        lblUniversityLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblUniversityLogo.setForeground(new Color(128, 0, 32));

        lblUniversityLogo.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );
    }

    // USERNAME PANEL
    private void customizeUsernamePanel() {

        JPanel usernamePanel = form.getUsernamePanel();
        JTextField txtStudentUsername = form.getTxtStudentUsername();

        JLabel userLabel = new JLabel(
                getIcon("us.png", 25, 25)
        );

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

        usernamePanel.setPreferredSize(new Dimension(0, 45));
        usernamePanel.setMinimumSize(new Dimension(0, 45));
        usernamePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        txtStudentUsername.setEditable(true);
        txtStudentUsername.setEnabled(true);
        txtStudentUsername.setFocusable(true);
        txtStudentUsername.setBackground(Color.WHITE);
        txtStudentUsername.setForeground(Color.DARK_GRAY);
        txtStudentUsername.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtStudentUsername.setBorder(null);

        usernamePanel.revalidate();
        usernamePanel.repaint();
    }

    // PASSWORD PANEL
    private void customizePasswordPanel() {

        JPanel passwordPanel = form.getPasswordPanel();
        JPasswordField txtStudentPassword = form.getTxtStudentPassword();

        JLabel passwordLabel = new JLabel(
                getIcon("password_icon.png", 25, 25)
        );

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

        passwordPanel.setPreferredSize(new Dimension(0, 45));
        passwordPanel.setMinimumSize(new Dimension(0, 45));
        passwordPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        txtStudentPassword.setEditable(true);
        txtStudentPassword.setEnabled(true);
        txtStudentPassword.setFocusable(true);
        txtStudentPassword.setBackground(Color.WHITE);
        txtStudentPassword.setForeground(Color.DARK_GRAY);
        txtStudentPassword.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        txtStudentPassword.setBorder(null);

        passwordPanel.revalidate();
        passwordPanel.repaint();
    }

    // LOGIN BUTTON
    private void customizeLoginButton() {

        JButton btnStudentLogIn = form.getBtnStudentLogIn();

        btnStudentLogIn.setBackground(new Color(128, 0, 32));
        btnStudentLogIn.setForeground(Color.WHITE);

        btnStudentLogIn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btnStudentLogIn.setPreferredSize(
                new Dimension(0, 50)
        );

        btnStudentLogIn.setFocusPainted(false);
        btnStudentLogIn.setBorderPainted(false);
        btnStudentLogIn.setContentAreaFilled(true);
        btnStudentLogIn.setOpaque(true);

        form.getLoginCardPanel().setBorder(
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );
    }

    // GOLD LINE
    private void customizeGoldLine() {

        JPanel goldLinePanel = form.getGoldLinePanel();

        goldLinePanel.setBackground(
                new Color(212, 175, 55)
        );

        goldLinePanel.setOpaque(true);

        goldLinePanel.setPreferredSize(
                new Dimension(0, 2)
        );

        goldLinePanel.setMinimumSize(
                new Dimension(0, 2)
        );

        goldLinePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 2)
        );
    }

    // CUSTOMIZE MAIN UI
    private void customizeLoginUI() {

        Color burgundy = new Color(128, 0, 32);
        Color warmWhite = new Color(247, 245, 242);

        form.getStudentLoginPanel().setBackground(warmWhite);

        form.getLblTitle().setForeground(burgundy);
        form.getLblTitle().setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        form.getLblUniversityLogo().setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        form.getLblWelcome().setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        form.getLblWelcome().setForeground(Color.GRAY);

        form.getBtnStudentLogReturn().setBackground(warmWhite);
    }

    // SIGN UP LINK
    private void customizeSignUpLink() {

        JLabel lblSignUpLink = form.getLblSignUpLink();

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

    // OPEN SIGN UP PAGE
    private void openSignUpPage() {

        JFrame frame = new JFrame(
                "STUDENT / STAFF ACCOUNT SIGN UP"
        );

        StudentSignUp signupForm = new StudentSignUp();

        JPanel panel = signupForm.getStudentSignUpPanel();

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
                        form.getStudentLoginPanel()
                );

        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }

    // PASSWORD PLACEHOLDER
    private void addPasswordPlaceholder(
            JPasswordField field,
            String placeholder) {

        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(
                new java.awt.event.FocusAdapter() {

                    @Override
                    public void focusGained(
                            java.awt.event.FocusEvent e) {

                        if (String.valueOf(field.getPassword())
                                .equals(placeholder)) {

                            field.setText("");
                            field.setForeground(Color.DARK_GRAY);
                            field.setEchoChar('•');
                        }
                    }

                    @Override
                    public void focusLost(
                            java.awt.event.FocusEvent e) {

                        if (field.getPassword().length == 0) {

                            field.setEchoChar((char) 0);
                            field.setText(placeholder);
                            field.setForeground(Color.GRAY);
                        }
                    }
                }
        );
    }

    // IMAGE ICON
    private ImageIcon getIcon(
            String filename,
            int width,
            int height) {

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
}

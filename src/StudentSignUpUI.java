
import javax.swing.*;
import java.awt.*;

public class StudentSignUpUI {

    private StudentSignUp signUp;

    // Components from StudentSignUp.java
    private JTextField txtCreateUsername;
    private JPasswordField txtCreatePassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnCreateAcc;
    private JButton btnSignUpReturn;

    private JPanel studentSignUpPanel;
    private JPanel signUpCardPanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel confirmPassPanel;

    private JLabel lblUniversityLogo;
    private JLabel lblTitle;
    private JPanel goldLinePanel;
    private JLabel lblEnter;

    // CONSTRUCTOR
    public StudentSignUpUI(StudentSignUp signUp) {

        this.signUp = signUp;

        // GET COMPONENTS USING GETTERS
        txtCreateUsername = signUp.getTxtCreateUsername();
        txtCreatePassword = signUp.getTxtCreatePassword();
        txtConfirmPassword = signUp.getTxtConfirmPassword();

        btnCreateAcc = signUp.getBtnCreateAcc();
        btnSignUpReturn = signUp.getBtnSignUpReturn();

        studentSignUpPanel = signUp.getStudentSignUpPanel();
        signUpCardPanel = signUp.getSignUpCardPanel();
        usernamePanel = signUp.getUsernamePanel();
        passwordPanel = signUp.getPasswordPanel();
        confirmPassPanel = signUp.getConfirmPassPanel();

        lblUniversityLogo = signUp.getLblUniversityLogo();
        lblTitle = signUp.getLblTitle();
        goldLinePanel = signUp.getGoldLinePanel();
        lblEnter = signUp.getLblEnter();

        // CUSTOMIZE UI
        customizeUniversity();
        customizeInputPanels();
        customizeTextFields();
        customizeButtons();
        customizeLoginUI();

        // Password placeholders
        addPasswordPlaceholder(txtCreatePassword, "Enter password");
        addPasswordPlaceholder(txtConfirmPassword, "Confirm password");
    }

    // UNIVERSITY LOGO
    private void customizeUniversity() {

        lblUniversityLogo.setIcon(
                getIcon("eu-logo.jpg", 35, 35)
        );

        lblUniversityLogo.setText(
                "<html><center>" +
                        "<b>Enverga University</b><br>" +
                        "<font color='#888888' size='2'>" +
                        "SCHOOL BUS SEAT RESERVATION" +
                        "</font></center></html>"
        );

        lblUniversityLogo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        lblUniversityLogo.setForeground(
                new Color(128, 0, 32)
        );

        lblUniversityLogo.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );
    }

    // INPUT PANELS
    private void customizeInputPanels() {

        JLabel userLabel = new JLabel(
                getIcon("us.png", 25, 25)
        );

        JLabel passwordLabel = new JLabel(
                getIcon("password_icon.png", 25, 25)
        );

        JLabel confirmPasswordLabel = new JLabel(
                getIcon("confirm_pass.png", 18, 18)
        );

        setupInputPanel(
                usernamePanel,
                userLabel,
                txtCreateUsername
        );

        setupInputPanel(
                passwordPanel,
                passwordLabel,
                txtCreatePassword
        );

        setupInputPanel(
                confirmPassPanel,
                confirmPasswordLabel,
                txtConfirmPassword
        );
    }

    private void setupInputPanel(
            JPanel panel,
            JLabel icon,
            JComponent field) {

        panel.removeAll();

        panel.setLayout(
                new BorderLayout(12, 0)
        );

        panel.setBackground(Color.WHITE);

        panel.add(icon, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 16, 8, 16
                        )
                )
        );

        panel.setPreferredSize(
                new Dimension(0, 55)
        );

        panel.setMinimumSize(
                new Dimension(0, 55)
        );

        panel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 55)
        );

        panel.revalidate();
        panel.repaint();
    }

    // TEXT FIELDS
    private void customizeTextFields() {

        JTextField[] fields = {
                txtCreateUsername
        };

        for (JTextField field : fields) {

            field.setEditable(true);
            field.setEnabled(true);
            field.setFocusable(true);

            field.setBackground(Color.WHITE);
            field.setForeground(Color.DARK_GRAY);

            field.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13)
            );

            field.setBorder(null);
        }

        JPasswordField[] passwordFields = {
                txtCreatePassword,
                txtConfirmPassword
        };

        for (JPasswordField field : passwordFields) {

            field.setEditable(true);
            field.setEnabled(true);
            field.setFocusable(true);

            field.setBackground(Color.WHITE);
            field.setForeground(Color.DARK_GRAY);

            field.setFont(
                    new Font("Segoe UI", Font.PLAIN, 13)
            );

            field.setBorder(null);
        }
    }

    // BUTTONS
    private void customizeButtons() {

        Color burgundy = new Color(128, 0, 32);

        btnCreateAcc.setBackground(burgundy);
        btnCreateAcc.setForeground(Color.WHITE);

        btnCreateAcc.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btnCreateAcc.setPreferredSize(
                new Dimension(0, 50)
        );

        btnCreateAcc.setFocusPainted(false);
        btnCreateAcc.setBorderPainted(false);

        // Keep custom button painting if using RoundedButton.
        btnCreateAcc.setOpaque(false);
        btnCreateAcc.setContentAreaFilled(false);

        btnSignUpReturn.setBackground(
                new Color(247, 245, 242)
        );
    }

    // MAIN UI CUSTOMIZATION
    private void customizeLoginUI() {

        Color burgundy = new Color(128, 0, 32);
        Color gold = new Color(212, 175, 55);
        Color warmWhite = new Color(247, 245, 242);

        studentSignUpPanel.setBackground(warmWhite);

        // SIGN UP CARD
        signUpCardPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        // TITLE
        lblTitle.setForeground(burgundy);

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        // INSTRUCTIONS
        lblEnter.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        lblEnter.setForeground(Color.GRAY);

        // GOLD LINE
        goldLinePanel.setBackground(gold);
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

        studentSignUpPanel.revalidate();
        studentSignUpPanel.repaint();
    }

    // IMAGE ICON
    private ImageIcon getIcon(
            String filename,
            int width,
            int height) {

        java.net.URL imageURL =
                getClass().getResource("/images/" + filename);

        if (imageURL == null) {
            System.out.println(
                    "IMAGE NOT FOUND: " + filename
            );
            return null;
        }

        ImageIcon original = new ImageIcon(imageURL);

        Image scaledImage = original.getImage()
                .getScaledInstance(
                        width,
                        height,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(scaledImage);
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

                        if (String.valueOf(
                                field.getPassword()
                        ).equals(placeholder)) {

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
}

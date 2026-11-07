import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentSignUp {
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

    public JTextField getTxtCreateUsername() {
        return txtCreateUsername;
    }

    public JPasswordField getTxtCreatePassword() {
        return txtCreatePassword;
    }

    public JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public JButton getBtnCreateAcc() {
        return btnCreateAcc;
    }

    public JButton getBtnSignUpReturn() {
        return btnSignUpReturn;
    }

    public JPanel getSignUpCardPanel() {
        return signUpCardPanel;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public JPanel getConfirmPassPanel() {
        return confirmPassPanel;
    }

    public JLabel getLblUniversityLogo() {
        return lblUniversityLogo;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JPanel getGoldLinePanel() {
        return goldLinePanel;
    }

    public JLabel getLblEnter() {
        return lblEnter;
    }

    public StudentSignUp() {
        new StudentSignUpUI(this);

        btnSignUpReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("STUDENT / STAFF LOGIN");
                    studentLogIn form = new studentLogIn();

                    JPanel panel = form.getStudentLoginPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getStudentLoginPanel returned null. Check studentLogIn.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(studentSignUpPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnCreateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtCreateUsername.getText().trim();
                String password = txtCreatePassword.getText().trim();
                String confPassword = txtConfirmPassword.getText().trim();

                if (username.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Username or password fields cannot be empty.");
                    return;
                }

                if (!(password.equals(confPassword))) {
                    JOptionPane.showMessageDialog(null,
                            "Passwords do not match.");
                    return;
                }

                String query = """
                INSERT INTO students_or_staff (username, password)
                VALUES (?, ?)
                """;

                try (Connection con = DatabaseConnection.getConnection();
                     PreparedStatement stmt = con.prepareStatement(query)) {

                    stmt.setString(1, username);
                    stmt.setString(2, password);

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null,
                                "Account created successfully!");

                        txtCreateUsername.setText("");
                        txtCreatePassword.setText("");
                        txtConfirmPassword.setText("");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Database Error: " + ex.getMessage());
                }
            }
        });
    }

    public JPanel getStudentSignUpPanel() {
        return studentSignUpPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnSignUpReturn = new RoundedButton("RETURN");
        btnCreateAcc= new RoundedButton("CREATE ACCOUNT");
        signUpCardPanel = new RoundedPanel();

        txtCreateUsername = new PlaceholderTextField("Enter your username");
    }
}

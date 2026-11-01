import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentSignUp {
    private JTextField txtCreateUsername;
    private JTextField txtCreatePassword;
    private JTextField txtConfirmPassword;
    private JButton btnCreateAcc;
    private JButton btnSignUpReturn;
    private JPanel studentSignUpPanel;

    public StudentSignUp() {
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
                    frame.setSize(500, 400);
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
                            "Username of password fields cannot be empty.");
                    return;
                }

                if (!(password.equals(confPassword))) {
                    JOptionPane.showMessageDialog(null,
                            "Passwords are not equal.");
                    return;
                }

                String query = """
                        INSERT INTO students_or_staff 
                        VALUES (?, ?)
                        """;

                try {
                    Connection con = DatabaseConnection.getConnection();
                    PreparedStatement stmt = con.prepareStatement(query);

                    stmt.setString(1, username);
                    stmt.setString(2, password);

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Account created successfully!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                }
            }
        });
    }

    public JPanel getStudentSignUpPanel() {
        return studentSignUpPanel;
    }
}

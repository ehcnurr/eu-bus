import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class studentLogIn {
    private JTextField txtStudentUsername;
    private JTextField txtStudentPassword;
    private JButton btnStudentLogIn;
    private JButton btnSignUp;
    private JButton btnStudentLogReturn;
    private JPanel studentLoginPanel;

    public studentLogIn() {
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
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("STUDENT / STAFF ACCOUNT SIGN UP");
                    StudentSignUp form = new StudentSignUp();

                    JPanel panel = form.getStudentSignUpPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getStudentSignUpPanel returned null. Check StudentSignUp.java!",
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

    public JPanel getStudentLoginPanel() {
        return studentLoginPanel;
    }
}

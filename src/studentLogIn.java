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

    public JTextField getTxtStudentUsername() {
        return txtStudentUsername;
    }

    public JPasswordField getTxtStudentPassword() {
        return txtStudentPassword;
    }

    public JButton getBtnStudentLogIn() {
        return btnStudentLogIn;
    }

    public JButton getBtnStudentLogReturn() {
        return btnStudentLogReturn;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JPanel getLoginCardPanel() {
        return loginCardPanel;
    }

    public JLabel getLblSignUpLink() {
        return lblSignUpLink;
    }

    public JLabel getLblWelcome() {
        return lblWelcome;
    }

    public JPanel getGoldLinePanel() {
        return goldLinePanel;
    }

    public JLabel getLblUniversityLogo() {
        return lblUniversityLogo;
    }

    public studentLogIn() {
        new StudentLogInUI(this);

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

    public JPanel getStudentLoginPanel() {
        return studentLoginPanel;
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

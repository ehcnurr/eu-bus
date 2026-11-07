
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

    public JTextField getTxtDriverUsername() {
        return txtDriverUsername;
    }

    public JPasswordField getTxtDriverPassword() {
        return txtDriverPassword;
    }

    public JButton getBtnDriverLogIn() {
        return btnDriverLogIn;
    }

    public JButton getBtnDriverLogReturn() {
        return btnDriverLogReturn;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public JPanel getLoginCardPanel() {
        return loginCardPanel;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JLabel getLblWelcome() {
        return lblWelcome;
    }

    public JLabel getLblUniversityLogo() {
        return lblUniversityLogo;
    }

    public JPanel getGoldLinePanel() {
        return goldLinePanel;
    }

    public BusDriverLogIn() {
        new BusDriverLogInUI(this);

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnDriverLogIn = new RoundedButton("LOG IN");
        btnDriverLogReturn = new RoundedButton("RETURN");
        loginCardPanel = new RoundedPanel();

        txtDriverUsername =
                new PlaceholderTextField("Enter your name");
    }
}

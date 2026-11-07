import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class BusDetails {
    private JPanel busDetailsPanel;
    private JComboBox cmbChooseBus;
    private JTextField txtArrivingTime;
    private JTextField txtDepartingTime;
    private JButton btnSelectBus;
    private JButton btnBusDetailsReturn;
    private JPanel arrivingTimePanel;
    private JPanel departingTimePanel;
    private JPanel busInfoCardPanel;
    private JPanel goldLinePanel;
    private JLabel lblUniversity;
    private String loggedInUsername;
    private JLabel lblSubtitle;
    private JLabel lblSched;
    private JLabel lblChoose;
    private JLabel lblBottom;

    public JComboBox getCmbChooseBus() {
        return cmbChooseBus;
    }

    public JTextField getTxtArrivingTime() {
        return txtArrivingTime;
    }

    public JTextField getTxtDepartingTime() {
        return txtDepartingTime;
    }

    public JButton getBtnSelectBus() {
        return btnSelectBus;
    }

    public JButton getBtnBusDetailsReturn() {
        return btnBusDetailsReturn;
    }

    public JPanel getArrivingTimePanel() {
        return arrivingTimePanel;
    }

    public JPanel getDepartingTimePanel() {
        return departingTimePanel;
    }

    public JPanel getBusInfoCardPanel() {
        return busInfoCardPanel;
    }

    public JPanel getGoldLinePanel() {
        return goldLinePanel;
    }

    public JLabel getLblUniversity() {
        return lblUniversity;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public JLabel getLblSubtitle() {
        return lblSubtitle;
    }

    public JLabel getLblSched() {
        return lblSched;
    }

    public JLabel getLblChoose() {
        return lblChoose;
    }

    public JLabel getLblBottom() {
        return lblBottom;
    }

    public BusDetails(String username) {
        this.loggedInUsername = username;

        new BusDetailsUI(this);

        cmbChooseBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int busNum = cmbChooseBus.getSelectedIndex() + 1;

                String query = """
                        SELECT * FROM buses
                        WHERE bus_num = ? 
                        """;

                try (
                        Connection con = DatabaseConnection.getConnection();
                        PreparedStatement stmt = con.prepareStatement(query);
                ) {
                    stmt.setInt(1, busNum);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String arrival = rs.getString("arrival");
                        String departing = rs.getString("departing");

                        txtArrivingTime.setText(arrival);
                        txtDepartingTime.setText(departing);
                    }

                    rs.close();
                    stmt.close();
                    con.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                }
            }
        });

        btnBusDetailsReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("Bus Login");
                    BusDriverLogIn form = new BusDriverLogIn();

                    JPanel panel = form.getDriverLoginPanel();
                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getAvailableBusPanel returned null. Check BusDriverLogIn.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(busDetailsPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSelectBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bus_num = Integer.parseInt(
                        cmbChooseBus.getSelectedItem().toString().replace("Bus ", "")
                );

                String checkQuery = """
                    SELECT username
                    FROM drivers
                    WHERE selected_bus = ?
                      AND username <> ?
                    """;

                String query = """
                    UPDATE drivers
                    SET selected_bus = ?
                    WHERE username = ?
                    """;

                try (Connection con = DatabaseConnection.getConnection()) {

                    // check if another driver selected this bus
                    try (PreparedStatement checkStmt =
                                 con.prepareStatement(checkQuery)) {

                        checkStmt.setInt(1, bus_num);
                        checkStmt.setString(2, loggedInUsername);

                        try (ResultSet rs = checkStmt.executeQuery()) {
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Bus " + bus_num
                                                + " is already selected by another driver.",
                                        "Bus Unavailable",
                                        JOptionPane.WARNING_MESSAGE
                                );
                                return;
                            }
                        }
                    }

                    // update the current driver's selected bus
                    try (PreparedStatement updateStmt =
                                 con.prepareStatement(query)) {

                        updateStmt.setInt(1, bus_num);
                        updateStmt.setString(2, loggedInUsername);

                        int affectedRows = updateStmt.executeUpdate();

                        if (affectedRows > 0) {
                            String getNameQuery = """
                            SELECT * FROM drivers
                            WHERE username = ?
                            """;

                            // get driver's name from drivers database and update the buses database
                            try (PreparedStatement nameStmt = con.prepareStatement(getNameQuery)) {

                                nameStmt.setString(1, loggedInUsername);

                                try (ResultSet rs = nameStmt.executeQuery()) {

                                    if (rs.next()) {
                                        String driverName = rs.getString("name");

                                        String updateDriverNameQuery = """
                                                                UPDATE buses
                                                                SET driver_name = ?
                                                                WHERE bus_num = ?
                                                                """;

                                        try (PreparedStatement updateNameStmt =
                                                     con.prepareStatement(updateDriverNameQuery)) {

                                            updateNameStmt.setString(1, driverName);
                                            updateNameStmt.setInt(2, bus_num);

                                            int rowsUpdated = updateNameStmt.executeUpdate();

                                            if (rowsUpdated > 0) {
                                                JOptionPane.showMessageDialog(
                                                        null,
                                                        "Bus " + bus_num + " selected successfully!\n"
                                                                + "Driver: " + driverName
                                                );
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null, "Driver name not found."
                                        );
                                    }
                                }
                            }

                            // open status panel after choosing a bus successfully
                            try {
                                JFrame frame = new JFrame("BUS STATUS");
                                BusStatus form = new BusStatus(bus_num, username);

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

                                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(busDetailsPanel);
                                if (currentFrame != null) {
                                    currentFrame.dispose();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Driver account not found.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Database Error: " + ex.getMessage()
                    );
                }
            }
        });
    }

    public JPanel getBusDetailsPanel() {
        return busDetailsPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnSelectBus = new RoundedButton("SELECT BUS");
        btnBusDetailsReturn = new RoundedButton("RETURN");
        busInfoCardPanel = new RoundedPanel();
        arrivingTimePanel = new RoundedPanel();
        departingTimePanel = new RoundedPanel();
        // cmbChooseBus = new RoundedButton(); // not working
    }
}



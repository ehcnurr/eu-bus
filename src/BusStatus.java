import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class BusStatus {
    private JButton btnAtStation;
    private JButton btnHasLeft;
    private JButton btnStatusBack;
    private JPanel busStatusPanel;
    private JButton btnReset;
    private JTabbedPane tabbedPane1;
    public JTable tblHistory;
    private int selectedBusNum;
    private String username;

    public BusStatus(int busNum, String username) {
        this.selectedBusNum = busNum;
        this.username = username;

        loadTransactHistory();

        btnAtStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = "At station";

                String query = """
                    UPDATE buses
                    SET status = ?
                    WHERE bus_num = ?
                    """;

                try (
                        Connection con = DatabaseConnection.getConnection();
                        PreparedStatement updateStmt =
                                con.prepareStatement(query);
                ) {
                    updateStmt.setString(1, status);
                    updateStmt.setInt(2, selectedBusNum);

                    updateStmt.executeUpdate();

                    int affectedRows = updateStmt.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Bus " + selectedBusNum
                                        + " status updated to At station!"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Bus not found. Please check the selected bus number.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                }
            }
        });
        btnHasLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = "Has left";

                String query = """
                    UPDATE buses
                    SET status = ?
                    WHERE bus_num = ?
                    """;

                try (
                        Connection con = DatabaseConnection.getConnection();
                        PreparedStatement updateStmt =
                                con.prepareStatement(query);
                ) {
                    updateStmt.setString(1, status);
                    updateStmt.setInt(2, selectedBusNum);

                    updateStmt.executeUpdate();

                    int affectedRows = updateStmt.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Bus " + selectedBusNum
                                        + " status updated to Has left!"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Bus not found. Please check the selected bus number.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                }
            }
        });

        btnStatusBack.addActionListener(new ActionListener() {
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

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(busStatusPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String resetBusQuery = """
            UPDATE buses
            SET status = 'N/A',
                driver_name = 'N/A'
            WHERE bus_num = ?
            """;

            String resetDriverQuery = """
            UPDATE drivers
            SET selected_bus = 0
            WHERE username = ?
            """;

                try (Connection con = DatabaseConnection.getConnection()) {

                    // Reset the selected bus
                    try (PreparedStatement busStmt =
                                 con.prepareStatement(resetBusQuery)) {

                        busStmt.setInt(1, selectedBusNum);

                        int busRows = busStmt.executeUpdate();

                        if (busRows == 0) {
                            JOptionPane.showMessageDialog(
                                    null, "Selected bus not found."
                            );
                            return;
                        }
                    }

                    // Reset the current driver's selected bus
                    try (PreparedStatement driverStmt =
                                 con.prepareStatement(resetDriverQuery)) {

                        driverStmt.setString(1, username);

                        int driverRows = driverStmt.executeUpdate();

                        if (driverRows == 0) {
                            JOptionPane.showMessageDialog(
                                    null, "Driver account not found."
                            );
                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(
                            null,
                            "Thank you for your service! Your bus has been reset."
                    );

                    // Open the bus selection panel again
                    JFrame frame = new JFrame("SELECT A BUS");
                    BusDetails form = new BusDetails(username);

                    frame.setContentPane(form.getBusDetailsPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    // Close the current BusStatus window
                    JFrame currentFrame =
                            (JFrame) SwingUtilities.getWindowAncestor(busStatusPanel);

                    if (currentFrame != null) {
                        currentFrame.dispose();
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

    private void loadTransactHistory() {
        String query = """
                SELECT
                    reservation_id,
                    bus_num,
                    seat_num,
                    passenger_name,
                    payment_method,
                    payment_provider,
                    account_number,
                    amount_paid,
                    reserved_at
                FROM transaction_history
                WHERE driver_username = ?
                ORDER BY reserved_at DESC
                """;

        String[] columns = {
                "Reservation ID",
                "Bus Number",
                "Seat Number",
                "Passenger Name",
                "Payment Method",
                "E-wallet / Bank",
                "Account Number",
                "Amount Paid",
                "Date and Time Reserved"
        };

        DefaultTableModel history = new DefaultTableModel(columns, 0);

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
        ) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    history.addRow(new Object[] {
                            rs.getInt("reservation_id"),
                            rs.getInt("bus_num"),
                            rs.getString("seat_num"),
                            rs.getString("passenger_name"),
                            rs.getString("payment_method"),
                            rs.getString("payment_provider"),
                            rs.getString("account_number"),
                            rs.getBigDecimal("amount_paid"),
                            rs.getTimestamp("reserved_at")
                    });
                }
            }

            tblHistory.setModel(history);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(busStatusPanel,
                    "Failed to show transaction history: " + e.getMessage());
        }
    }

    public JPanel getBusStatusPanel() {
        return busStatusPanel;
    }
}

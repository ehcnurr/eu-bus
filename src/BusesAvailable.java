import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusesAvailable {
    private JTable tblBusesAvailable;
    private JButton btnSelectBus1;
    private JButton btnSelectBus5;
    private JButton btnSelectBus4;
    private JButton btnSelectBus3;
    private JButton btnSelectBus2;
    private JButton btnBusAvReturn;
    private JPanel availableBusPanel;

    DefaultTableModel tableBuses = new DefaultTableModel();

    public BusesAvailable() {

        tableBuses.addColumn("Bus Number");
        tableBuses.addColumn("Driver Name");
        tableBuses.addColumn("Arriving Time");
        tableBuses.addColumn("Departing Time");
        tableBuses.addColumn("Available Slots");
        tableBuses.addColumn("Status");

        tblBusesAvailable.setModel(tableBuses);

        loadBuses();

        btnBusAvReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("STUDENT / STAFF ACCOUNT SIGN UP");
                    Main form = new Main();

                    JPanel panel = form.getMainPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getStudentSignUpPanel returned null. Check Main.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(availableBusPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton[] busButtons = {
                btnSelectBus1,
                btnSelectBus2,
                btnSelectBus3,
                btnSelectBus4,
                btnSelectBus5
        };

        for (int i = 0; i < busButtons.length; i++) {
            int busNum = i+1;

            busButtons[i].addActionListener(e -> selectBus(busNum));
        }
    }

    private void loadBuses() {
        tableBuses.setRowCount(0); // clear existing rows

        String query = """
        SELECT bus_num, arrival, departing, status, available_slots, driver_name
        FROM buses
        ORDER BY bus_num
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int busNum = rs.getInt("bus_num");
                String arrival = rs.getString("arrival");
                String departing = rs.getString("departing");
                int available_slots = rs.getInt("available_slots");
                String status = rs.getString("status");
                String driver_name = rs.getString("driver_name");

                tableBuses.addRow(new Object[]{
                        busNum,
                        driver_name,
                        arrival,
                        departing,
                        available_slots,
                        status
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    availableBusPanel,
                    "Failed to load buses: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void selectBus(int busNum) {
        String query = """
                SELECT status 
                FROM buses
                WHERE bus_num = ?
                """;

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
        ) {
            stmt.setInt(1, busNum);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getString("status");

                    if (status == null || status.equals("N/A") || status.equals("Has Left")) {
                        JOptionPane.showMessageDialog(availableBusPanel,
                                "Bus " + busNum + " is currently unavailable.",
                                "Bus unavailable",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (status.equals("At station")) {
                        displayWindow(busNum);
                    }
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }
    }

    private void displayWindow(int busNum) {
        try {
            JFrame frame = new JFrame("SEAT SELECTION");
            SeatSelection form = new SeatSelection();

            form.loadBusSeats(busNum);

            JPanel panel = form.getSeatSelectionPanel();

            if (panel == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: getSeatSelectionPanel returned null. Check SeatSelection.java!",
                        "Null Panel Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(380, 530);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(availableBusPanel);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public JPanel getAvailableBusPanel() {
        return availableBusPanel;
    }
}

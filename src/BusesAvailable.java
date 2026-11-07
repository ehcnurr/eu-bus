import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
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
    private JButton btnSelect;
    private JLabel lblBusesAvailable;
    private JPanel headerPanel;
    private JPanel selectionPanel;
    private JPanel tablePanel;
    private JPanel goldLinePanel;
    private JLabel lblSubtitle;
    private JScrollPane tblScrollPane;
    private JLabel lblBusIcon;

    public JTable getTblBusesAvailable() {
        return tblBusesAvailable;
    }

    public JButton getBtnSelectBus1() {
        return btnSelectBus1;
    }

    public JButton getBtnSelectBus2() {
        return btnSelectBus2;
    }

    public JButton getBtnSelectBus3() {
        return btnSelectBus3;
    }

    public JButton getBtnSelectBus4() {
        return btnSelectBus4;
    }

    public JButton getBtnSelectBus5() {
        return btnSelectBus5;
    }

    public JButton getBtnBusAvReturn() {
        return btnBusAvReturn;
    }

    public JButton getBtnSelect() {
        return btnSelect;
    }

    public JLabel getLblBusesAvailable() {
        return lblBusesAvailable;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JPanel getSelectionPanel() {
        return selectionPanel;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JPanel getGoldLinePanel() {
        return goldLinePanel;
    }

    public JLabel getLblSubtitle() {
        return lblSubtitle;
    }

    public JScrollPane getTblScrollPane() {
        return tblScrollPane;
    }

    public JLabel getLblBusIcon() {
        return lblBusIcon;
    }

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

        new BusesAvailableUI(this);

        btnBusAvReturn.addActionListener(new ActionListener() {
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
        SELECT 
            b.bus_num,
            b.arrival,
            b.departing,
            b.status,
            (b.total_slots - (
                SELECT COUNT(*)
                FROM seat_reservations sr
                WHERE sr.bus_num = b.bus_num
            )) AS available_slots,
            b.driver_name
        FROM buses b
        ORDER BY b.bus_num
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

                if (status == null || status.equals("N/A") || status.equals("Has left")) {
                    available_slots = 0;
                }

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

                    if (status == null || status.equals("N/A") || status.equals("Has left")) {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        headerPanel = new RoundedPanel();
        tablePanel = new RoundedPanel();
        selectionPanel = new RoundedPanel();
        tblScrollPane = new RoundedScrollPane(tblBusesAvailable);

        btnSelectBus1 = new RoundedButton("BUS 1");
        btnSelectBus2 = new RoundedButton("BUS 2");
        btnSelectBus3 = new RoundedButton("BUS 3");
        btnSelectBus4 = new RoundedButton("BUS 4");
        btnSelectBus5 = new RoundedButton("BUS 5");

        btnBusAvReturn = new RoundedButton("RETURN");

        lblBusIcon = new JLabel();
    }
}

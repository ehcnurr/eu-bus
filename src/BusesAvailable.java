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

    DefaultTableModel tableBuses = new DefaultTableModel();

    public BusesAvailable() {

        tableBuses.addColumn("Bus Number");
        tableBuses.addColumn("Driver Name");
        tableBuses.addColumn("Arriving Time");
        tableBuses.addColumn("Departing Time");
        tableBuses.addColumn("Available Slots");
        tableBuses.addColumn("Status");

        tblBusesAvailable.setModel(tableBuses);

// Set row height
        tblBusesAvailable.setRowHeight(87);

// Set table font
        tblBusesAvailable.setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13)
        );

// Set table header font
        tblBusesAvailable.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
        );

// Set column widths
        tblBusesAvailable.getColumnModel().getColumn(0).setPreferredWidth(55);  // Bus Number
        tblBusesAvailable.getColumnModel().getColumn(1).setPreferredWidth(85);  // Driver Name
        tblBusesAvailable.getColumnModel().getColumn(2).setPreferredWidth(85);  // Arriving Time
        tblBusesAvailable.getColumnModel().getColumn(3).setPreferredWidth(85);  // Departing Time
        tblBusesAvailable.getColumnModel().getColumn(4).setPreferredWidth(65);  // Available Slots
        tblBusesAvailable.getColumnModel().getColumn(5).setPreferredWidth(75);  // Status

        tblBusesAvailable.getTableHeader().setPreferredSize(
                new java.awt.Dimension(0, 84)
        );

        java.awt.Dimension buttonSize =
                new java.awt.Dimension(120, 45);

        btnSelect.setPreferredSize(new java.awt.Dimension(125, 60));
        btnSelectBus1.setPreferredSize(buttonSize);
        btnSelectBus2.setPreferredSize(buttonSize);
        btnSelectBus3.setPreferredSize(buttonSize);
        btnSelectBus4.setPreferredSize(buttonSize);
        btnSelectBus5.setPreferredSize(buttonSize);

        javax.swing.table.DefaultTableCellRenderer centerRenderer =
                new javax.swing.table.DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        for (int i = 0; i < tblBusesAvailable.getColumnCount(); i++) {
            tblBusesAvailable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        // to change the border radius of the buttons

        JButton[] buttons = {
                btnSelectBus1,
                btnSelectBus2,
                btnSelectBus3,
                btnSelectBus4,
                btnSelectBus5,
                btnBusAvReturn
        };

        for (JButton button : buttons) {

            button.setBackground(new Color(128, 0, 32));
            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font("Segoe UI", Font.BOLD, 14)
            );

            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
        }

        // round the headerPanel

        lblBusesAvailable.setOpaque(false);
        lblBusesAvailable.setForeground(Color.WHITE);

        lblBusesAvailable.setFont(
                new Font("Segoe UI", Font.BOLD, 32)
        );

        lblBusesAvailable.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        loadBuses();

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

        // Custom rounded header panel
        headerPanel = new RoundedPanel();

        // Custom rounded buttons

        btnSelectBus1 = new RoundedButton("BUS 1");
        btnSelectBus2 = new RoundedButton("BUS 2");
        btnSelectBus3 = new RoundedButton("BUS 3");
        btnSelectBus4 = new RoundedButton("BUS 4");
        btnSelectBus5 = new RoundedButton("BUS 5");

        btnBusAvReturn = new RoundedButton("RETURN");
    }
}

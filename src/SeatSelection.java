import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;


public class SeatSelection {
    private JCheckBox chkS11;
    private JCheckBox chkS9;
    private JCheckBox chkS12;
    private JCheckBox chkS10;
    private JCheckBox chkS7;
    private JCheckBox chkS8;
    private JCheckBox chkS5;
    private JCheckBox chkS6;
    private JCheckBox chkS3;
    private JCheckBox chkS4;
    private JCheckBox chkS2;
    private JCheckBox chkS23;
    private JCheckBox chkS25;
    private JCheckBox chkS21;
    private JCheckBox chkS19;
    private JCheckBox chkS17;
    private JCheckBox chkS15;
    private JCheckBox chkS26;
    private JCheckBox chkS24;
    private JCheckBox chkS22;
    private JCheckBox chkS20;
    private JCheckBox chkS18;
    private JCheckBox chkS16;
    private JCheckBox chkS13;
    private JCheckBox chkS14;
    private JCheckBox chkDriver;
    private JCheckBox chkS1;
    private JButton btnSeatSelectReturn;
    private JPanel seatSelectionPanel;
    private int selectedBusNum;

    public SeatSelection() {
        btnSeatSelectReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame();
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
                    frame.setSize(500, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(seatSelectionPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getSeatSelectionPanel() {
        return seatSelectionPanel;
    }

    public void loadBusSeats(int busNum) {
        this.selectedBusNum = busNum;

        Map<String, JCheckBox> seatBoxes = new LinkedHashMap<>();

        seatBoxes.put("S1", chkS1);
        seatBoxes.put("S2", chkS2);
        seatBoxes.put("S3", chkS3);
        seatBoxes.put("S4", chkS4);
        seatBoxes.put("S5", chkS5);
        seatBoxes.put("S6", chkS6);
        seatBoxes.put("S7", chkS7);
        seatBoxes.put("S8", chkS8);
        seatBoxes.put("S9", chkS9);
        seatBoxes.put("S10", chkS10);
        seatBoxes.put("S11", chkS11);
        seatBoxes.put("S12", chkS12);
        seatBoxes.put("S13", chkS13);
        seatBoxes.put("S14", chkS14);
        seatBoxes.put("S15", chkS15);
        seatBoxes.put("S16", chkS16);
        seatBoxes.put("S17", chkS17);
        seatBoxes.put("S18", chkS18);
        seatBoxes.put("S19", chkS19);
        seatBoxes.put("S20", chkS20);
        seatBoxes.put("S21", chkS21);
        seatBoxes.put("S22", chkS22);
        seatBoxes.put("S23", chkS23);
        seatBoxes.put("S24", chkS24);
        seatBoxes.put("S25", chkS25);
        seatBoxes.put("S26", chkS26);

        for (JCheckBox seat: seatBoxes.values()) {
            seat.setSelected(false);
            seat.setEnabled(true);
        }

        String query = """
                SELECT seat_num 
                FROM seat_reservations
                WHERE bus_num = ?
                """;

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setInt(1, busNum);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String seatNum = rs.getString("seat_num");
                    JCheckBox seat = seatBoxes.get(seatNum);

                    if (seat != null) {
                        seat.setSelected(true);
                        seat.setEnabled(false);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
            return;
        }
    }

    private void handleSeatSelection(JCheckBox seat) {
        if (!(seat.isSelected())) {
            return;
        }

        String seatNum = seat.getText();

        int choice = JOptionPane.showConfirmDialog(
                seatSelectionPanel,
                "Do you want to reserve " + seatNum + " on Bus " + selectedBusNum+"?",
                "Confirm seat reservation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            // code to open payment window mamaya q na gagawin
        } else {
            seat.setSelected(false);
        }
    }

    private void setupSeatListeners() {
        JCheckBox[] seats = {
                chkS1, chkS2, chkS3, chkS4, chkS5, chkS6,
                chkS7, chkS8, chkS9, chkS10, chkS11, chkS12,
                chkS13, chkS14, chkS15, chkS16, chkS17, chkS18,
                chkS19, chkS20, chkS21, chkS22, chkS23, chkS24,
                chkS25, chkS26
        };

        for (JCheckBox seat : seats) {
            seat.addActionListener(e -> handleSeatSelection(seat)) ;
        }
    }

}

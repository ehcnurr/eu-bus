import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusStatus {
    private JButton btnAtStation;
    private JButton btnHasLeft;
    private JButton btnStatusBack;
    private JPanel busStatusPanel;
    private int selectedBusNum;

    public BusStatus(int busNum) {
        this.selectedBusNum = busNum;

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
                    frame.setSize(500, 400);
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
    }

    public JPanel getBusStatusPanel() {
        return busStatusPanel;
    }
}

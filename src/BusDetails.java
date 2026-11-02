import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusDetails {
    private JPanel busDetailsPanel;
    private JComboBox cmbChooseBus;
    private JTextField txtArrivingTime;
    private JTextField txtDepartingTime;
    private JButton btnSelectBus;
    private JButton btnBusDetailsReturn;

    public BusDetails() {
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

        btnSelectBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getBusDetailsPanel() {
        return busDetailsPanel;
    }
}



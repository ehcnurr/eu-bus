import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

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
        tableBuses.addColumn("Arriving Time");
        tableBuses.addColumn("Departing Time");

        tblBusesAvailable.setModel(tableBuses);

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
                    frame.setSize(500, 400);
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
    }

    public JPanel getAvailableBusPanel() {
        return availableBusPanel;
    }
}

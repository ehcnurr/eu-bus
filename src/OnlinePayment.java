import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OnlinePayment {
    private JPanel onlinePaymentPanel;
    private JComboBox cmbEWalletBank;
    private JTextField txtAccNum;
    private JButton btnConfirmTransac;
    private JTextField txtAmount;
    private JButton btnHeader;
    private JPanel paymentCardPanel;
    private JSeparator line;
    private JSeparator line2;
    private JPanel headerPanel;
    private int busNum;
    private String seatNum;
    private String passengerName;

    public JSeparator getLine() {
        return line;
    }

    public JSeparator getLine2() {
        return line2;
    }

    public JButton getBtnHeader() {
        return btnHeader;
    }

    public OnlinePayment(int busNum, String name, String seatNum) {
        this.busNum = busNum;
        this.passengerName = name;
        this.seatNum = seatNum;

        new OnlinePaymentUI(this);

        txtAmount.setText("₱10.00");

        btnConfirmTransac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String payment = (String)cmbEWalletBank.getSelectedItem();
                String accNum = txtAccNum.getText().trim();

                if (name.isEmpty() || accNum.isEmpty()) {
                    JOptionPane.showMessageDialog(onlinePaymentPanel,
                            "Name or Account number cannot be empty.");
                    return;
                }

                boolean saved = TransactionHistory.saveTransaction(
                        busNum,
                        seatNum,
                        name,
                        "Online Payment",
                        payment,
                        accNum
                );

                if (!saved) {
                    JOptionPane.showMessageDialog(
                            onlinePaymentPanel,
                            "Failed to save transaction."
                    );
                    return;
                }

                JOptionPane.showMessageDialog(
                        onlinePaymentPanel,
                        "Transaction recorded successfully."
                );

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

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(onlinePaymentPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public JPanel getOnlinePaymentPanel() {
        return onlinePaymentPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnConfirmTransac = new RoundedButton("CONFIRM TRANSACTION");
        btnHeader = new RoundedButton("ONLINE PAYMENT");
        paymentCardPanel = new RoundedPanel();
    }
}

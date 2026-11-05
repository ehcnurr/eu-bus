import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class OnlinePayment {
    private JTextField txtName;
    private JPanel onlinePaymentPanel;
    private JComboBox cmbEWalletBank;
    private JTextField txtAccNum;
    private JButton btnConfirmTransac;
    private int busNum;
    private String seatNum;

    public OnlinePayment(int busNum, String seatNum) {
        this.busNum = busNum;
        this.seatNum = seatNum;

        btnConfirmTransac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
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
            }


        });
    }

    public JPanel getOnlinePaymentPanel() {
        return onlinePaymentPanel;
    }
}

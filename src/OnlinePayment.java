import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlinePayment {
    private JTextField txtName;
    private JPanel onlinePaymentPanel;
    private JComboBox cmbEWalletBank;
    private JTextField txtAccNum;
    private JButton btnConfirmTransac;

    public OnlinePayment() {
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
            }
        });
    }

    public JPanel getOnlinePaymentPanel() {
        return onlinePaymentPanel;
    }
}

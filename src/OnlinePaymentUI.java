import javax.swing.*;
import java.awt.*;

public class OnlinePaymentUI {

    private final OnlinePayment onlinePayment;

    public OnlinePaymentUI(OnlinePayment onlinePayment) {
        this.onlinePayment = onlinePayment;

        styleComponents();
    }

    private void styleComponents() {

        // Get components using OnlinePayment getters
        JSeparator line = onlinePayment.getLine();
        JSeparator line2 = onlinePayment.getLine2();
        JButton btnHeader = onlinePayment.getBtnHeader();

        // COLORS
        Color gold = new Color(212, 175, 55);
        Color burgundy = new Color(128, 0, 32);

        // =========================
        // GOLD DIVIDER 1
        // =========================
        line.setOrientation(SwingConstants.HORIZONTAL);
        line.setForeground(gold);
        line.setBackground(gold);

        // =========================
        // GOLD DIVIDER 2
        // =========================
        line2.setOrientation(SwingConstants.HORIZONTAL);
        line2.setForeground(gold);
        line2.setBackground(gold);

        // =========================
        // HEADER BUTTON
        // =========================
        btnHeader.setBackground(burgundy);
        btnHeader.setForeground(Color.WHITE);

        btnHeader.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btnHeader.setFocusPainted(false);
        btnHeader.setBorderPainted(false);
    }
}
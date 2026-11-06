
import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int radius = 25;

    public RoundedButton(String text) {
        super(text);

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (getModel().isPressed()) {
            g2.setColor(new Color(90, 0, 25));
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(150, 0, 40));
        } else {
            g2.setColor(new Color(128, 0, 32));
        }

        g2.fillRoundRect(
                0, 0,
                getWidth(),
                getHeight(),
                radius,
                radius
        );

        g2.dispose();

        super.paintComponent(g);
    }
}

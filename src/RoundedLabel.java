
import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {

    private int radius = 25;

    public RoundedLabel(String text) {
        super(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setOpaque(false);
        setForeground(Color.WHITE);
        setBackground(new Color(128, 0, 32));
        setFont(new Font("Segoe UI", Font.BOLD, 32));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());

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

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int radius = 30;

    public RoundedPanel() {
        setOpaque(false);
        setBackground(new Color(128, 0, 32));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

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
                radius, radius
        );

        g2.dispose();
    }
}

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedScrollPane extends JScrollPane {

    public RoundedScrollPane(Component view) {
        super(view);

        setOpaque(false);
        setBorder(new RoundedBorder(15));

        getViewport().setOpaque(false);
        getViewport().setBackground(Color.WHITE);

        setBackground(Color.WHITE);
    }

    private static class RoundedBorder extends AbstractBorder {

        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(new Color(212, 175, 55));

            g2.setStroke(new BasicStroke(1.5f));

            g2.draw(new RoundRectangle2D.Double(
                    x + 1,
                    y + 1,
                    width - 3,
                    height - 3,
                    radius,
                    radius
            ));

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }
    }
}

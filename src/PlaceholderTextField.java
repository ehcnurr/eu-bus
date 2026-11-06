
import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {

    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));

            Insets insets = getInsets();

            g2.drawString(
                    placeholder,
                    insets.left,
                    getHeight() / 2 +
                            g2.getFontMetrics().getAscent() / 2 - 2
            );

            g2.dispose();
        }
    }
}

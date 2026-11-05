
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class BackgroundPanel extends JPanel {

    private BufferedImage backgroundImage;

    public BackgroundPanel() {
        try {
            URL imageURL = getClass().getResource(
                    "/images/mseufbackground.jpg"
            );

            if (imageURL == null) {
                System.out.println("Background image not found!");
            } else {
                backgroundImage = ImageIO.read(imageURL);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(
                    backgroundImage,
                    0, 0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}

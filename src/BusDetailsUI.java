import javax.swing.*;
import java.awt.*;

public class BusDetailsUI {

    private final BusDetails busDetails;

    public BusDetailsUI(BusDetails busDetails) {
        this.busDetails = busDetails;

        // Get components using getters
        JPanel arrivingTimePanel = busDetails.getArrivingTimePanel();
        JPanel departingTimePanel = busDetails.getDepartingTimePanel();

        JTextField txtArrivingTime = busDetails.getTxtArrivingTime();
        JTextField txtDepartingTime = busDetails.getTxtDepartingTime();

        JLabel lblUniversity = busDetails.getLblUniversity();
        JLabel lblSubtitle = busDetails.getLblSubtitle();
        JLabel lblChoose = busDetails.getLblChoose();
        JLabel lblSched = busDetails.getLblSched();

        JPanel goldLinePanel = busDetails.getGoldLinePanel();

        JComboBox cmbChooseBus = busDetails.getCmbChooseBus();

        JButton btnSelectBus = busDetails.getBtnSelectBus();
        JButton btnBusDetailsReturn = busDetails.getBtnBusDetailsReturn();

        // Time panels
        customizeTimePanel(
                arrivingTimePanel,
                "ARRIVING TIME",
                txtArrivingTime
        );

        customizeTimePanel(
                departingTimePanel,
                "DEPARTING TIME",
                txtDepartingTime
        );

        try {
            // univ logo
            lblUniversity.setIcon(
                    getIcon("eu-logo.jpg", 95, 95)
            );

            lblUniversity.setText("MSEUF");

            lblUniversity.setFont(
                    new Font("Segoe UI", Font.BOLD, 22)
            );

            lblUniversity.setForeground(new Color(128, 0, 32));

            lblUniversity.setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            lblUniversity.setHorizontalTextPosition(
                    SwingConstants.CENTER
            );

            lblUniversity.setVerticalTextPosition(
                    SwingConstants.BOTTOM
            );

            lblUniversity.setIconTextGap(10);

            lblSubtitle.setFont(
                    new Font("Segoe UI", Font.PLAIN, 15)
            );

            lblChoose.setFont(
                    new Font("Segoe UI", Font.BOLD, 12)
            );

            lblSched.setFont(
                    new Font("Segoe UI", Font.PLAIN, 15)
            );

            lblSubtitle.setForeground(Color.GRAY);
            lblChoose.setForeground(Color.GRAY);
            lblSched.setForeground(Color.GRAY);

            lblSubtitle.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        // GOLD LINE
        goldLinePanel.setBackground(new Color(212, 175, 55));
        goldLinePanel.setOpaque(true);

        goldLinePanel.setPreferredSize(new Dimension(0, 2));
        goldLinePanel.setMinimumSize(new Dimension(0, 2));
        goldLinePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 2)
        );

        Color warmWhite = new Color(247, 245, 242);

        Dimension inputSize = new Dimension(0, 100);

        arrivingTimePanel.setPreferredSize(inputSize);
        arrivingTimePanel.setMinimumSize(
                new Dimension(0, 100)
        );
        arrivingTimePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 100)
        );

        departingTimePanel.setPreferredSize(inputSize);
        departingTimePanel.setMinimumSize(
                new Dimension(0, 100)
        );
        departingTimePanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 100)
        );

        txtArrivingTime.setEditable(false);
        txtArrivingTime.setEditable(false);
        txtArrivingTime.setFocusable(false);
        txtArrivingTime.setBackground(warmWhite);
        txtArrivingTime.setForeground(Color.DARK_GRAY);
        txtArrivingTime.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );
        txtArrivingTime.setBorder(null);

        txtDepartingTime.setEditable(false);
        txtDepartingTime.setEditable(false);
        txtDepartingTime.setFocusable(false);
        txtDepartingTime.setBackground(warmWhite);
        txtDepartingTime.setForeground(Color.DARK_GRAY);
        txtDepartingTime.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );
        txtDepartingTime.setBorder(null);

        cmbChooseBus.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        cmbChooseBus.setBackground(Color.WHITE);
        cmbChooseBus.setForeground(Color.DARK_GRAY);

        txtArrivingTime.setFont(
                new Font("Segoe UI", Font.PLAIN, 17)
        );

        txtDepartingTime.setFont(
                new Font("Segoe UI", Font.PLAIN, 17)
        );

        JButton[] buttons = {
                btnSelectBus,
                btnBusDetailsReturn
        };

        Color burgundy = new Color(128, 0, 32);

        for (JButton button : buttons) {
            button.setBackground(burgundy);
            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font("Segoe UI", Font.BOLD, 16)
            );

            button.setFocusPainted(false);
            button.setBorderPainted(false);
        }
    }

    private void customizeTimePanel(
            JPanel panel,
            String title,
            JTextField timeField) {

        panel.removeAll();

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        panel.setBackground(
                new Color(247, 245, 242)
        );

        panel.setOpaque(true);

        // clock
        JLabel lblTimeIcon = new JLabel(
                getIcon("eu_time.png", 20, 20)
        );

        lblTimeIcon.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // arriving / departing label
        JLabel lblTimeTitle = new JLabel(title);

        lblTimeTitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        lblTimeTitle.setForeground(Color.GRAY);

        lblTimeTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // time text
        timeField.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        timeField.setForeground(Color.BLACK);
        timeField.setEditable(false);
        timeField.setBorder(null);
        timeField.setOpaque(false);

        timeField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // arrange vertically
        panel.add(lblTimeIcon);

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(lblTimeTitle);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(timeField);

        // inner spacing
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 12, 10, 12
                )
        );

        panel.revalidate();
        panel.repaint();
    }

    private ImageIcon getIcon(
            String filename,
            int width,
            int height) {

        ImageIcon icon = new ImageIcon(
                getClass().getResource(
                        "/images/" + filename
                )
        );

        Image image = icon.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }
}
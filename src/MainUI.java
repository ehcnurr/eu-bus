import javax.swing.*;
import java.awt.*;

public class MainUI {

    private Main main;

    // Components from Main.java
    private JLabel lblUniversity;
    private JLabel lblSubtitle;

    private JButton btnStudentStaff;
    private JButton btnDriver;
    private JButton btnGuest;

    private JPanel mainPanel;
    private JPanel instructionPanel;

    private JLabel lblChooseUserType;
    private JLabel lblSelectOption;

    // CONSTRUCTOR
    public MainUI(Main main) {

        this.main = main;

        // GET COMPONENTS USING GETTERS FROM Main.java
        lblUniversity = main.getLblUniversity();
        lblSubtitle = main.getLblSubtitle();

        btnStudentStaff = main.getBtnStudentStaff();
        btnDriver = main.getBtnDriver();
        btnGuest = main.getBtnGuest();

        mainPanel = main.getMainPanel();
        instructionPanel = main.getInstructionPanel();

        lblChooseUserType = main.getLblChooseUserType();
        lblSelectOption = main.getLblSelectOption();

        // CUSTOMIZE UI
        customizeUniversity();
        customizeInstructions();
        customizeButtons();
        buildCenteredLayout();
    }

    // UNIVERSITY LOGO
    private void customizeUniversity() {

        lblUniversity.setIcon(
                getIcon("mseuf-logo.png", 95, 95)
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

        // SUBTITLE
        lblSubtitle.setText("SCHOOL BUS SEAT RESERVATION");

        lblSubtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        lblSubtitle.setForeground(Color.GRAY);

        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // INSTRUCTIONS
    private void customizeInstructions() {

        instructionPanel.removeAll();

        instructionPanel.setLayout(
                new BoxLayout(instructionPanel, BoxLayout.Y_AXIS)
        );

        instructionPanel.setOpaque(false);

        lblChooseUserType.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblSelectOption.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        instructionPanel.add(lblChooseUserType);
        instructionPanel.add(Box.createVerticalStrut(5));
        instructionPanel.add(lblSelectOption);

        instructionPanel.revalidate();
        instructionPanel.repaint();
    }

    // IMAGE ICON
    private ImageIcon getIcon(String filename, int width, int height) {

        java.net.URL imageURL =
                getClass().getResource("/images/" + filename);

        if (imageURL == null) {
            System.out.println("IMAGE NOT FOUND: " + filename);
            return null;
        }

        ImageIcon icon = new ImageIcon(imageURL);

        Image image = icon.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

    // CUSTOMIZE BUTTONS
    private void customizeButtons() {

        Color burgundy = new Color(128, 0, 32);

        JButton[] buttons = {
                btnStudentStaff,
                btnDriver,
                btnGuest
        };

        for (JButton button : buttons) {

            button.setBackground(burgundy);
            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font("Segoe UI", Font.BOLD, 16)
            );

            button.setPreferredSize(
                    new Dimension(420, 55)
            );

            button.setMinimumSize(
                    new Dimension(100, 55)
            );

            button.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 55)
            );

            button.setFocusPainted(false);
            button.setBorderPainted(false);
        }
    }

    // CENTERED LAYOUT
    private void buildCenteredLayout() {

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.Y_AXIS)
        );

        contentPanel.setOpaque(false);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 35, 30, 35
                )
        );

        // UNIVERSITY
        lblUniversity.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblSubtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // INSTRUCTIONS
        instructionPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // BUTTONS
        JButton[] buttons = {
                btnStudentStaff,
                btnDriver,
                btnGuest
        };

        for (JButton button : buttons) {
            button.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );
        }

        // TOP SECTION
        contentPanel.add(lblUniversity);

        contentPanel.add(
                Box.createVerticalStrut(5)
        );

        contentPanel.add(lblSubtitle);

        // DIVIDER
        contentPanel.add(
                Box.createVerticalStrut(20)
        );

        JSeparator separator =
                new JSeparator(SwingConstants.HORIZONTAL);

        separator.setForeground(Color.BLACK);
        separator.setBackground(Color.BLACK);

        separator.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 2)
        );

        contentPanel.add(separator);

        contentPanel.add(
                Box.createVerticalStrut(30)
        );

        // INSTRUCTIONS
        contentPanel.add(instructionPanel);

        contentPanel.add(
                Box.createVerticalStrut(50)
        );

        // BUTTONS
        contentPanel.add(btnStudentStaff);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );

        contentPanel.add(btnDriver);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );

        contentPanel.add(btnGuest);

        // CENTER EVERYTHING
        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(contentPanel, gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
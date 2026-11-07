import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Main {
    private JButton btnStudentStaff;
    private JButton btnDriver;
    private JButton btnGuest;
    private JPanel mainPanel;
    private JLabel lblUniversity;
    private JPanel instructionPanel;
    private JLabel lblChooseUserType;
    private JLabel lblSelectOption;
    private JLabel lblSubtitle;

    Payment payment = new Payment();

    public Main() {
        // Initialize the GUI Designer components

        // Customize Student/Staff button

        try {
            // UNIVERSITY LOGO
            lblUniversity.setIcon(
                    getIcon("mseuf-logo.png", 95, 95)
            );

            lblUniversity.setText("MSEUF");

            lblUniversity.setFont(
                    new Font("Segoe UI", Font.BOLD, 22)
            );

            lblUniversity.setForeground(new Color(128, 0, 32));

// Put text BELOW the logo
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
            lblSubtitle = new JLabel("SCHOOL BUS SEAT RESERVATION");

            lblSubtitle.setFont(
                    new Font("Segoe UI", Font.PLAIN, 15)
            );

            lblSubtitle.setForeground(Color.GRAY);

            lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        customizeButtons();

        buildCenteredLayout();

        btnStudentStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment.setType("student/staff");

                try {
                    JFrame frame = new JFrame("STUDENT / STAFF LOGIN");
                    studentLogIn form = new studentLogIn();

                    JPanel panel = form.getStudentLoginPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getStudentLoginPanel returned null. Check studentLogIn.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnDriver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame = new JFrame("DRIVER LOGIN");
                    BusDriverLogIn form = new BusDriverLogIn();

                    JPanel panel = form.getDriverLoginPanel();

                    if (panel == null) {
                        JOptionPane.showMessageDialog(null,
                                "Error: getDriverLoginPanel returned null. Check BusDriverLogIn.java!",
                                "Null Panel Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(490, 720);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment.setType("guest");

                try {
                JFrame frame = new JFrame("AVAILABLE BUSES");
                BusesAvailable form = new BusesAvailable();

                JPanel panel = form.getAvailableBusPanel();
                if (panel == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: getAvailableBusPanel returned null. Check BusesAvailable.java!",
                            "Null Panel Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                frame.setContentPane(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(490, 720);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MAIN");

            Main form = new Main();
            frame.setContentPane(form.mainPanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(490, 720);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private ImageIcon getIcon(String filename, int width, int height) {

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/" + filename)
        );

        Image image = icon.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

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


    private void buildCenteredLayout() {

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.Y_AXIS)
        );

        contentPanel.setOpaque(false);

        // MARGINS FROM THE PANEL CORNERS
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 35, 30, 35
                )
        );

        // UNIVERSITY LOGO AND TITLE
        lblUniversity.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // INSTRUCTIONS
        instructionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // BUTTONS
        JButton[] buttons = {
                btnStudentStaff,
                btnDriver,
                btnGuest
        };

        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // TOP SECTION
        contentPanel.add(lblUniversity);

        contentPanel.add(Box.createVerticalStrut(5));

        contentPanel.add(lblSubtitle);

        // DIVIDER / SPACING

        contentPanel.add(Box.createVerticalStrut(20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        separator.setForeground(Color.BLACK);
        separator.setBackground(Color.BLACK);
        separator.setPreferredSize(new Dimension(200, 4));

        contentPanel.add(separator);

        contentPanel.add(Box.createVerticalStrut(30));



        // INSTRUCTIONS
        contentPanel.add(instructionPanel);

        contentPanel.add(Box.createVerticalStrut(50));

        // BUTTONS
        contentPanel.add(btnStudentStaff);

        contentPanel.add(Box.createVerticalStrut(25));

        contentPanel.add(btnDriver);

        contentPanel.add(Box.createVerticalStrut(25));

        contentPanel.add(btnGuest);

        // CENTER EVERYTHING
        mainPanel.removeAll();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(contentPanel, gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void createUIComponents() {
        btnStudentStaff = new RoundedButton("STUDENT/STAFF");
        btnDriver = new RoundedButton("DRIVER");
        btnGuest = new RoundedButton("GUEST");

        mainPanel = new BackgroundPanel();
    }

}

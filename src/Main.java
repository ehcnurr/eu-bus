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


    public Main() {
        // Initialize the GUI Designer components

        // Customize Student/Staff button
        btnStudentStaff.setOpaque(true);
        btnStudentStaff.setContentAreaFilled(true);
        btnStudentStaff.setBackground(new Color(128, 0, 32));
        btnStudentStaff.setForeground(Color.WHITE);

        // Customize Driver button
        btnDriver.setOpaque(true);
        btnDriver.setContentAreaFilled(true);
        btnDriver.setBackground(new Color(128, 0, 32));
        btnDriver.setForeground(Color.WHITE);

        // Customize Guest button
        btnGuest.setOpaque(true);
        btnGuest.setContentAreaFilled(true);
        btnGuest.setBackground(new Color(128, 0, 32));
        btnGuest.setForeground(Color.WHITE);

        btnStudentStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainPanel = new BackgroundPanel();
    }
}
